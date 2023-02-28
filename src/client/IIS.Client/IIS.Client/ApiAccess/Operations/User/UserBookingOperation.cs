using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserBookingOperation : UserOperationBase, IUserOperationFactory<UserBookingOperation>
{
    private UserBookingOperation(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    public static UserBookingOperation Create(ApiContext apiContext, string? userIdentity) => 
        new(apiContext, userIdentity);

    public void Book() => throw new NotImplementedException();

    public void ShowAll() => throw new NotImplementedException();
}
