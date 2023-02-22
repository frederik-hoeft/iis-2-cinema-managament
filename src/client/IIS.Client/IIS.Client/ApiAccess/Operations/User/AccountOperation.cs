using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.User.Requests;
using IIS.Client.ApiAccess.Operations.User.Responses;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal class AccountOperation : UserOperationBase
{
    public AccountOperation(string? userIdentifier) : base(userIdentifier)
    {
    }

    public void Register(ApiContext apiContext)
    {
        string? userIdentifier = UserIdentifier;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            userIdentifier = InputProvider.RequestValueFor("email");
        }
        string? firstName = InputProvider.RequestValueFor("first name");
        string? lastName = InputProvider.RequestValueFor("last name");
        UserCreateAccountRequest request = new(firstName!, lastName!, userIdentifier!);

        Uri userApi = apiContext.ApiBase.CombineWith(ApiPath).CombineWith("account");
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, userApi.CombineWith("create"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = apiContext.HttpClient.Send(requestMessage);
        UserCreateAccountResponse? response = responseMessage.Content.ReadFromJson<UserCreateAccountResponse>();
        Console.WriteLine($"Success: {response?.Success is true}");
    }

    public void Delete(ApiContext apiContext) => throw new NotImplementedException();

    public void Show(ApiContext apiContext) => throw new NotImplementedException();
}
