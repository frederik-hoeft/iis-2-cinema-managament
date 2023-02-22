using System.CommandLine;
using System.CommandLine.NamingConventionBinder;
using IIS.Client.Cli.Commands.Management;

namespace IIS.Client.Cli.Commands.User.Booking;

internal class UserBookingCommandHandler : ICliHandler
{
    public static void RegisterOn(Command command)
    {
        command.Handler = CommandHandler.Create<ManagementTarget>(Handle);
    }

    private static void Handle(ManagementTarget target)
    {
        Console.WriteLine(target);
    }
}