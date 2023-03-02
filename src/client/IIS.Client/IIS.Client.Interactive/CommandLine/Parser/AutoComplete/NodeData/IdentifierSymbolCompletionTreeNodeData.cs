using System.CommandLine;
using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal class IdentifierSymbolCompletionTreeNodeData : ICompletionTreeNodeData
{
    private readonly IdentifierSymbol _identifierSymbol;

    private readonly List<CompletionItem> _additionalCompletionItems = new();

    public IdentifierSymbolCompletionTreeNodeData(IdentifierSymbol identifierSymbol)
    {
        _identifierSymbol = identifierSymbol;
    }

    public IReadOnlyCollection<string> Aliases => _identifierSymbol.Aliases;

    public string Name => _identifierSymbol.Name;

    public void AddCompletions(IEnumerable<CompletionItem> completions) =>
        _additionalCompletionItems.AddRange(completions);

    public IEnumerable<CompletionItem> GetCompletions() =>
        _identifierSymbol.GetCompletions().Concat(_additionalCompletionItems);
}
