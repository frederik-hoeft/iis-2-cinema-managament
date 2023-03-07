using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal class HelpCompletionTreeNodeData : ICompletionTreeNodeData
{
    private static readonly List<string> _aliases = new()
    {
        "help",
        "--help",
        "-h",
        "-?"
    };

    private readonly List<CompletionItem> _completionItems = new()
    {
        new CompletionItem("help", sortText: "help", insertText: "--help"),
    };

    public IReadOnlyCollection<string> Aliases => _aliases;

    public string Name => "help";

    public void AddCompletions(IEnumerable<CompletionItem> completions) =>
        _completionItems.AddRange(completions);

    public IEnumerable<CompletionItem> GetCompletions() =>
        _completionItems;

    public bool Matches(string input) => Aliases.Contains(input, default(AliasEqualityComparer));
}
