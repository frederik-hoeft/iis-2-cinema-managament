using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics.CodeAnalysis;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

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

    public bool TryGetNextSuggestion([NotNullWhen(true)] out CompletionItem? suggestion)
    {
        if (_suggestions?.MoveNext() is true)
        {
            suggestion = _suggestions.Current;
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

    public void Reset()
    {
        ResetSuggestions();
        _current = _root;
    }

    private static CompletionTreeNode CreateAutoCompleteTree(CompletionTreeNode? parent, Symbol current)
    {
        ICompletionTreeNodeData data = current switch
        {
            IdentifierSymbol identifier => new IdentifierSymbolCompletionTreeNodeData(identifier),
            _ => new SymbolCompletionTreeNodeData(current)
        };
        CompletionTreeNode node = new(data)
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
        ICompletionTreeNodeData helpData = new HelpCompletionTreeNodeData();
        CompletionTreeNode helpNode = new(helpData)
        {
            Parent = node
        };
        node.Children.Add(helpNode);
        node.Data.AddCompletions(helpData.GetCompletions());
        return node;
    }
}
