using IIS.Client.Cli.Commands;
using System.CommandLine;

namespace IIS.Client.Cli.Extensions;

internal static class CommandExtensions
{
    public static Command RegisterSubCommand<T>(this Command root) where T : ICliCommand, new()
    {
        T commandBuilder = new();
        Command command = commandBuilder.Build();
        root.AddCommand(command);
        return root;
    }

    public static Command RegisterSubCommand<T>(this Command root, T commandBuilder) where T : ICliCommand
    {
        Command command = commandBuilder.Build();
        root.AddCommand(command);
        return root;
    }
}
