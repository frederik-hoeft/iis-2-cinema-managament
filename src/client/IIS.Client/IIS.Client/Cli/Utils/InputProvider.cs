using System.Text.RegularExpressions;

namespace IIS.Client.Cli.Utils;

internal static class InputProvider
{
    public static string? RequestValueFor(string name)
    {
        InputStringPrompt prompt = new($"{name}: ");
        return prompt.RequestInput();
    }
}
