using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User;

public abstract class UserSubCommandBase<TCommand, THandler> : CommandBase<TCommand, THandler>
    where TCommand : UserSubCommandBase<TCommand, THandler>
    where THandler : UserSubCommandHandlerBase<TCommand, THandler>, ICliHandler<TCommand, THandler>
{
    public Option<string?> UserIdentity { get; }

    public UserSubCommandBase(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext)
    {
        UserIdentity = userIdentity;
    }
}
