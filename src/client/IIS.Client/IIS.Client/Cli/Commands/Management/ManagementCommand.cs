using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Management.Create;
using IIS.Client.Cli.Extensions;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management;

internal class ManagementCommand : CommandBase<ManagementCommand, ManagementCommandHandler>
{
    public ManagementCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    public override Command Build()
    {
        Command command = new("management", "The admin management API to create, update, or delete elements of the cinema infrastructure.");
        return RegisterHandler(this, command);
    }
}
