using IIS.Client.Interactive.CommandLine.Parser;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;
using System.CommandLine;
using System.Diagnostics;
using SlaveProgram = IIS.Client.Program;

namespace IIS.Client.Interactive.CommandLine;

internal class Shell
{
    private readonly List<CinemaArguments> _history = new();
    private readonly ArgumentBuilder _argumentBuilder;

    public Shell(CommandCompletionService completionService)
    {
        _argumentBuilder = new ArgumentBuilder
        {
            CompletionService = completionService
        };
    }

    public static Shell Create(Command rootCommand)
    {
        CommandCompletionService completionService = CommandCompletionService.CreateFor(rootCommand);
        Shell shell = new(completionService);
        return shell;
    }

    public void Start()
    {
        while (true)
        {
            _argumentBuilder.Reset();
            string beforeCompletionBackup = string.Empty;
            bool isCurrentValid;
            bool isParentValid = true;
            bool isAutoCompleting = false;
            //IEnumerator<CompletionItem>? suggestions = null;
            int historyIndex = -1;
            while (true)
            {
                _argumentBuilder.LineRenderer.RenderLine();
                ConsoleKeyInfo keyInfo = Console.ReadKey(intercept: true);
                if (char.IsLetter(keyInfo.KeyChar) || char.IsSymbol(keyInfo.KeyChar))
                {
                    _argumentBuilder.WordBuilder.Append(keyInfo);
                    _argumentBuilder.CompletionService.ResetSuggestions();
                    _argumentBuilder.LineRenderer.Append(keyInfo);
                }
                // start history
                if (keyInfo.Key is ConsoleKey.UpArrow)
                {
                    if (++historyIndex < _history.Count)
                    {
                        CinemaArguments historyEntry = _history[historyIndex];
                        _argumentBuilder.LineRenderer.Load(historyEntry.Line);
                        _argumentBuilder.Arguments.Clear();
                        foreach (string arg in historyEntry.Arguments.Reverse())
                        {
                            _argumentBuilder.Arguments.Push(arg);
                        }
                        _argumentBuilder.WordBuilder.Clear();
                        continue;
                    }
                    else
                    {
                        historyIndex -= 1;
                    }
                }
                else if (keyInfo.Key is ConsoleKey.DownArrow)
                {
                    if (--historyIndex >= 0)
                    {
                        CinemaArguments historyEntry = _history[historyIndex];
                        _argumentBuilder.LineRenderer.Load(historyEntry.Line);
                        _argumentBuilder.Arguments.Clear();
                        foreach (string arg in historyEntry.Arguments.Reverse())
                        {
                            _argumentBuilder.Arguments.Push(arg);
                        }
                        _argumentBuilder.WordBuilder.Clear();
                        continue;
                    }
                    else
                    {
                        historyIndex += 1;
                    }
                }
                else
                {
                    historyIndex = -1;
                }
                // end history
                if (keyInfo.Key is ConsoleKey.Backspace)
                {
                    if (keyInfo.Modifiers == ConsoleModifiers.Control)
                    {
                        _argumentBuilder.RemoveLastWord();
                    }
                    else
                    {
                        _argumentBuilder.RemoveLastCharacter();
                    }
                }
                else if (keyInfo.Key is ConsoleKey.Spacebar or ConsoleKey.Enter)
                {
                    _argumentBuilder.CompleteCurrentArgument();
                    _argumentBuilder.LineRenderer.Append(keyInfo.KeyChar);
                    if (keyInfo.Key is ConsoleKey.Enter)
                    {
                        Stdout.WriteLine();
                        break;
                    }
                }
                // tab-completion & validation
                if (_argumentBuilder.IsValid)
                {
                    if (!isAutoCompleting)
                    {
                        _argumentBuilder.CompletionService.UpdateSuggestions(_argumentBuilder.WordBuilder.Value);
                    }
                    else if (keyInfo.Key is not ConsoleKey.Tab)
                    {
                        isAutoCompleting = false;
                    }
                }
                isCurrentValid = isParentValid && (_argumentBuilder.CompletionService.HasSuggestions || _argumentBuilder.WordBuilder.IsEmpty);
                if (keyInfo.Key is ConsoleKey.Tab && isParentValid && isCurrentValid)
                {
                    if (!_argumentBuilder.CompletionService.HasSuggestions)
                    {
                        continue;
                    }
                    // auto-complete
                    if (_argumentBuilder.CompletionService.TryGetNextSuggestion(out string? suggestion))
                    {
                        if (isAutoCompleting)
                        {
                            // full completion
                            _argumentBuilder.LineRenderer.RemoveCount(_argumentBuilder.WordBuilder.Length);
                            _argumentBuilder.WordBuilder.Load(suggestion);
                            _argumentBuilder.LineRenderer.Append(suggestion);
                        }
                        else
                        {
                            // partial completion
                            isAutoCompleting = true;
                            beforeCompletionBackup = _argumentBuilder.WordBuilder.Value;
                            string partialCompletion = suggestion[_argumentBuilder.WordBuilder.Length..];
                            _argumentBuilder.WordBuilder.Append(partialCompletion);
                            _argumentBuilder.LineRenderer.Append(partialCompletion);
                        }
                    }
                    else if (isAutoCompleting)
                    {
                        // cycled though all suggestions
                        _argumentBuilder.CompletionService.ResetSuggestions();
                        isAutoCompleting = false;
                        _argumentBuilder.LineRenderer.RemoveCount(_argumentBuilder.WordBuilder.Length);
                        // restore original value
                        _argumentBuilder.WordBuilder.Load(beforeCompletionBackup);
                        _argumentBuilder.LineRenderer.Append(beforeCompletionBackup);
                    }
                    else
                    {
                        _argumentBuilder.CompletionService.ResetSuggestions();
                    }
                }
                _argumentBuilder.LineRenderer.IsValid = isCurrentValid && isParentValid;
            }
            CinemaArguments arguments = _argumentBuilder.Build();
            RunSlave(arguments);
            _history.Insert(0, arguments);
        }
    }

    private static void RunSlave(CinemaArguments arguments)
    {
        Stdout.WriteLine($"Spawning 'cinema {arguments.Line}' as slave process ...", ConsoleColor.DarkGray);
        ProcessStartInfo cinemaClientInfo = new("cinema.exe", arguments.Line)
        {
            RedirectStandardOutput = true,
            RedirectStandardError = true,
            RedirectStandardInput = true,
            UseShellExecute = false,
        };
        cinemaClientInfo.EnvironmentVariables.Add(SlaveProgram.SLAVE_ENVIRONMENT_VARIABLE, "true");
        Process cinemaClient = new()
        {
            StartInfo = cinemaClientInfo,
        };
        cinemaClient.Start();

        IOForwarder.ForwardUntilExits(cinemaClient);
    }
}
