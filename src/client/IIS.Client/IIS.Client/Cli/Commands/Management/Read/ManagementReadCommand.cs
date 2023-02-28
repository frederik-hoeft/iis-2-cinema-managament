using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Read;

internal class ManagementReadCommand : ManagementSubCommandBase<ManagementReadCommand, ManagementReadCommandHandler>, IManagementOperationCommand
{
    public ManagementReadCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be read";

    public override Command Build()
    {
        // TODO:
        Command command = new("read", "TODO");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
