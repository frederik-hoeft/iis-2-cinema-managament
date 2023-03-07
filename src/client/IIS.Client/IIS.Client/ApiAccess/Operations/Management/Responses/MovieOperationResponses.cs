using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

/// <summary>
/// POST /management/movie/create
/// </summary>
internal record CreateMovieResponse(bool Success, string? Error): IApiResponse;

/// <summary>
/// POST /management/movie/delete
/// </summary>
internal record DeleteMovieResponse(bool Success, string? Error) : IApiResponse;

internal record GetMoviesResponseEntry(int Id, string Title, string Description);

/// <summary>
/// GET /management/movie/list
/// GET /management/movie-screening/available-movies
/// GET /user/booking/available-movies
/// </summary>
internal record GetMoviesResponse(bool Success, string? Error, GetMoviesResponseEntry[] Movies) : IApiResponse;

internal record GetMoviesFullResponseEntry(int Id, string Title, string Description, int ScreeningCount);

/// <summary>
/// GET /management/movie/list-full
/// </summary>
internal record GetMoviesFullResponse(bool Success, string? Error, GetMoviesFullResponseEntry[] Movies) : IApiResponse;

/// <summary>
/// POST /management/movie/update
/// </summary>
internal record UpdateMovieResponse(bool Success, string? Error) : IApiResponse;