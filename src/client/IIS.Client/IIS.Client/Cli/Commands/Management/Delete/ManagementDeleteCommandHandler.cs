using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.Management;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.Cli.Commands.Management.Create;
using System;
using System.Collections.Generic;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Cli.Commands.Management.Delete;

internal class ManagementDeleteCommandHandler : ManagementSubCommandHandlerBase<ManagementDeleteCommand, ManagementDeleteCommandHandler>, 
    ICliHandler<ManagementDeleteCommand, ManagementDeleteCommandHandler>
{
    private ManagementDeleteCommandHandler(ManagementDeleteCommand command) : base(command)
    {
    }

    public static ManagementDeleteCommandHandler Create(ManagementDeleteCommand command) => new(command);

    public void RegisterOn(Command command) => RegisterDefaults(command);

    protected override RemoteOperation GetRemoteOperation(IManagementOperation operation) =>
        operation.Delete;
}
