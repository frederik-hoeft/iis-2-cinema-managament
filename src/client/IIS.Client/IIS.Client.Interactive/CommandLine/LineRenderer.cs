using System.Diagnostics.SymbolStore;
using System.Text;
using System.Text.RegularExpressions;

namespace IIS.Client.Interactive.CommandLine;

using static AnsiColors;

internal partial class LineRenderer
{
    private readonly StringBuilder _line = new();

    public bool IsValid { get; set; } = true;

    private int _previousContentLength = 0;

    private readonly string _prompt;
    private readonly string? _contentPrefix;
    private readonly string _invalidColor;
    private readonly string _contentColor;
    private readonly int _promptLength;

    public LineRenderer(string prompt, string? contentPrefix = null, string invalidColor = LIGHT_RED, string contentColor = WHITE)
    {
        _prompt = prompt;
        _contentPrefix = contentPrefix;
        _invalidColor = invalidColor;
        _contentColor = contentColor;
        _promptLength = GetRenderedTextLength(prompt + (_contentPrefix ?? string.Empty));
    }

    public void RenderPrompt()
    {
        Stdout.ResetColor();
        if (_line.Length < _previousContentLength)
        {
            Stdout.Write('\r');
            Stdout.Write(new string(' ', _promptLength + _previousContentLength));
        }
        string line = _line.ToString();
        _previousContentLength = GetRenderedTextLength(line);
        Stdout.Write('\r');
        RenderInternal(_prompt);
        string color = IsValid ? _contentColor : _invalidColor;
        RenderInternal($"{color}{_contentPrefix}{line}");
        Stdout.ResetColor();
    }

    public void RenderLine(string text, bool resetColor = true)
    {
        RenderInternal(text);
        Stdout.WriteLine();
        if (resetColor)
        {
            Stdout.ResetColor();
        }
    }

    public void Render(string text, bool resetColor = true)
    {
        RenderInternal(text);
        if (resetColor)
        {
            Stdout.ResetColor();
        }
    }

    public void RemoveCount(int length)
    {
        if (length <= 0)
        {
            return;
        }
        if (_line.Length >= length)
        {
            _line.Remove(_line.Length - length, length);
        }
        else
        {
            _line.Clear();
        }
    }

    public bool TryRemoveCount(int length)
    {
        if (_line.Length >= length)
        {
            _line.Remove(_line.Length - length, length);
            return true;
        }
        return false;
    }

    public void Clear() => _line.Clear();

    public void Load(string s)
    {
        _line.Clear();
        _line.Append(s);
    }

    public void Append(char c) => _line.Append(c);

    public void Append(string s) => _line.Append(s);

    public void Append(ConsoleKeyInfo keyInfo) => _line.Append(keyInfo.KeyChar);

    public bool TryRemoveLastCharacter()
    {
        if (_line.Length > 0)
        {
            RemoveCount(1);
            return true;
        }
        return false;
    }

    private static readonly Dictionary<string, ConsoleColor> _ansiToConsoleColor = new Dictionary<string, ConsoleColor>()
    {
        { BLACK, ConsoleColor.Black },
        { RED, ConsoleColor.DarkRed },
        { GREEN, ConsoleColor.DarkGreen },
        { YELLOW, ConsoleColor.DarkYellow },
        { BLUE, ConsoleColor.DarkBlue },
        { CYAN, ConsoleColor.DarkCyan },
        { MAGENTA, ConsoleColor.DarkMagenta },
        { WHITE, ConsoleColor.Gray },
        { LIGHT_BLACK, ConsoleColor.DarkGray },
        { LIGHT_RED, ConsoleColor.Red },
        { LIGHT_GREEN, ConsoleColor.Green },
        { LIGHT_YELLOW, ConsoleColor.Yellow },
        { LIGHT_BLUE, ConsoleColor.Blue },
        { LIGHT_CYAN, ConsoleColor.Cyan },
        { LIGHT_MAGENTA, ConsoleColor.Magenta },
        { LIGHT_WHITE, ConsoleColor.White },
    };

    private static void RenderInternal(string input)
    {
        Regex ansiColors = AnsiColorRegex();
        MatchCollection matches = ansiColors.Matches(input);
        if (matches.Count > 0)
        {
            int lastIndex = 0;
            foreach (Match match in matches.Cast<Match>())
            {
                string text = input[lastIndex..match.Index];
                Console.Write(text);
                lastIndex = match.Index + match.Length;
                string ansiColor = match.Value;
                if (_ansiToConsoleColor.TryGetValue(ansiColor, out ConsoleColor color))
                {
                    Console.ForegroundColor = color;
                }
                else
                {
                    Stdout.ResetColor();
                }
            }
            Console.Write(input[lastIndex..]);
        }
        else
        {
            Console.Write(input);
        }
    }

    private static int GetRenderedTextLength(string text)
    {
        Regex ansiColors = AnsiColorRegex();
        return ansiColors.Replace(text, string.Empty).Length;
    }

    [GeneratedRegex("\\\\e\\[0(;[39][0-7])??m")]
    private static partial Regex AnsiColorRegex();
}

public static class AnsiColors
{
    public const string BLACK = "\\e[0;30m";
    public const string RED = "\\e[0;31m";
    public const string GREEN = "\\e[0;32m";
    public const string YELLOW = "\\e[0;33m";
    public const string BLUE = "\\e[0;34m";
    public const string MAGENTA = "\\e[0;35m";
    public const string CYAN = "\\e[0;36m";
    public const string WHITE = "\\e[0;37m";
    public const string LIGHT_BLACK = "\\e[0;90m";
    public const string LIGHT_RED = "\\e[0;91m";
    public const string LIGHT_GREEN = "\\e[0;92m";
    public const string LIGHT_YELLOW = "\\e[0;93m";
    public const string LIGHT_BLUE = "\\e[0;94m";
    public const string LIGHT_MAGENTA = "\\e[0;95m";
    public const string LIGHT_CYAN = "\\e[0;96m";
    public const string LIGHT_WHITE = "\\e[0;97m";
    public const string RESET = "\\e[0m";
}