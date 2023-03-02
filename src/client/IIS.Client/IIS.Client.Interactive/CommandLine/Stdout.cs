namespace IIS.Client.Interactive.CommandLine;

internal static class Stdout
{
    private static readonly ConsoleColor _defaultBackgound = ConsoleColor.Black;
    private static readonly ConsoleColor _defaultForeground = ConsoleColor.White;

    public static void ResetColor()
    {
        Console.BackgroundColor = _defaultBackgound;
        Console.ForegroundColor = _defaultForeground;
    }

    public static void Write(string s) => Console.Write(s);

    public static void Write(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.Write(s);
        ResetColor();
    }

    public static void Write(char c) => Console.Write(c);

    public static void Write(char c, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.Write(c);
        ResetColor();
    }

    public static void WriteLine(string s) => Console.WriteLine(s);

    public static void WriteLine() => Console.WriteLine();

    public static void WriteLine(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.WriteLine(s);
        ResetColor();
    }
}
