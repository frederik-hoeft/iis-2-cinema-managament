using System.Diagnostics.SymbolStore;
using System.Text;

namespace IIS.Client.Interactive.CommandLine;

internal class LineRenderer
{
    private readonly StringBuilder _line = new();

    public bool IsValid { get; set; } = true;

    private int _previousContentLength = 0;

    private readonly string _prompt;

    public LineRenderer(string prompt) => _prompt = prompt;

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
}
