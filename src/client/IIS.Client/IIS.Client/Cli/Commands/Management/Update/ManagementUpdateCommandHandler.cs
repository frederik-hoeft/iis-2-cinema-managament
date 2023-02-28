using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Management;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Update;

internal class ManagementUpdateCommandHandler : ManagementSubCommandHandlerBase<ManagementUpdateCommand, ManagementUpdateCommandHandler>,
    ICliHandler<ManagementUpdateCommand, ManagementUpdateCommandHandler>
{
    private ManagementUpdateCommandHandler(ManagementUpdateCommand command) : base(command)
    {
    }

    public static ManagementUpdateCommandHandler Create(ManagementUpdateCommand command) => new(command);

    public void RegisterOn(Command command) => RegisterDefaults(command);

    protected override RemoteOperation GetRemoteOperation(IManagementOperation operation) =>
        operation.Update;
}
