using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management;

internal class ManagementCommand : CommandBase<ManagementCommand, ManagementCommandHandler>
{
    public ManagementCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    public override Command Build()
    {
        Command command = new("management", "Accesses the admin management API to create, update, or delete elements in the cinema infrastructure.");
        return RegisterHandler(this, command);
    }
}
