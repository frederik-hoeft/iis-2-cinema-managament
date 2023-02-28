using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

internal record CreateMovieScreeningResponse(bool Success) : IApiResponse;

internal record DeleteMovieScreeningResponse(bool Success) : IApiResponse;

internal record GetMovieScreeningsResponseEntry(int Id, string Name);

internal record GetMovieScreeningsResponse(bool Success, GetMovieScreeningsResponseEntry[] Screenings) : IApiResponse;

internal record GetAvailableMoviesResponseEntry(int Id, string Title);

internal record GetAvailableMoviesResponse(bool Success, GetAvailableMoviesResponseEntry[] Movies) : IApiResponse;

internal record GetAvailableHallsResponseEntry(int Id, string Title);

internal record GetAvailableHallsResponse(bool Success, GetAvailableHallsResponseEntry[] Halls) : IApiResponse;

internal record UpdateMovieScreeningResponse(bool Success) : IApiResponse;