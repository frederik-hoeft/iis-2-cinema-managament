using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Booking;

internal class UserBookingCommand : CommandBase<UserBookingCommandHandler>, ICliCommand
{
    static Command ICliCommand.CreateCommand()
    {
        // TODO...
        Command command = new("user", "")
        {
        };
        return RegisterHandler(command);
    }
}
