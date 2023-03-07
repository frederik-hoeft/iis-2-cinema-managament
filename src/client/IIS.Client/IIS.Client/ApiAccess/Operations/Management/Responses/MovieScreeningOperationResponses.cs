using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

/// <summary>
/// POST /management/movie-screening/create
/// </summary>
internal record CreateMovieScreeningResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/movie-screening/delete
/// </summary>
internal record DeleteMovieScreeningResponse(bool Success, string? Error) : IApiResponse;

internal record GetMovieScreeningsResponseEntry(int Id, string Name, string MovieTitle, bool HasExpired);

/// <summary>
/// GET /management/movie-screening/list
/// </summary>
internal record GetMovieScreeningsResponse(bool Success, string? Error, GetMovieScreeningsResponseEntry[] Screenings) : IApiResponse;

internal record GetMovieScreeningsFullResponseEntry(int Id, string Name, GetMoviesResponseEntry Movie, GetCinemaHallsResponseEntry Hall, bool HasExpired);

/// <summary>
/// GET /management/movie-screening/list-full
/// POST /user/booking/available-screenings
/// </summary>
internal record GetMovieScreeningsFullResponse(bool Success, string? Error, GetMovieScreeningsFullResponseEntry[] Screenings) : IApiResponse;

/// <summary>
/// POST /management/movie-screening/update
/// </summary>
internal record UpdateMovieScreeningResponse(bool Success, string? Error) : IApiResponse;