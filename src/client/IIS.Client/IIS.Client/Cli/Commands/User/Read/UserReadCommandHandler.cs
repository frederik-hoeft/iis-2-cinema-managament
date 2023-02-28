using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.User;
using IIS.Client.ApiAccess.Operations;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Read;

using static CommonOperationProvider;

internal class UserReadCommandHandler : UserSubCommandHandlerBase<UserReadCommand, UserReadCommandHandler>,
    ICliHandler<UserReadCommand, UserReadCommandHandler>
{
    private UserReadCommandHandler(UserReadCommand command) : base(command)
    {
    }

    public static UserReadCommandHandler Create(UserReadCommand command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Target);

    private void Handle(string? userIdentity, UserReadCommandTarget target)
    {
        ApiContext apiContext = Command.ApiContext;
        RemoteOperation remoteOperation = target switch
        {
            UserReadCommandTarget.Users => UserAccountOperation.Create(apiContext, userIdentity).Show,
            UserReadCommandTarget.Reservations => UserReservationOperation.Create(apiContext, userIdentity).ShowAll,
            UserReadCommandTarget.Bookings => UserBookingOperation.Create(apiContext, userIdentity).ShowAll,
            _ => Fail($"Unable to handle read target '{target}'.")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
