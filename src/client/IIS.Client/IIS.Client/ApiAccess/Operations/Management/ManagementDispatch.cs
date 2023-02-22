using IIS.Client.Cli.Commands.Management;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.ApiAccess.Operations.Management;

using static CommonOperationProvider;

internal class ManagementDispatch<TTarget> : IOperationFactory<ManagementOperation> where TTarget : IManagementOperation
{
    public static RemoteOperation Parse(ManagementOperation operation) => operation switch
    {
        ManagementOperation.Create => TTarget.Create,
        ManagementOperation.Update => TTarget.Update,
        ManagementOperation.Delete => TTarget.Delete,
        ManagementOperation.Show => TTarget.ShowAll,
        _ => Fail($"Undefined management operation: '{operation}'")
    };
}
