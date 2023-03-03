using System.CommandLine;
using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal class ArgumentCompletionTreeNodeData : ICompletionTreeNodeData
{
    private readonly Argument _symbol;
    private readonly IReadOnlyCollection<string> _aliases;

    private readonly List<CompletionItem> _additionalCompletionItems = new();

    public ArgumentCompletionTreeNodeData(Argument symbol)
    {
        _symbol = symbol;
        _aliases = new List<string>()
        {
            symbol.Name
        };
    }

    public IReadOnlyCollection<string> Aliases => _aliases;

    public string Name => _symbol.Name;

    public void AddCompletions(IEnumerable<CompletionItem> completions) =>
        _additionalCompletionItems.AddRange(completions);

    public IEnumerable<CompletionItem> GetCompletions() =>
        _symbol.GetCompletions().Concat(_additionalCompletionItems);

    public bool Matches(string input) => Aliases.Contains(input, default(AliasEqualityComparer));
}
