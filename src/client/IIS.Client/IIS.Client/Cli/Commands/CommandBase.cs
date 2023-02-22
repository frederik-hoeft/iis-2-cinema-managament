using System.CommandLine;

namespace IIS.Client.Cli.Commands;

internal class CommandBase<TCommand, THandler>
    where TCommand : CommandBase<TCommand, THandler>, ICliCommandBuilder
    where THandler : class, ICliHandler<TCommand, THandler>
{
    protected static Command RegisterHandler(TCommand cliCommand, Command command)
    {
        THandler handler = THandler.Create(cliCommand);
        handler.RegisterOn(command);
        return command;
    }
}
