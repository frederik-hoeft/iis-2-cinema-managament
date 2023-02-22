using System.CommandLine;

namespace IIS.Client.Cli.Commands;

internal class CommandBase<T> where T : ICliHandler
{
    protected static Command RegisterHandler(Command command)
    {
        T.RegisterOn(command);
        return command;
    }
}
