using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Admin;

internal class AdminCommand : CommandBase<AdminCommand, AdminCommandHandler>, ICliCommand
{
    public AdminCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    public override Command Build()
    {
        Command command = new("admin", "The admin API to perform operational tasks like inspecting revenue.");
        return RegisterHandler(this, command);
    }
}
