using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.Commands.User.Booking;
using IIS.Client.Cli.Extensions;
using System;
using System.Collections.Generic;
using System.CommandLine;
using System.CommandLine.Invocation;
using System.CommandLine.NamingConventionBinder;
using System.CommandLine.Parsing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Cli.Commands.User;

internal class UserCommandHandler : HandlerBase<UserCommand>, ICliHandler<UserCommand, UserCommandHandler>
{
    private UserCommandHandler(UserCommand command) : base(command)
    {
    }

    public static UserCommandHandler Create(UserCommand command) => new(command);

    public void RegisterOn(Command command)
    {
        UserBookingCommand childCommand = new(Command.UserIdentity);
        command.Register(childCommand);
    }
}