namespace IIS.Client.Cli.Commands.User;

public abstract class UserSubCommandHandlerBase<TCommand, THandler> : HandlerBase<TCommand>
    where TCommand : UserSubCommandBase<TCommand, THandler>
    where THandler : UserSubCommandHandlerBase<TCommand, THandler>, ICliHandler<TCommand, THandler>
{
    public UserSubCommandHandlerBase(TCommand command) : base(command)
    {
    }
}
