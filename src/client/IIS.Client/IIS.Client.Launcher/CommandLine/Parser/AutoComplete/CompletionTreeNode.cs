using System.Collections.Immutable;
using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

[DebuggerDisplay("{Symbol.Name}")]
internal class CompletionTreeNode
{
    public Symbol Symbol { get; }

    public List<CompletionItem> Completions { get; }

    public CompletionTreeNode(Symbol data)
    {
        Symbol = data;
        Completions = data.GetCompletions().ToList();
    }

    public CompletionTreeNode? Parent { get; set; }

    public List<CompletionTreeNode> Children { get; } = new();

    public IEnumerator<CompletionItem>? GetSuggestions(string input)
    {
        if (input is not null)
        {
            input = input.Trim();
            IImmutableList<CompletionItem> candidates = Completions.Where(d => d.Label.StartsWith(input, StringComparison.InvariantCultureIgnoreCase)).ToImmutableList();
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
                if (child.Symbol is Command command && command.Aliases.Any(a => a.Equals(input, StringComparison.InvariantCultureIgnoreCase)))
                {
                    return child;
                }
                if (child.Symbol.Name.Equals(input, StringComparison.InvariantCultureIgnoreCase))
                {
                    return child;
                }
            }
        }
        return null;
    }
}
