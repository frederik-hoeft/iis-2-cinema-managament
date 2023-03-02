using System.CommandLine.Completions;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal interface ICompletionTreeNodeData
{
    string Name { get; }

    IReadOnlyCollection<string> Aliases { get; }

    IEnumerable<CompletionItem> GetCompletions();

    public void AddCompletions(IEnumerable<CompletionItem> completions);
}
