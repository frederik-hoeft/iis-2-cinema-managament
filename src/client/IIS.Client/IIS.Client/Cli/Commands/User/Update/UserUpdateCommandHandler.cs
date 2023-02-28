using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.User;
using IIS.Client.ApiAccess.Operations;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Upgrade;

using static CommonOperationProvider;

internal class UserUpdateCommandHandler : UserSubCommandHandlerBase<UserUpdateCommand, UserUpdateCommandHandler>,
    ICliHandler<UserUpdateCommand, UserUpdateCommandHandler>
{
    private UserUpdateCommandHandler(UserUpdateCommand command) : base(command)
    {
    }

    public static UserUpdateCommandHandler Create(UserUpdateCommand command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Target);

    private void Handle(string? userIdentity, UserUpdateCommandTarget target)
    {
        ApiContext apiContext = Command.ApiContext;
        RemoteOperation remoteOperation = target switch
        {
            UserUpdateCommandTarget.Reservation => UserReservationOperation.Create(apiContext, userIdentity).Upgrade,
            _ => Fail($"Unable to handle update target '{target}'.")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
