using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

internal class BookingOperation : UserOperationBase
{
    public BookingOperation(string? userIdentifier) : base(userIdentifier)
    {
    }

    public void Book(ApiContext apiContext) => throw new NotImplementedException();

    public void Reserve(ApiContext apiContext) => throw new NotImplementedException();

    public void Cancel(ApiContext apiContext) => throw new NotImplementedException();

    public void Upgrade(ApiContext apiContext) => throw new NotImplementedException();

    public void ShowAll(ApiContext apiContext) => throw new NotImplementedException();
}
