using IIS.Client.Cli.Commands;
using System.CommandLine;

namespace IIS.Client.Cli.Extensions;

internal static class CommandExtensions
{
    public static Command Register<T>(this Command root) where T : ICliCommandBuilder, new()
    {
        T commandBuilder = new();
        Command command = commandBuilder.Build();
        root.AddCommand(command);
        return root;
    }

    public static Command Register<T>(this Command root, T commandBuilder) where T : ICliCommandBuilder
    {
        Command command = commandBuilder.Build();
        root.AddCommand(command);
        return root;
    }
}
