namespace IIS.Client.Cli.Utils;

internal readonly struct InputStringPrompt : IInputPrompt<string>
{
    private readonly string _prompt;

    public InputStringPrompt(string prompt) => _prompt = prompt;

    public string? RequestInput()
    {
        Console.Write(_prompt);
        return Console.ReadLine();
    }
}
