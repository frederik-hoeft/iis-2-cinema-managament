using IIS.Client.ApiAccess;
using IIS.Client.Interactive.CommandLine.Parser;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;
using System.CommandLine;
using System.Diagnostics;
using SlaveProgram = IIS.Client.Program;

namespace IIS.Client.Interactive.CommandLine;

using static AnsiColors;

internal class Shell
{
    private readonly CinemaArgumentHistory _history = new();
    private readonly ArgumentBuilder _argumentBuilder;
    private readonly LineRenderer _renderer;
    private readonly string _slaveProcessName;

    public Shell(CommandCompletionService completionService, LineRenderer renderer, string slaveProcessName)
    {
        _renderer = renderer;
        _argumentBuilder = new ArgumentBuilder(completionService, renderer);
        _slaveProcessName = slaveProcessName;
    }

    public static Shell Create(Command rootCommand)
    {
        CommandCompletionService completionService = CommandCompletionService.CreateFor(rootCommand);
        RuntimeConfig slaveConfig = SlaveProgram.LoadConfig();
        string slaveName = slaveConfig.ProcessName;
        string remoteEndpoint = new Uri(slaveConfig.ApiEndpoint).Authority;
        string prompt = $"{LIGHT_CYAN}launch against {YELLOW}{remoteEndpoint}{RESET}> {LIGHT_BLACK}\"";
        LineRenderer renderer = new(prompt, contentPrefix: "cinema ", invalidColor: LIGHT_RED, contentColor: WHITE);
        Shell shell = new(completionService, renderer, slaveName);
        return shell;
    }

    public void Start()
    {
        while (true)
        {
            _argumentBuilder.Reset();
            while (true)
            {
                _renderer.RenderPrompt();
                ConsoleKeyInfo keyInfo = Console.ReadKey(intercept: true);
                if (char.IsLetter(keyInfo.KeyChar) || keyInfo.KeyChar.IsAsciiSymbol())
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
                    if (keyInfo.Key is ConsoleKey.Enter)
                    {
                        _renderer.Append($"{LIGHT_BLACK}\"");
                        _renderer.RenderPrompt();
                        Stdout.WriteLine();
                        Stdout.WriteLine();
                        break;
                    }
                    _renderer.Append(keyInfo);
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

    private void RunSlave(CinemaArguments arguments)
    {
        _renderer.RenderLine($"{LIGHT_BLACK}Spawning '{WHITE}./{_slaveProcessName} {arguments.Line}{LIGHT_BLACK}' as slave process ...");
        Stdout.WriteLine();
        ProcessStartInfo cinemaClientInfo = new(_slaveProcessName, arguments.Line)
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
