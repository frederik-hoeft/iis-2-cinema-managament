using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.User.Responses;

internal record UserCreateAccountResponse(bool Success) : IApiResponse;

internal record GetUserAccountsResponseEntry(int Id, string FirstName, string LastName, string Email);

internal record GetUserAccountsResponse(bool Success, GetUserAccountsResponseEntry[] Accounts) : IApiResponse;

internal record GetUserAccountResponse(bool Success, GetUserAccountsResponseEntry Account) : IApiResponse;

internal record UserDeleteAccountResponse(bool Success) : IApiResponse;

internal record UserUpdateAccountResponse(bool Success) : IApiResponse;