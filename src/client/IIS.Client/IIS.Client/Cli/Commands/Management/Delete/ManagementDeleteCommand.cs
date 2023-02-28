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
        Command command = new("delete", "Deletes the specified <target> from the cinema infrastructure.");
        command.AddAlias("-d");
        command.AddAlias("remove");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
