namespace IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

internal static class CharacterExtensions
{
    public static bool IsPrintable(this char c) =>
        char.IsAscii(c) && !char.IsControl(c);
}
