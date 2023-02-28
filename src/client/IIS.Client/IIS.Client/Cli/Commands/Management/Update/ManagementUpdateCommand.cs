using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Update;

internal class ManagementUpdateCommand : ManagementSubCommandBase<ManagementUpdateCommand, ManagementUpdateCommandHandler>, IManagementOperationCommand
{
    public ManagementUpdateCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be updated";

    public override Command Build()
    {
        // TODO:
        Command command = new("update", "TODO");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
