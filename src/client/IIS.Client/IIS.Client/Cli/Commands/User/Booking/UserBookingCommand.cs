using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Booking;

internal class UserBookingCommand : CommandBase<UserBookingCommand, UserBookingCommandHandler>, ICliCommandBuilder
{
    public Option<string?> UserIdentity { get; }

    public Argument<UserBookingOperation> Operation { get; private set; } = null!;

    public UserBookingCommand(Option<string?> userIdentity) => UserIdentity = userIdentity;

    public Command Build()
    {
        Command command = new("booking", "perform a booking operation.");
        Operation = new Argument<UserBookingOperation>("operation", () => UserBookingOperation.Show, "the booking operation to be performed.");
        command.AddArgument(Operation);
        return RegisterHandler(this, command);
    }
}
