using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

internal abstract class UserOperationBase : OperationBase
{
    protected UserOperationBase(ApiContext apiContext, string? userIdentity) : base(apiContext)
    {
        UserIdentity = userIdentity;
    }

    protected string? UserIdentity { get; }

    protected virtual string ApiPath { get; } = "user";
}
