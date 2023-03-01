using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.User.Requests;
using IIS.Client.ApiAccess.Operations.User.Responses;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserAccountOperation : UserOperationBase, IUserOperationFactory<UserAccountOperation>
{
    protected override Uri Uri => base.Uri.CombineWith("account");

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
            userIdentifier = InputProvider.RequestStringFor("email");
        }
        string? firstName = InputProvider.RequestStringFor("first name");
        string? lastName = InputProvider.RequestStringFor("last name");
        UserCreateAccountRequest request = new(userIdentifier!, firstName!, lastName!);
        ValidationService.AssertIsValid(request);

        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserCreateAccountResponse? response = responseMessage.Content.ReadFromJson<UserCreateAccountResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created user account {request}", ConsoleColor.Green);
    }

    public void Delete()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
            using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
            GetUserAccountsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserAccountsResponse>();
            GetUserAccountsResponseEntry[] accounts = listResponse.AssertIsValid().Accounts;
            InputOptionPrompt<GetUserAccountsResponseEntry> accountPrompt = new(accounts, "Which user account should be deleted?");
            if (!accountPrompt.TryRequestInput(out GetUserAccountsResponseEntry? account))
            {
                return;
            }
            userIdentifier = account.Email;
        }
        UserDeleteAccountRequest request = new(userIdentifier!);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserDeleteAccountResponse? response = responseMessage.Content.ReadFromJson<UserDeleteAccountResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted user account {userIdentifier}", ConsoleColor.Green);
    }

    public void Read()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
            using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
            GetUserAccountsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserAccountsResponse>();
            GetUserAccountsResponseEntry[] accounts = listResponse.AssertIsValid().Accounts;
            Stdout.WriteLine("Ok. These are the user accounts:", ConsoleColor.Green);
            foreach (GetUserAccountsResponseEntry account in accounts)
            {
                Stdout.WriteLine($"  {account}", ConsoleColor.Green);
            }
        }
        else
        {
            GetUserAccountRequest request = new(userIdentifier!);
            using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("get"));
            requestMessage.Content = JsonContent.Create(request);
            using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
            GetUserAccountResponse? response = responseMessage.Content.ReadFromJson<GetUserAccountResponse>();
            GetUserAccountsResponseEntry? account = response.AssertIsValid().Account;
            Stdout.WriteLine($"Ok. This is the user account: {account}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        GetUserAccountsResponseEntry account;
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
            using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
            GetUserAccountsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserAccountsResponse>();
            GetUserAccountsResponseEntry[] accounts = listResponse.AssertIsValid().Accounts;
            InputOptionPrompt<GetUserAccountsResponseEntry> accountPrompt = new(accounts, "Which user account should be updated?");
            if (!accountPrompt.TryRequestInput(out GetUserAccountsResponseEntry? selectedAccount))
            {
                return;
            }
            account = selectedAccount;
            userIdentifier = account.Email;
        }
        else
        {
            GetUserAccountRequest request = new(UserIdentity!);
            using HttpRequestMessage getRequestMessage = new(HttpMethod.Post, Uri.CombineWith("get"));
            getRequestMessage.Content = JsonContent.Create(request);
            using HttpResponseMessage getResponseMessage = ApiContext.HttpClient.Send(getRequestMessage);
            GetUserAccountResponse? getResponse = getResponseMessage.Content.ReadFromJson<GetUserAccountResponse>();
            account = getResponse.AssertIsValid().Account;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? firstName = InputProvider.RequestStringFor($"new first name [{account.FirstName}]");
        if (string.IsNullOrEmpty(firstName))
        {
            firstName = account.FirstName;
        }
        string? lastName = InputProvider.RequestStringFor($"new last name [{account.LastName}]");
        if (string.IsNullOrEmpty(lastName))
        {
            lastName = account.LastName;
        }
        UserUpdateAccountRequest updateRequest = new(userIdentifier!, firstName!, lastName!);
        ValidationService.AssertIsValid(updateRequest);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        requestMessage.Content = JsonContent.Create(updateRequest);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserUpdateAccountResponse? response = responseMessage.Content.ReadFromJson<UserUpdateAccountResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated user account {account} to {updateRequest}", ConsoleColor.Green);
    }
}
