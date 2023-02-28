using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.User.Requests;

internal record UserCreateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);

internal record UserDeleteAccountRequest
(
    [Required][EmailAddress] string Email
);

internal record GetUserAccountRequest
(
    [Required][EmailAddress] string Email
);

internal record UserUpdateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);