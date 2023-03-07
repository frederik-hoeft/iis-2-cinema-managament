using System.CommandLine;
using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal class OptionCompletionTreeNodeData : ICompletionTreeNodeData
{
    public Option Option { get; }

    public OptionCompletionTreeNodeData(Option option) => Option = option;

    public string Name => string.Empty;

    public IReadOnlyCollection<string> Aliases => Option.Aliases;

    public void AddCompletions(IEnumerable<CompletionItem> completions) => Option.GetCompletions();

    public IEnumerable<CompletionItem> GetCompletions() => Array.Empty<CompletionItem>();

    public bool Matches(string input) => Aliases.Contains(input, default(AliasEqualityComparer));
}