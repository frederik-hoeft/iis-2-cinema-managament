using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.User.Responses;

/// <summary>
/// POST /user/account/create
/// </summary>
internal record UserCreateAccountResponse(bool Success, string? Error) : IApiResponse;

internal record GetUserAccountsResponseEntry(int Id, string FirstName, string LastName, string Email);

/// <summary>
/// GET /user/account/list
/// </summary>
internal record GetUserAccountsResponse(bool Success, string? Error, GetUserAccountsResponseEntry[] Accounts) : IApiResponse;

/// <summary>
/// POST /user/account/get
/// </summary>
internal record GetUserAccountResponse(bool Success, string? Error, GetUserAccountsResponseEntry Account) : IApiResponse;

/// <summary>
/// POST /user/account/delete
/// </summary>
internal record UserDeleteAccountResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /user/account/update
/// </summary>
internal record UserUpdateAccountResponse(bool Success, string? Error) : IApiResponse;