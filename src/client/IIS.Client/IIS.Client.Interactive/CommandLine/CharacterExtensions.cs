namespace IIS.Client.Interactive.CommandLine;

internal static class CharacterExtensions
{
    public static bool IsAsciiSymbol(this char c) =>
        (int)c is > 0x20 and < 0x30;
}
