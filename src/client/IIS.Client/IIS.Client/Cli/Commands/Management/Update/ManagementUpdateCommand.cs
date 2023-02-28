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
        Command command = new("update", "Updates one or more properties of a single instance of the specified <target> component in the cinema infrastructure.");
        command.AddAlias("-u");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
