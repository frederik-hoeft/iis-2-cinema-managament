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
    private readonly RuntimeConfig _slaveConfig;
    private readonly string _slaveProcessName;

    public Shell(CommandCompletionService completionService, LineRenderer renderer, string slaveProcessName, RuntimeConfig slaveConfig)
    {
        _renderer = renderer;
        _argumentBuilder = new ArgumentBuilder(completionService, renderer);
        _slaveProcessName = slaveProcessName;
        _slaveConfig = slaveConfig;
    }

    public static Shell Create(Command rootCommand)
    {
        CommandCompletionService completionService = CommandCompletionService.CreateFor(rootCommand);
        RuntimeConfig slaveConfig = SlaveProgram.LoadConfig();
        string slaveName = slaveConfig.ProcessName;
        string remoteEndpoint = new Uri(slaveConfig.ApiEndpoint).Authority;
        string prompt = $"{LIGHT_CYAN}launch against {YELLOW}{remoteEndpoint}{RESET}> {LIGHT_BLACK}\"";
        LineRenderer renderer = new(prompt, contentPrefix: "cinema ", invalidColor: LIGHT_RED, contentColor: WHITE);
        Shell shell = new(completionService, renderer, slaveName, slaveConfig);
        return shell;
    }

    public void Start()
    {
        Stdout.ResetColor();
        Stdout.WriteLine(new string('=', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        string welcome = $"{LIGHT_BLACK}Welcome to the {WHITE}IIS cinema client launcher{LIGHT_BLACK} v{LIGHT_BLUE}{SlaveProgram.Version}{LIGHT_BLACK}!";
        int welcomeRenderedLength = _renderer.GetRenderedTextLength(welcome);
        string padding = new(' ', (Console.WindowWidth - welcomeRenderedLength) / 2);
        _renderer.RenderLine(padding + welcome);
        Stdout.WriteLine(new string('=', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        Stdout.WriteLine(new string('-', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        string title = "KEY BINDINGS";
        padding = new(' ', (Console.WindowWidth - title.Length) / 2);
        Stdout.Write(padding, ConsoleColor.DarkGray);
        Stdout.WriteLine(title, ConsoleColor.DarkGray);
        Stdout.WriteLine(new string('-', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        Stdout.WriteLine();
        _renderer.RenderLine($"{LIGHT_BLACK}This program is an interactive wrapper around {WHITE}./{_slaveProcessName}{LIGHT_BLACK}.");
        _renderer.RenderLine($"{LIGHT_BLACK}- you have context-aware {WHITE}[TAB]{LIGHT_BLACK} auto-completion available.");
        _renderer.RenderLine($"{LIGHT_BLACK}- you may at any time type '{WHITE}--help{LIGHT_BLACK}' to retrieve more information about a command or option.");
        _renderer.RenderLine($"{LIGHT_BLACK}- you may use {WHITE}[ARROW UP]{LIGHT_BLACK} and {WHITE}[ARROW DOWN]{LIGHT_BLACK} to cycle through your most recent commands.");
        _renderer.RenderLine($"{LIGHT_BLACK}- you may also press {WHITE}[CTRL]+[BACKSPACE]{LIGHT_BLACK} to delete the whole word at once.");
        Stdout.WriteLine();
        Stdout.WriteLine(new string('-', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        title = "CONFIGURATION";
        padding = new(' ', (Console.WindowWidth - title.Length) / 2);
        Stdout.Write(padding, ConsoleColor.DarkGray);
        Stdout.WriteLine(title, ConsoleColor.DarkGray);
        Stdout.WriteLine(new string('-', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        Stdout.WriteLine();
        bool hasErrors = false;
        _renderer.RenderLine($"{LIGHT_BLACK}Before running this client please verify that the following content of the '{WHITE}./{SlaveProgram.CFG_FILE_NAME}{LIGHT_BLACK}' config file is correct:");
        _renderer.RenderLine($"{WHITE}{{");
        if (Uri.IsWellFormedUriString(_slaveConfig.ApiEndpoint, UriKind.Absolute))
        {
            _renderer.RenderLine($"{WHITE}    {YELLOW}\"{nameof(RuntimeConfig.ApiEndpoint)}\"{WHITE}: {LIGHT_GREEN}\"{LIGHT_BLUE}{_slaveConfig.ApiEndpoint}{LIGHT_GREEN}\"{WHITE},");
        }
        else
        {
            hasErrors = true;
            _renderer.RenderLine($"{WHITE}    {YELLOW}\"{nameof(RuntimeConfig.ApiEndpoint)}\"{WHITE}: {LIGHT_RED}\"{_slaveConfig.ApiEndpoint}\"{WHITE},   {LIGHT_RED} <-- ERROR: {nameof(RuntimeConfig.ApiEndpoint)} is invalid or missing!");
        }
        if (File.Exists(_slaveConfig.ProcessName) || File.Exists(_slaveConfig.ProcessName + ".exe"))
        {
            _renderer.RenderLine($"{WHITE}    {YELLOW}\"{nameof(RuntimeConfig.ProcessName)}\"{WHITE}: {LIGHT_GREEN}\"{_slaveConfig.ProcessName}\"{WHITE}");
        }
        else
        {
            _renderer.RenderLine($"{WHITE}    {YELLOW}\"{nameof(RuntimeConfig.ProcessName)}\"{WHITE}: {LIGHT_RED}\"{_slaveConfig.ProcessName}\"    <-- ERROR: {nameof(RuntimeConfig.ProcessName)} is invalid or file '{_slaveConfig.ProcessName}[.exe]' could not be found!");
            hasErrors = true;
        }
        _renderer.RenderLine($"{WHITE}}}");
        Stdout.WriteLine();

        if (hasErrors)
        {
            _renderer.RenderLine($"{LIGHT_RED} Please fix all configuration issues before continuing ...");
            Console.ReadKey();
            return;
        }

        Stdout.WriteLine(new string('-', Console.WindowWidth - 1), ConsoleColor.DarkGray);
        Stdout.WriteLine();
        Stdout.WriteLine();

        Stdout.ResetColor();

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
