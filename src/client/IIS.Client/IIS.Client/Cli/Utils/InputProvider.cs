using System.Text.RegularExpressions;

namespace IIS.Client.Cli.Utils;

internal static class InputProvider
{
    public static string? RequestValueFor(string name, string? prompt = null)
    {
        InputStringPrompt inputPrompt = new(prompt ?? $"{name}: ");
        return inputPrompt.RequestInput();
    }

    public static string? RequestValueFor(string name, Regex regex, string? prompt = null)
    {
        string? input;
        do
        {
            InputStringPrompt inputPrompt = new(prompt ?? $"{name} (must match {regex}): ");
            input = inputPrompt.RequestInput();

        } while (input is null || !regex.IsMatch(input));
        
        return input;
    }
}
