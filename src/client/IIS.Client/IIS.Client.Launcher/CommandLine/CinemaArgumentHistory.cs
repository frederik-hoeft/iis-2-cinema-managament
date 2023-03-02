using IIS.Client.Interactive.CommandLine.Parser;
using System.Diagnostics.CodeAnalysis;

namespace IIS.Client.Interactive.CommandLine;

internal class CinemaArgumentHistory
{
    private readonly List<CinemaArguments> _history = new();
    private int _historyIndex = -1;

    public void Add(CinemaArguments historyEntry)
    {
        _history.Insert(0, historyEntry);
        _historyIndex = -1;
    }

    public bool TryGetPrevious([NotNullWhen(true)] out CinemaArguments? historyEntry)
    {
        int previousIndex = _historyIndex + 1;
        if (previousIndex < _history.Count)
        {
            historyEntry = _history[previousIndex];
            _historyIndex = previousIndex;
            return true;
        }
        historyEntry = null;
        return false;
    }

    public bool TryGetNext([NotNullWhen(true)] out CinemaArguments? historyEntry)
    {
        int nextIndex = _historyIndex - 1;
        if (nextIndex >= 0)
        {
            historyEntry = _history[nextIndex];
            _historyIndex = nextIndex;
            return true;
        }
        historyEntry = null;
        return false;
    }

    public void ResetEnumerator() => _historyIndex = -1;
}