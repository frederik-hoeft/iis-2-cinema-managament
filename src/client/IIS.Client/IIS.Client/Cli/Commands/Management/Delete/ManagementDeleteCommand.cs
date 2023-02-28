using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Delete;

internal class ManagementDeleteCommand : ManagementSubCommandBase<ManagementDeleteCommand, ManagementDeleteCommandHandler>, IManagementOperationCommand
{
    public ManagementDeleteCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be deleted";

    public override Command Build()
    {
        // TODO
        Command command = new("delete", "TODO");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
