using IIS.Client.Interactive.AutoComplete;
using System;
using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics;
using System.Diagnostics.CodeAnalysis;
using System.Text;
using System.Xml.Linq;
using SlaveProgram = IIS.Client.Program;

Stdout.ResetColor();
Stdout.WriteLine($"Welcome to the IIS cinema client v{SlaveProgram.Version}!");

Command rootCommand = SlaveProgram.BuildCommandTree();

Shell shell = Shell.Create(rootCommand);
shell.Start();

class Shell
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
                        foreach (string arg in historyEntry.Arguments)
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

    private void RunSlave(CinemaArguments arguments)
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

class ArgumentBuilder
{
    public LineRenderer LineRenderer { get; } = new();

    public WordBuilder WordBuilder { get; } = new();

    public CommandCompletionService CompletionService { get; set; } = null!;

    public Stack<string> Arguments { get; private set; } = new();

    public bool IsValid { get; private set; } = true;

    public bool IsCurrentWordValid { get; private set; } = true;

    public CinemaArguments Build()
    {
        Stack<string> arguments = Arguments;
        string cliArgs = string.Join(' ', arguments.Reverse());
        return new CinemaArguments(arguments, cliArgs);
    }

    public void Reset()
    {
        LineRenderer.Clear();
        WordBuilder.Clear();
        Arguments = new Stack<string>();
    }

    public void RemoveLastCharacter()
    {
        LineRenderer.TryRemoveLastCharacterLive();
        if (!WordBuilder.TryRemoveLast())
        {
            string previousArgument = Arguments.Pop();
            WordBuilder.Load(previousArgument);
            IsValid = true; // TODO wtf?
            CompletionService.TryMoveUp();
        }
    }

    public void RemoveLastWord()
    {
        // TODO
    }

    public void CompleteCurrentArgument()
    {
        if (!WordBuilder.IsEmpty)
        {
            string arg = WordBuilder.Flush();
            Arguments.Push(arg);
            bool argIsValid = CompletionService.TryMoveDown(arg);
            IsValid = argIsValid;
        }
    }
}

class CommandCompletionService
{
    private readonly CompletionTreeNode _root;
    private CompletionTreeNode _current;
    private IEnumerator<CompletionItem>? _suggestions;

    private CommandCompletionService(CompletionTreeNode root)
    {
        _root = root;
        _current = root;
    }

    public static CommandCompletionService CreateFor(Command command)
    {
        CompletionTreeNode root = CreateAutoCompleteTree(null, command);
        return new CommandCompletionService(root);
    }

    public bool TryMoveUp()
    {
        if (_current.Parent is not null)
        {
            _current = _current.Parent;
            return true;
        }
        return false;
    }

    public bool TryMoveDown(string childDescriptor)
    {
        _suggestions = null;
        CompletionTreeNode? newNode = _current.GetChildFor(childDescriptor);
        if (newNode is null)
        {
            return false;
        }
        _current = newNode;
        return true;
    }

    public bool TryGetNextSuggestion([NotNullWhen(true)] out string? suggestion)
    {
        if (_suggestions?.MoveNext() is true)
        {
            suggestion = _suggestions.Current.InsertText ?? _suggestions.Current.Label;
            return true;
        }
        suggestion = null;
        return false;
    }

    public void UpdateSuggestions(string word) => 
        _suggestions = _current.GetSuggestions(word);

    public bool HasSuggestions => _suggestions is not null;

    public void ResetSuggestions() => 
        _suggestions = null;

    private static CompletionTreeNode CreateAutoCompleteTree(CompletionTreeNode? parent, Symbol current)
    {
        CompletionTreeNode node = new(current)
        {
            Parent = parent
        };
        if (current is Command command)
        {
            foreach (Symbol child in command.Children)
            {
                CompletionTreeNode childNode = CreateAutoCompleteTree(node, child);
                node.Children.Add(childNode);
            }
        }
        return node;
    }
}

class WordBuilder
{
    private readonly StringBuilder _builder = new();

    public void Append(char c)
    {
        _builder.Append(c);
    }

    public void Append(ConsoleKeyInfo k)
    {
        _builder.Append(k.KeyChar);
    }

    public void Append(string s)
    {
        _builder.Append(s);
    }

    public string Flush()
    {
        string word = _builder.ToString();
        _builder.Clear();
        return word;
    }

    public void Load(string s)
    {
        _builder.Clear();
        _builder.Append(s);
    }

    public bool TryRemoveLast()
    {
        if (_builder.Length > 0)
        {
            _builder.Remove(_builder.Length - 1, 1);
            return true;
        }
        return false;
    }

