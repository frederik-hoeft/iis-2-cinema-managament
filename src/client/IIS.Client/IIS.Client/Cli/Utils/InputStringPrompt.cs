namespace IIS.Client.Cli.Utils;

internal readonly struct InputStringPrompt : IInputPrompt<string>
{
    private readonly string _prompt;

    public InputStringPrompt(string prompt) => _prompt = prompt;

    public string? RequestInput()
    {
        Console.WriteLine(_prompt);
        Console.Write(Program.SLAVE_PROMPT);
        return Console.ReadLine();
    }
}
