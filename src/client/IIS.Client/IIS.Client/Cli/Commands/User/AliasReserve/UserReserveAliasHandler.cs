using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.User;
using IIS.Client.ApiAccess.Operations;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.AliasReserve;

internal class UserReserveAliasHandler : UserSubCommandHandlerBase<UserReserveAlias, UserReserveAliasHandler>,
    ICliHandler<UserReserveAlias, UserReserveAliasHandler>
{
    public UserReserveAliasHandler(UserReserveAlias command) : base(command)
    {
    }

    public static UserReserveAliasHandler Create(UserReserveAlias command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity);

    private void Handle(string? userIdentity)
    {
        RemoteOperation remoteOperation = UserReservationOperation.Create(Command.ApiContext, userIdentity).Reserve;
        RemoteApi.Execute(remoteOperation);
    }
}
