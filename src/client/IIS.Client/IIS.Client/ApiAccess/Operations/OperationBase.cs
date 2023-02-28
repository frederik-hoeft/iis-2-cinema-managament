using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations;

public abstract class OperationBase
{
    protected ApiContext ApiContext { get; }

    protected virtual Uri Uri => ApiContext.ApiBase;

    protected OperationBase(ApiContext apiContext) => ApiContext = apiContext;
}
