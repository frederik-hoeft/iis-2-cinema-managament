using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Booking;

internal class UserBookingCommandHandler : HandlerBase<UserBookingCommand>, ICliHandler<UserBookingCommand, UserBookingCommandHandler>
{
    private UserBookingCommandHandler(UserBookingCommand command) : base(command)
    {
    }

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Operation);

    private void Handle(string? userIdentity, UserBookingOperation bookingOperation)
    {
        Console.WriteLine((userIdentity, bookingOperation));
    }

    public static UserBookingCommandHandler Create(UserBookingCommand command) => new(command);
}