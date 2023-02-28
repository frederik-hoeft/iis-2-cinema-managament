using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;

namespace IIS.Client.ApiAccess.Operations.User;

internal abstract class UserOperationBase : OperationBase
{
    protected override Uri Uri => base.Uri.CombineWith("user");

    protected UserOperationBase(ApiContext apiContext, string? userIdentity) : base(apiContext)
    {
        UserIdentity = userIdentity;
    }

    protected string? UserIdentity { get; }
}
