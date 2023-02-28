using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;

namespace IIS.Client.ApiAccess.Operations.Management;

internal abstract class ManagementOperationBase : OperationBase
{
    protected override Uri Uri => base.Uri.CombineWith("management");

    protected ManagementOperationBase(ApiContext apiContext) : base(apiContext)
    {
    }
}
