using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

namespace IIS.Client.Interactive.CommandLine.Parser;

internal class ArgumentBuilder
{
    public LineRenderer LineRenderer { get; } = new("# ");

    public WordBuilder WordBuilder { get; } = new();

    public CommandCompletionService CompletionService { get; set; } = null!;

    public Stack<(string Argument, bool IsValid)> Arguments { get; private set; } = new();

    public bool IsValid { get; private set; } = true;

    public bool IsCurrentWordValid { get; private set; } = true;

    public bool IsAutoCompleting { get; set; } = false;

    private string _beforeCompletionBackup = string.Empty;

    public CinemaArguments Build()
    {
        string cliArgs = string.Join(' ', Arguments.Select(arg => arg.Argument).Reverse());
        return new CinemaArguments(Arguments, cliArgs);
    }

    public void Reset()
    {
        IsAutoCompleting = false;
        LineRenderer.Clear();
        WordBuilder.Clear();
        Arguments = new Stack<(string Argument, bool IsValid)>();
    }

    public void RemoveLastCharacter()
    {
        LineRenderer.TryRemoveLastCharacter();
        if (!WordBuilder.TryRemoveLast() && Arguments.TryPop(out (string Argument, bool IsValid) previousArgument))
        {
            WordBuilder.Load(previousArgument.Argument);
            IsValid = previousArgument.IsValid;
            CompletionService.TryMoveUp();
        }
    }

    public void RemoveLastWord()
    {
        string lastWord = WordBuilder.Flush();
        if (!string.IsNullOrEmpty(lastWord))
        {
            LineRenderer.RemoveCount(lastWord.Length);
            if (Arguments.TryPop(out (string Argument, bool IsValid) previousArgument))
            {
                WordBuilder.Load(previousArgument.Argument);
                IsValid = previousArgument.IsValid;
                CompletionService.TryMoveUp();
            }
            else
            {
                IsValid = true;
            }
        }
    }

    public void FlushCurrentArgument()
    {
        if (!WordBuilder.IsEmpty)
        {
            string arg = WordBuilder.Flush();
            bool argIsValid = CompletionService.TryMoveDown(arg);
            IsValid = argIsValid;
            Arguments.Push((arg, IsValid));
        }
    }

    public bool Validate()
    {
        if (IsValid)
        {
            if (!IsAutoCompleting)
            {
                CompletionService.UpdateSuggestions(WordBuilder.Value);
            }
            IsCurrentWordValid = CompletionService.HasSuggestions || WordBuilder.IsEmpty;
        }
        else
        {
            IsCurrentWordValid = false;
        }
        LineRenderer.IsValid = IsCurrentWordValid;
        return IsCurrentWordValid;
    }

    public void ResetAutoComplete()
    {
        CompletionService.ResetSuggestions();
        IsAutoCompleting = false;
    }

    public void Append(char c)
    {
        LineRenderer.Append(c);
        WordBuilder.Append(c);
    }

    public void Append(string s)
    {
        LineRenderer.Append(s); 
        WordBuilder.Append(s);
    }

    public void Append(ConsoleKeyInfo key)
    {
        LineRenderer.Append(key.KeyChar);
        WordBuilder.Append(key.KeyChar);
    }

    public void LoadFromHistory(CinemaArguments args)
    {
        LineRenderer.Load(args.Line);
        Arguments.Clear();
        WordBuilder.Clear();
        foreach ((string Argument, bool _) arg in args.Arguments.Reverse())
        {
            Arguments.Push(arg);
        }
        IsValid = !Arguments.TryPeek(out (string Argument, bool IsValid) result) || result.IsValid;
    }

    public bool AutoComplete()
    {
        if (IsCurrentWordValid && CompletionService.HasSuggestions)
        {
            // auto-complete
            if (CompletionService.TryGetNextSuggestion(out string? suggestion))
            {
                if (IsAutoCompleting)
                {
                    // full completion
                    LineRenderer.RemoveCount(WordBuilder.Length);
                    WordBuilder.Load(suggestion);
                    LineRenderer.Append(suggestion);
                }
                else
                {
                    // partial completion
                    IsAutoCompleting = true;
                    _beforeCompletionBackup = WordBuilder.Value;
                    string partialCompletion = suggestion[WordBuilder.Length..];
                    WordBuilder.Append(partialCompletion);
                    LineRenderer.Append(partialCompletion);
                }
            }
            else if (IsAutoCompleting)
            {
                // cycled though all suggestions
                ResetAutoComplete();
                LineRenderer.RemoveCount(WordBuilder.Length);
                // restore original value
                WordBuilder.Load(_beforeCompletionBackup);
                LineRenderer.Append(_beforeCompletionBackup);
            }
            else
            {
                ResetAutoComplete();
            }
        }
        return false;
    }
}
