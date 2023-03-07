using System.Text;

namespace IIS.Client.Interactive.CommandLine.Parser;

internal class WordBuilder
{
    private readonly StringBuilder _builder = new();

    public void Append(char c) => 
        _builder.Append(c);

    public void Append(ConsoleKeyInfo k) => 
        _builder.Append(k.KeyChar);

    public void Append(string s) => 
        _builder.Append(s);

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
