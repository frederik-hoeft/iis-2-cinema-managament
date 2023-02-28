using IIS.Client.ApiAccess.Operations;
using IIS.Client.Cli.Commands.Admin.Read;
using IIS.Client.Cli.Extensions;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Admin;

internal class AdminCommandHandler : HandlerBase<AdminCommand>, ICliHandler<AdminCommand, AdminCommandHandler>
{
    public AdminCommandHandler(AdminCommand command) : base(command)
    {
    }

    public static AdminCommandHandler Create(AdminCommand command) => new(command);

    public void RegisterOn(Command command)
    {
        ICliCommand read = new AdminReadCommand(Command.ApiContext);
        command.RegisterSubCommand(read);
    }
}
