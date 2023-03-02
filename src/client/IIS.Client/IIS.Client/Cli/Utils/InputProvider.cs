using System.Text.RegularExpressions;

namespace IIS.Client.Cli.Utils;

internal static partial class InputProvider
{
    public static string? RequestStringFor(string name, string? prompt = null)
    {
        InputStringPrompt inputPrompt = new(prompt ?? $"{name}: ");
        return inputPrompt.RequestInput();
    }

    public static string? RequestStringForOrEmpty(string name, Regex regex, string? prompt = null)
    {
        string? input;
        do
        {
            InputStringPrompt inputPrompt = new(prompt ?? $"{name} (must match {regex}): ");
            input = inputPrompt.RequestInput();
        } while (!string.IsNullOrEmpty(input) && !regex.IsMatch(input?.Trim() ?? string.Empty));

        return input;
    }

    public static bool RequestBoolFor(string name, bool std)
    {
        string? input = RequestStringForOrEmpty(string.Empty, BoolRegex(), $"{name} (must be one of 'true, false, yes, no, 1, 0') [{std}]");
        return string.IsNullOrEmpty(input) 
            ? std 
            : input.ToLower() is "true" or "yes" or "1";
    }

    [GeneratedRegex("^(true|false|yes|no|1|0)$", RegexOptions.IgnoreCase)]
    private static partial Regex BoolRegex();
}
