using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.User.Requests;

internal record UserCreateAccountRequest
(
    [Required] string FirstName,
    [Required] string LastName,
    [EmailAddress] string Email
);

internal record UserDeleteAccountRequest
(
    [Required][EmailAddress] string Email
);

internal record UserShowAccountRequest
(
    [EmailAddress] string? Email
);