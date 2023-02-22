namespace IIS.Client.Cli.Commands;

internal abstract class HandlerBase<TCommand> : ICliHandler<TCommand> where TCommand : ICliCommandBuilder
{
    public TCommand Command { get; }

    protected HandlerBase(TCommand command) => Command = command;
}
