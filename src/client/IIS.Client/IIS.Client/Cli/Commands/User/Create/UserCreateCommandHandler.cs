using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.User;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Create;

using static CommonOperationProvider;

internal class UserCreateCommandHandler : UserSubCommandHandlerBase<UserCreateCommand, UserCreateCommandHandler>,
    ICliHandler<UserCreateCommand, UserCreateCommandHandler>
{
    private UserCreateCommandHandler(UserCreateCommand command) : base(command)
    {
    }

    public static UserCreateCommandHandler Create(UserCreateCommand command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Target);

    private void Handle(string? userIdentity, UserCreateCommandTarget target)
    {
        ApiContext apiContext = Command.ApiContext;
        RemoteOperation remoteOperation = target switch
        {
            UserCreateCommandTarget.User => UserAccountOperation.Create(apiContext, userIdentity).Register,
            UserCreateCommandTarget.Reservation => UserReservationOperation.Create(apiContext, userIdentity).Reserve,
            UserCreateCommandTarget.Booking => UserBookingOperation.Create(apiContext, userIdentity).Book,
            _ => Fail($"Unable to handle creation target '{target}'.")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
