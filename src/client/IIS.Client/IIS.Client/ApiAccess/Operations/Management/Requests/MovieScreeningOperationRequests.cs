using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int MovieId, 
    [Required][Range(1, int.MaxValue)] int CinemaHallId, 
    [Required] string Name
);

internal record DeleteMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int ScreeningId
);

internal record UpdateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int ScreeningId,
    [Required] string Name
);