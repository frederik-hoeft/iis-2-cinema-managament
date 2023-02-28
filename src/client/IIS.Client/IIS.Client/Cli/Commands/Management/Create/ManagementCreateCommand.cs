using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Create;

internal class ManagementCreateCommand : ManagementSubCommandBase<ManagementCreateCommand, ManagementCreateCommandHandler>, IManagementOperationCommand
{
    public ManagementCreateCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be created";

    public override Command Build()
    {
        // TODO
        Command command = new("create", "TODO");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
