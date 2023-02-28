using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.User.Requests;
using IIS.Client.ApiAccess.Operations.User.Responses;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserAccountOperation : UserOperationBase, IUserOperationFactory<UserAccountOperation>
{
    private UserAccountOperation(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    public static UserAccountOperation Create(ApiContext apiContext, string? userIdentity) => 
        new(apiContext, userIdentity);

    public void Register()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            userIdentifier = InputProvider.RequestValueFor("email");
        }
        string? firstName = InputProvider.RequestValueFor("first name");
        string? lastName = InputProvider.RequestValueFor("last name");
        UserCreateAccountRequest request = new(firstName!, lastName!, userIdentifier!);
        if (!ValidationService.ValidateRecord(request))
        {
            return;
        }

        Uri userApi = ApiContext.ApiBase.CombineWith(ApiPath).CombineWith("account");
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, userApi.CombineWith("create"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserCreateAccountResponse? response = responseMessage.Content.ReadFromJson<UserCreateAccountResponse>();
        Console.WriteLine($"Success: {response?.Success is true}");
    }

    public void Delete() => throw new NotImplementedException();

    public void Show() => throw new NotImplementedException();
}
