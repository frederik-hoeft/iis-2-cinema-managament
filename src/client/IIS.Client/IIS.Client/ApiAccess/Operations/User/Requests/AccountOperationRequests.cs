using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.User.Requests;

/// <summary>
/// POST /user/account/create
/// </summary>
internal record UserCreateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);

/// <summary>
/// POST /user/account/delete
/// </summary>
internal record UserDeleteAccountRequest
(
    [Required][EmailAddress] string Email
);

/// <summary>
/// POST /user/account/get
/// </summary>
internal record GetUserAccountRequest
(
    [Required][EmailAddress] string Email
);

/// <summary>
/// POST /user/account/update
/// </summary>
internal record UserUpdateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);