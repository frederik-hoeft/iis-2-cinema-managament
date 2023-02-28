namespace IIS.Client.Cli.IO;

internal static class Stdout
{
    public static void WriteLine(string s) => Console.WriteLine(s);

    public static void Write(string s) => Console.WriteLine(s);

    public static void WriteLine(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.WriteLine(s);
        Console.ResetColor();
    }

    public static void Write(string s, ConsoleColor color)
    {
        Console.ForegroundColor = color;
        Console.WriteLine(s);
        Console.ResetColor();
    }
}
