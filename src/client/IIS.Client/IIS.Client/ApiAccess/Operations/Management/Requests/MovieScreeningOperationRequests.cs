using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /management/movie-screening/create
/// </summary>
internal record CreateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int MovieId, 
    [Required][Range(1, int.MaxValue)] int CinemaHallId, 
    [Required] string Name,
    [Required] bool HasExpired
);

/// <summary>
/// POST /management/movie-screening/delete
/// </summary>
internal record DeleteMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int Id
);

/// <summary>
/// POST /management/movie-screening/update
/// </summary>
internal record UpdateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int Id,
    [Required] string NewName,
    [Required] bool NewFinished
);

/// <summary>
/// POST /user/booking/available-screenings
/// </summary>
internal record GetMovieScreeningsRequest
(
    [Required][Range(1, int.MaxValue)] int MovieId
);