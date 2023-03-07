using System.Collections.Immutable;
using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

[DebuggerDisplay("{Data.Name}")]
internal class CompletionTreeNode
{
    public ICompletionTreeNodeData Data { get; }

    internal bool IsAllowed { get; set; } = true;

    public CompletionTreeNode(ICompletionTreeNodeData data)
    {
        Data = data;
    }

    public CompletionTreeNode? Parent { get; set; }

    public List<CompletionTreeNode> Children { get; } = new();

    public IEnumerator<CompletionItem>? GetSuggestions(string input)
    {
        if (input is not null)
        {
            input = input.Trim();
            IImmutableList<CompletionItem> candidates = Data
                .GetCompletions()
                .Where(c => c.Label.StartsWith(input, StringComparison.InvariantCultureIgnoreCase)
                    || c.InsertText?.StartsWith(input, StringComparison.InvariantCultureIgnoreCase) is true)
                .ToImmutableList();
            if (candidates.Any() || Data is OptionCompletionTreeNodeData)
            {
                return candidates.GetEnumerator();
            }
        }
        return null;
    }

    public CompletionTreeNode? GetChildFor(string input)
    {
        if (!string.IsNullOrEmpty(input))
        {
            if (Data is OptionCompletionTreeNodeData)
            {
                CompletionTreeNode parent = Children.Single(c => c.Data is not HelpCompletionTreeNodeData);
                parent.IsAllowed = true;
                return parent;
            }
            if (Data is ArgumentCompletionTreeNodeData)
            {
                CompletionTreeNode parent = Children.Single(c => c.Data is not HelpCompletionTreeNodeData);
                parent.IsAllowed = true;
                return parent;
            }
            foreach (CompletionTreeNode child in Children)
            {
                if (child.Data.Matches(input))
                {
                    return child;
                }
            }
        }
        return null;
    }
}