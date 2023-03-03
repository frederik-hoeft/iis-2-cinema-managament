using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics.CodeAnalysis;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

public class CommandCompletionService
{
    private readonly CompletionTreeNode _root;
    private CompletionTreeNode? _current;
    private IEnumerator<CompletionItem>? _suggestions;
    private readonly Dictionary<OptionCompletionTreeNodeData, IReadOnlyCollection<string>> _blacklistedGlobalOptions = new();

    private CommandCompletionService(CompletionTreeNode root)
    {
        _root = root;
        _current = root;
        _root.IsAllowed = false;
    }

    public static CommandCompletionService CreateFor(Command command)
    {
        CompletionTreeNode root = CreateAutoCompleteTree(null, command);
        return new CommandCompletionService(root);
    }

    internal bool TryLoadFrame(ArgumentStackFrame stackFrame)
    {
        if (stackFrame.Node is not null)
        {
            if (_current != null)
            {
                _current.IsAllowed = true;
            }
            if (ReferenceEquals(stackFrame.Node.Parent, _current))
            {
                // removing option
                OptionCompletionTreeNodeData nodeData = (OptionCompletionTreeNodeData)stackFrame.Node.Data;
                _blacklistedGlobalOptions.Remove(nodeData);
            }
            _current = stackFrame.Node;
            return true;
        }
        return false;
    }

    internal bool TryMoveDown(string childDescriptor, out CompletionTreeNode? oldNode)
    {
        _suggestions = null;
        if (_current is null)
        {
            oldNode = null;
            return false;
        }
        oldNode = _current;
        CompletionTreeNode? next = _current.GetChildFor(childDescriptor);
        if (!(next?.IsAllowed ?? true))
        {
            return false;
        }
        if (next != null)
        {
            if (ReferenceEquals(next, oldNode.Parent))
            {
                // blacklist option from auto-complete
                OptionCompletionTreeNodeData optionsNode = (OptionCompletionTreeNodeData)oldNode.Data;
                _blacklistedGlobalOptions.Add(optionsNode, optionsNode.Aliases);
            }
            next.IsAllowed = false;
        }
        _current = next;
        return true;
    }

    public bool TryGetNextSuggestion([NotNullWhen(true)] out CompletionItem? suggestion)
    {
        bool hasSuggestion;
        do
        {
            hasSuggestion = _suggestions?.MoveNext() is true;
        } while (hasSuggestion && _blacklistedGlobalOptions.Values
            .Any(blackList => blackList
                .Any(entry =>
                    _suggestions!.Current.InsertText?.Equals(entry) is true
                    || _suggestions.Current.Label.Equals(entry))));

        if (hasSuggestion)
        {
            suggestion = _suggestions!.Current;
            return true;
        }
        suggestion = null;
        return false;
    }

    public void UpdateSuggestions(string word) =>
        _suggestions = _current?.GetSuggestions(word);

    public bool HasSuggestions => _suggestions is not null;

    public void ResetSuggestions() =>
        _suggestions = null;

    public void Reset()
    {
        ResetSuggestions();
        _blacklistedGlobalOptions.Clear();
        ResetNodeStates(_root);
        _current = _root;
    }

    private static void ResetNodeStates(CompletionTreeNode current)
    {
        current.IsAllowed = true;
        foreach (CompletionTreeNode child in current.Children)
        {
            if (!child.Children.Contains(current) || !child.IsAllowed)
            {
                ResetNodeStates(child);
            }
        }
    }

    private static CompletionTreeNode CreateAutoCompleteTree(CompletionTreeNode? parent, Symbol current)
    {
        ICompletionTreeNodeData data = current switch
        {
            Option option => new OptionCompletionTreeNodeData(option),
            IdentifierSymbol identifier => new IdentifierSymbolCompletionTreeNodeData(identifier),
            Argument arg => new ArgumentCompletionTreeNodeData(arg),
            _ => throw new ArgumentException(current.ToString())
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
        if (current is Option or Argument)
        {
            node.Children.Add(parent!);
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
