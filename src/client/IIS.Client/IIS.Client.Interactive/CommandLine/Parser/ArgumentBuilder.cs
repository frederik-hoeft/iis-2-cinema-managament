using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;
using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser;

internal class ArgumentBuilder
{
    private readonly WordBuilder _wordBuilder = new();
    private readonly CommandCompletionService CompletionService;
    private Stack<(string Argument, bool IsValid)> _arguments = new();
    private string _beforeCompletionBackup = string.Empty;
    private bool _isCurrentWordValid = true;
    private bool _isAutoCompleting = false;

    private readonly LineRenderer _renderer;

    public bool IsValid { get; private set; } = true;

    public ArgumentBuilder(CommandCompletionService completionService, LineRenderer renderer)
    {
        CompletionService = completionService;
        _renderer = renderer;
    }

    public CinemaArguments Build()
    {
        string cliArgs = string.Join(' ', _arguments.Select(arg => arg.Argument).Reverse());
        return new CinemaArguments(_arguments, cliArgs);
    }

    public void Reset()
    {
        _renderer.Clear();
        _wordBuilder.Clear();
        _arguments = new Stack<(string Argument, bool IsValid)>();
        IsValid = true;
        _isCurrentWordValid = true;
        ResetAutoComplete();
        _renderer.IsValid = true;
        CompletionService.Reset();
    }

    public void RemoveLastCharacter()
    {
        _renderer.TryRemoveLastCharacter();
        if (!_wordBuilder.TryRemoveLast() && _arguments.TryPop(out (string Argument, bool IsValid) previousArgument))
        {
            _wordBuilder.Load(previousArgument.Argument);
            _isCurrentWordValid = previousArgument.IsValid;
            if (_isCurrentWordValid)
            {
                CompletionService.TryMoveUp();
            }
            IsValid = !_arguments.TryPeek(out (string Argument, bool IsValid) result) || result.IsValid;
            _renderer.IsValid = IsValid && _isCurrentWordValid;
        }
    }

    public void RemoveLastWord()
    {
        string lastWord = _wordBuilder.Flush();
        if (string.IsNullOrEmpty(lastWord))
        {
            RemoveLastCharacter();
            return;
        }
        _renderer.RemoveCount(lastWord.Length);
    }

    public void FlushCurrentArgument()
    {
        if (!_wordBuilder.IsEmpty)
        {
            string arg = _wordBuilder.Flush();
            bool argIsValid = CompletionService.TryMoveDown(arg);
            IsValid = argIsValid;
            _arguments.Push((arg, IsValid));
        }
    }

    public bool Validate()
    {
        if (IsValid)
        {
            if (!_isAutoCompleting)
            {
                CompletionService.UpdateSuggestions(_wordBuilder.Value);
            }
            _isCurrentWordValid = CompletionService.HasSuggestions || _wordBuilder.IsEmpty;
        }
        else
        {
            _isCurrentWordValid = false;
        }
        _renderer.IsValid = _isCurrentWordValid;
        return _isCurrentWordValid;
    }

    public void ResetAutoComplete()
    {
        CompletionService.ResetSuggestions();
        _isAutoCompleting = false;
    }

    public void Append(char c)
    {
        _renderer.Append(c);
        _wordBuilder.Append(c);
    }

    public void Append(string s)
    {
        _renderer.Append(s); 
        _wordBuilder.Append(s);
    }

    public void Append(ConsoleKeyInfo key)
    {
        _renderer.Append(key.KeyChar);
        _wordBuilder.Append(key.KeyChar);
    }

    public void LoadFromHistory(CinemaArguments args)
    {
        _renderer.Load(args.Line);
        _renderer.Append(' ');
        _arguments.Clear();
        _wordBuilder.Clear();
        foreach ((string Argument, bool _) arg in args.Arguments.Reverse())
        {
            _arguments.Push(arg);
            CompletionService.TryMoveDown(arg.Argument);
        }
        IsValid = !_arguments.TryPeek(out (string Argument, bool IsValid) result) || result.IsValid;
        _renderer.IsValid = IsValid;
        _isCurrentWordValid = true;
    }

    public bool AutoComplete()
    {
        if (_isCurrentWordValid && CompletionService.HasSuggestions)
        {
            // auto-complete
            if (CompletionService.TryGetNextSuggestion(out CompletionItem? completion))
            {
                string suggestion = completion.InsertText ?? completion.Label;
                if (!_isAutoCompleting)
                {
                    // remove partial match
                    _isAutoCompleting = true;
                    _beforeCompletionBackup = _wordBuilder.Value;
                }
                _renderer.RemoveCount(_wordBuilder.Length);
                _wordBuilder.Load(suggestion);
                _renderer.Append(suggestion);
            }
            else if (_isAutoCompleting)
            {
                // cycled though all suggestions
                ResetAutoComplete();
                _renderer.RemoveCount(_wordBuilder.Length);
                // restore original value
                _wordBuilder.Load(_beforeCompletionBackup);
                _renderer.Append(_beforeCompletionBackup);
            }
            else
            {
                ResetAutoComplete();
            }
        }
        return false;
    }
}
