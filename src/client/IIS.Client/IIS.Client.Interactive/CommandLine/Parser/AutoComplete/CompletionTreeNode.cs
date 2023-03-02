using System.Collections.Immutable;
using System.CommandLine.Completions;
using System.Diagnostics;
using System.Diagnostics.CodeAnalysis;
using IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

[DebuggerDisplay("{Data.Name}")]
internal class CompletionTreeNode
{
    public ICompletionTreeNodeData Data { get; }

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
            if (candidates.Any())
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
            foreach (CompletionTreeNode child in Children)
            {
                if (child.Data.Aliases.Contains(input, default(AliasEqualityComparer)))
                {
                    return child;
                }
            }
        }
        return null;
    }
}

file readonly struct AliasEqualityComparer : IEqualityComparer<string>
{
    public bool Equals(string? x, string? y) => 
        x?.Equals(y, StringComparison.InvariantCultureIgnoreCase) is true;

    public int GetHashCode([DisallowNull] string obj) => obj.GetHashCode();
}