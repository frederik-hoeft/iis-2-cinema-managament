using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateMovieRequest([Required] string Title, [Required] string Description);

internal record DeleteMovieRequest([Required] int Id);

internal record UpdateMovieRequest([Required] int Id, [Required] string Title, [Required] string Description);