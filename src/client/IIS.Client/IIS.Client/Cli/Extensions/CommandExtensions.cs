using IIS.Client.Cli.Commands;
using System.CommandLine;

namespace IIS.Client.Cli.Extensions;

internal static class CommandExtensions
{
    public static Command Register<T>(this Command root) where T : ICliCommand
    {
        Command command = T.CreateCommand();
        root.AddCommand(command);
        return root;
    }
}
