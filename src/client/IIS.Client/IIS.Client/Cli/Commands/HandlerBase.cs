namespace IIS.Client.Cli.Commands;

public abstract class HandlerBase<TCommand> : ICliHandler<TCommand> where TCommand : ICliCommand
{
    public TCommand Command { get; }

    protected HandlerBase(TCommand command) => Command = command;
}
