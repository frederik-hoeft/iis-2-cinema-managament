using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands;

public abstract class CommandBase : ICliCommand
{
    public ApiContext ApiContext { get; }

    protected CommandBase(ApiContext apiContext) => ApiContext = apiContext;

    public abstract Command Build();
}

public abstract class CommandBase<TCommand, THandler> : CommandBase
    where TCommand : CommandBase<TCommand, THandler>
    where THandler : class, ICliHandler<TCommand, THandler>
{
    protected CommandBase(ApiContext apiContext) : base(apiContext)
    {
    }

    protected static Command RegisterHandler(TCommand cliCommand, Command command)
    {
        THandler handler = THandler.Create(cliCommand);
        handler.RegisterOn(command);
        return command;
    }
}
