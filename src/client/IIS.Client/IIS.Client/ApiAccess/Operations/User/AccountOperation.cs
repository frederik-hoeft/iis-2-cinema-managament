using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

internal class AccountOperation : UserOperationBase
{
    public AccountOperation(string? userIdentifier) : base(userIdentifier)
    {
    }

    public void Register(ApiContext apiContext) => throw new NotImplementedException();

    public void Delete(ApiContext apiContext) => throw new NotImplementedException();

    public void Show(ApiContext apiContext) => throw new NotImplementedException();
}
