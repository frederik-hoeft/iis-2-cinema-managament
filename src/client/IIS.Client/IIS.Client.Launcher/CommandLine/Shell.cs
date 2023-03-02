using IIS.Client.Interactive.CommandLine.Parser;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;
using System.CommandLine;
using System.Diagnostics;
using SlaveProgram = IIS.Client.Program;

namespace IIS.Client.Interactive.CommandLine;

internal class Shell
{
    private readonly CinemaArgumentHistory _history = new();
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
            while (true)
            {
                _argumentBuilder.LineRenderer.RenderLine();
                ConsoleKeyInfo keyInfo = Console.ReadKey(intercept: true);
                if (char.IsLetter(keyInfo.KeyChar) || char.IsSymbol(keyInfo.KeyChar))
                {
                    _argumentBuilder.Append(keyInfo);
                }
                else if (keyInfo.Key is ConsoleKey.UpArrow)
                {
                    if (_history.TryGetPrevious(out CinemaArguments? historyEntry))
                    {
                        _argumentBuilder.LoadFromHistory(historyEntry);
                        continue;
                    }
                }
                else if (keyInfo.Key is ConsoleKey.DownArrow)
                {
                    if (_history.TryGetNext(out CinemaArguments? historyEntry))
                    {
                        _argumentBuilder.LoadFromHistory(historyEntry);
                        continue;
                    }
                }
                else
                {
                    _history.ResetEnumerator();
                }
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
                    _argumentBuilder.FlushCurrentArgument();
                    _argumentBuilder.LineRenderer.Append(keyInfo);
                    if (keyInfo.Key is ConsoleKey.Enter)
                    {
                        Stdout.WriteLine();
                        break;
                    }
                }
                // tab-completion & validation
                _argumentBuilder.Validate();
                if (keyInfo.Key is ConsoleKey.Tab)
                {
                    _argumentBuilder.AutoComplete();
                }
                else
                {
                    _argumentBuilder.ResetAutoComplete();
                }
            }
            CinemaArguments arguments = _argumentBuilder.Build();
            RunSlave(arguments);
            _history.Add(arguments);
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
