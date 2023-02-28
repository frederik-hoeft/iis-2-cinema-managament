using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserReservationOperation : UserOperationBase, IUserOperationFactory<UserReservationOperation>
{
    private UserReservationOperation(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    public static UserReservationOperation Create(ApiContext apiContext, string? userIdentity) => 
        new(apiContext, userIdentity);

    public void Reserve() => throw new NotImplementedException();

    public void Cancel() => throw new NotImplementedException();

    public void Upgrade() => throw new NotImplementedException();

    public void ShowAll() => throw new NotImplementedException();
}
