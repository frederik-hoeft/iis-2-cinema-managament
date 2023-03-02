using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

namespace IIS.Client.Interactive.CommandLine.Parser;

internal class ArgumentBuilder
{
    public LineRenderer LineRenderer { get; } = new("# ");

    public WordBuilder WordBuilder { get; } = new();

    public CommandCompletionService CompletionService { get; set; } = null!;

    public Stack<string> Arguments { get; private set; } = new();

    public bool IsValid { get; private set; } = true;

    public bool IsCurrentWordValid { get; private set; } = true;

    public CinemaArguments Build()
    {
        Stack<string> arguments = Arguments;
        string cliArgs = string.Join(' ', arguments.Reverse());
        return new CinemaArguments(arguments, cliArgs);
    }

    public void Reset()
    {
        LineRenderer.Clear();
        WordBuilder.Clear();
        Arguments = new Stack<string>();
    }

    public void RemoveLastCharacter()
    {
        LineRenderer.TryRemoveLastCharacter();
        if (!WordBuilder.TryRemoveLast() && Arguments.TryPop(out string? previousArgument))
        {
            WordBuilder.Load(previousArgument);
            IsValid = true; // TODO wtf?
            CompletionService.TryMoveUp();
        }
    }

    public void RemoveLastWord()
    {
        // TODO
    }

    public void CompleteCurrentArgument()
    {
        if (!WordBuilder.IsEmpty)
        {
            string arg = WordBuilder.Flush();
            Arguments.Push(arg);
            bool argIsValid = CompletionService.TryMoveDown(arg);
            IsValid = argIsValid;
        }
    }
}
