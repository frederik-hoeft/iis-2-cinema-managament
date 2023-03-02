using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics.CodeAnalysis;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

public class CommandCompletionService
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
