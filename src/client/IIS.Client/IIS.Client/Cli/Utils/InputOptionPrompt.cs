using System.Diagnostics.CodeAnalysis;

namespace IIS.Client.Cli.Utils;

internal readonly struct InputOptionPrompt<TOption> : IInputPrompt<TOption>
{
    public IReadOnlyList<TOption> Options { get; }

    private readonly string _prompt;

    public InputOptionPrompt(IReadOnlyList<TOption> options, string? prompt = null)
    {
        Options = options;
        _prompt = prompt ?? "Select one of the following options:";
    }

    public TOption? RequestInput()
    {
        RenderOptions();
        _ = TryReadSelection(out TOption? selection);
        return selection;
    }

    public bool TryRequestInput([NotNullWhen(true)] out TOption? selection)
    {
        RenderOptions();
        return TryReadSelection(out selection);
    }

    private void RenderOptions()
    {
        Console.WriteLine(_prompt);
        for (int i = 0; i < Options.Count; i++)
        {
            Console.WriteLine($" [{i+1}] {Options[i]}");
        }
        Console.WriteLine();
        Console.WriteLine(" [0] Abort");
    }

    private bool TryReadSelection([NotNullWhen(true)] out TOption? selection)
    {
        while (true)
        {
            Console.Write(Program.SLAVE_PROMPT);
            string? input = Console.ReadLine();
            if (int.TryParse(input, out int i))
            {
                if (i == 0)
                {
                    selection = default;
                    return false;
                }
                else if (i > 0 && i < Options.Count + 1)
                {
                    selection = Options[i - 1]!;
                    return true;
                }
            }
            Console.WriteLine("Please enter a number matching one of the options listed above.");
        }
    }
}
