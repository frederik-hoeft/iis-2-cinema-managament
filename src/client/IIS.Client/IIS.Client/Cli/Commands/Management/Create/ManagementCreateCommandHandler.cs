using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Management;
using System;
using System.Collections.Generic;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Cli.Commands.Management.Create;

internal class ManagementCreateCommandHandler : ManagementSubCommandHandlerBase<ManagementCreateCommand, ManagementCreateCommandHandler>, 
    ICliHandler<ManagementCreateCommand, ManagementCreateCommandHandler>
{
    private ManagementCreateCommandHandler(ManagementCreateCommand command) : base(command)
    {
    }

    public static ManagementCreateCommandHandler Create(ManagementCreateCommand command) => new(command);

    public void RegisterOn(Command command) => RegisterDefaults(command);

    protected override RemoteOperation GetRemoteOperation(IManagementOperation operation) =>
        operation.Create;
}
