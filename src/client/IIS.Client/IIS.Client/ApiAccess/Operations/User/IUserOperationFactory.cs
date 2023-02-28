using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

public interface IUserOperationFactory<T> where T : class, IUserOperationFactory<T>
{
    static abstract T Create(ApiContext apiContext, string? userIdentity);
}
