using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.User;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Booking;

using static CommonOperationProvider;

internal class UserBookingCommandHandler : HandlerBase<UserBookingCommand>, ICliHandler<UserBookingCommand, UserBookingCommandHandler>
{
    private UserBookingCommandHandler(UserBookingCommand command) : base(command)
    {
    }

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Operation);

    private void Handle(string? userIdentity, UserBookingOperation bookingOperation)
    {
        BookingOperation operation = new(userIdentity);
        RemoteOperation remoteOperation = bookingOperation switch
        {
            UserBookingOperation.New => operation.Book,
            UserBookingOperation.Reserve => operation.Reserve,
            UserBookingOperation.Cancel => operation.Cancel,
            UserBookingOperation.Upgrade => operation.Upgrade,
            UserBookingOperation.Show => operation.ShowAll,
            _ => Fail($"Undefined booking operation: '{bookingOperation}'")
        };
        RemoteApi.Execute(remoteOperation);
    }

    public static UserBookingCommandHandler Create(UserBookingCommand command) => new(command);
}