    public void Clear() => _builder.Clear();

    public bool IsEmpty => _builder.Length <= 0;

    public string Value => _builder.ToString();

    public int Length => _builder.Length;
}

class IOForwarder
{
    private readonly Process _slave;
    private static readonly object _consoleLock = new();

    private IOForwarder(Process slave) => _slave = slave;

    public static void ForwardUntilExits(Process slave)
    {
        IOForwarder self = new(slave);
        ThreadPool.QueueUserWorkItem(_ => self.RedirectStdOut());
        ThreadPool.QueueUserWorkItem(_ => self.ForwardStdIn());
        ThreadPool.QueueUserWorkItem(_ => self.RedirectStdErr());
        slave.WaitForExit();
    }

    private void RedirectStdOut()
    {
        Span<char> buffer = stackalloc char[256];
        StreamReader slaveOutputReader = _slave.StandardOutput;
        while (!_slave.HasExited)
        {
            int bytesRead = 0;
            do
            {
                bytesRead = slaveOutputReader.Read(buffer);
                lock (_consoleLock)
                {
                    Stdout.Write(buffer[..bytesRead].ToString());
                }
            } while (bytesRead > 0);
            Thread.Sleep(10);
        }
    }

    private void RedirectStdErr()
    {
        Span<char> buffer = stackalloc char[256];
        StreamReader slaveErrorReader = _slave.StandardError;
        while (!_slave.HasExited)
        {
            int bytesRead = 0;
            do
            {
                bytesRead = slaveErrorReader.Read(buffer);
                lock (_consoleLock)
                {
                    Stdout.WriteLine(buffer[..bytesRead].ToString(), ConsoleColor.Red);
                }
            } while (bytesRead > 0);
            Thread.Sleep(10);
        }
    }

    private void ForwardStdIn()
    {
        StreamWriter slaveInputWriter = _slave.StandardInput;
        while (!_slave.HasExited)
        {
            if (Console.KeyAvailable)
            {
                ConsoleKeyInfo keyInfo = Console.ReadKey();
                if (keyInfo.Key == ConsoleKey.Enter)
                {
                    Stdout.WriteLine();
                    slaveInputWriter.WriteLine();
                }
                else
                {
                    slaveInputWriter.Write(keyInfo.KeyChar);
                }
            }
            else
            {
                Thread.Sleep(10);
            }
        }
    }
}

class LineRenderer
{
    private readonly StringBuilder _line = new();

    public bool IsValid { get; set; } = true;

    private int _previousContentLength = 0;

    private readonly string _prompt = "> ";

    public void RenderLine()
    {
        if (!IsValid)
        {
            Console.ForegroundColor = ConsoleColor.Red;
        }
        if (_line.Length < _previousContentLength)
        {
            Stdout.Write('\r');
            Stdout.Write(new string(' ', _prompt.Length + _previousContentLength));
        }
        _previousContentLength = _line.Length;
        Stdout.Write('\r');
        Stdout.Write(_prompt);
        Stdout.Write(_line.ToString());
        Stdout.ResetColor();
    }

    public void RemoveCount(int length) => _line.Remove(_line.Length - length, length);

    public void Clear() => _line.Clear();

    public void Load(string s)
    {
        _line.Clear();
        _line.Append(s);
    }

    public void Append(char c) => _line.Append(c);

    public void Append(string s) => _line.Append(s);

    public void Append(ConsoleKeyInfo keyInfo) => _line.Append(keyInfo.KeyChar);

    public bool TryRemoveLastCharacterLive()
    {
        if (_line.Length > 0)
        {
            RemoveCount(1);
            Stdout.Write(' ');
            return true;
        }
        return false;
    }
}

static class Stdout
{
    private static readonly ConsoleColor _defaultBackgound = ConsoleColor.Black;
    private static readonly ConsoleColor _defaultForeground = ConsoleColor.White;

    public static void ResetColor()
    {
        Console.BackgroundColor = _defaultBackgound;
        Console.ForegroundColor = _defaultForeground;
    }

    public static void Write(string s) => Console.Write(s);

    public static void Write(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.Write(s);
        ResetColor();
    }

    public static void Write(char c) => Console.Write(c);

    public static void Write(char c, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.Write(c);
        ResetColor();
    }

    public static void WriteLine(string s) => Console.WriteLine(s);

    public static void WriteLine() => Console.WriteLine();

    public static void WriteLine(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.WriteLine(s);
        ResetColor();
    }
}

record CinemaArguments(Stack<string> Arguments, string Line);