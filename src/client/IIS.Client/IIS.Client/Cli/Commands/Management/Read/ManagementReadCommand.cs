using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Read;

internal class ManagementReadCommand : ManagementSubCommandBase<ManagementReadCommand, ManagementReadCommandHandler>, IManagementOperationCommand
{
    public ManagementReadCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be listed";

    public override Command Build()
    {
        Command command = new("list", "Lists the specified by <target> instances of the cinema infrastructure.");
        command.AddAlias("-l");
        command.AddAlias("show");
        command.AddAlias("view");
        command.AddAlias("display");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
