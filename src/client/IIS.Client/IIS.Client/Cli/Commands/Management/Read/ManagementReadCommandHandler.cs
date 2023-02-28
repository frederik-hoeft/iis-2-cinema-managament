using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Management;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Read;

internal class ManagementReadCommandHandler : ManagementSubCommandHandlerBase<ManagementReadCommand, ManagementReadCommandHandler>,
    ICliHandler<ManagementReadCommand, ManagementReadCommandHandler>
{
    private ManagementReadCommandHandler(ManagementReadCommand command) : base(command)
    {
    }

    public static ManagementReadCommandHandler Create(ManagementReadCommand command) => new(command);

    public void RegisterOn(Command command) => RegisterDefaults(command);

    protected override RemoteOperation GetRemoteOperation(IManagementOperation operation) =>
        operation.Read;
}
