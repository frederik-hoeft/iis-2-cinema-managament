using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.User;
using IIS.Client.ApiAccess.Operations;
using System.CommandLine;
using IIS.Client.Cli.Commands.User.AliasCancel;

namespace IIS.Client.Cli.Commands.User.CancelAlias;

internal class UserCancelAliasHandler : UserSubCommandHandlerBase<UserCancelAlias, UserCancelAliasHandler>,
    ICliHandler<UserCancelAlias, UserCancelAliasHandler>
{
    public UserCancelAliasHandler(UserCancelAlias command) : base(command)
    {
    }

    public static UserCancelAliasHandler Create(UserCancelAlias command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity);

    private void Handle(string? userIdentity)
    {
        RemoteOperation remoteOperation = UserReservationOperation.Create(Command.ApiContext, userIdentity).Cancel;
        RemoteApi.Execute(remoteOperation);
    }
}
