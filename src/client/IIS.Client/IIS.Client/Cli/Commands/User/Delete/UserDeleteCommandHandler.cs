using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.User;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.Cli.Commands.User.Create;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Delete;

using static CommonOperationProvider;

internal class UserDeleteCommandHandler : UserSubCommandHandlerBase<UserDeleteCommand, UserDeleteCommandHandler>,
    ICliHandler<UserDeleteCommand, UserDeleteCommandHandler>
{
    private UserDeleteCommandHandler(UserDeleteCommand command) : base(command)
    {
    }

    public static UserDeleteCommandHandler Create(UserDeleteCommand command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Target);

    private void Handle(string? userIdentity, UserDeleteCommandTarget target)
    {
        ApiContext apiContext = Command.ApiContext;
        RemoteOperation remoteOperation = target switch
        {
            UserDeleteCommandTarget.User => UserAccountOperation.Create(apiContext, userIdentity).Delete,
            UserDeleteCommandTarget.Reservation => UserReservationOperation.Create(apiContext, userIdentity).Cancel,
            _ => Fail($"Unable to handle deletion target '{target}'.")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
