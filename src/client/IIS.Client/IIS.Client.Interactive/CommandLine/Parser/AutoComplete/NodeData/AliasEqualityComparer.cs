using System.Diagnostics.CodeAnalysis;

namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete.NodeData;

internal readonly struct AliasEqualityComparer : IEqualityComparer<string>
{
    public bool Equals(string? x, string? y) =>
        x?.Equals(y, StringComparison.InvariantCultureIgnoreCase) is true;

    public int GetHashCode([DisallowNull] string obj) => obj.GetHashCode();
}