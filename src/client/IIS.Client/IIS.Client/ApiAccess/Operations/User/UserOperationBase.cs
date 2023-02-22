namespace IIS.Client.ApiAccess.Operations.User;

internal abstract class UserOperationBase
{
    protected string? UserIdentifier { get; }

    protected virtual string ApiPath { get; } = "user";

    protected UserOperationBase(string? userIdentifier) => UserIdentifier = userIdentifier;
}
