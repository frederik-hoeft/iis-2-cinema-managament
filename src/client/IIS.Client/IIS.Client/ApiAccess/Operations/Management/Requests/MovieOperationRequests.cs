using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /management/movie/create
/// </summary>
internal record CreateMovieRequest([Required] string Title, [Required] string Description);

/// <summary>
/// POST /management/movie/delete
/// </summary>
internal record DeleteMovieRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/movie/update
/// </summary>
internal record UpdateMovieRequest([Required][Range(1, int.MaxValue)] int Id, [Required] string Title, [Required] string Description);