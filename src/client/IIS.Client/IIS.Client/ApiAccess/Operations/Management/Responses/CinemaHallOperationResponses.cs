using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

/// <summary>
/// POST /management/cinema-hall/create
/// </summary>
internal record CreateCinemaHallResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/cinema-hall/delete
/// </summary>
internal record DeleteCinemaHallResponse(bool Success, string? Error) : IApiResponse;

internal record GetCinemaHallsResponseEntry(int Id, string Name, bool Available);

/// <summary>
/// GET /management/cinema-hall/list
/// GET /management/movie-screening/available-halls
/// GET /management/seat/available-halls
/// </summary>
internal record GetCinemaHallsResponse(bool Success, string? Error, GetCinemaHallsResponseEntry[] CinemaHalls) : IApiResponse;

internal record GetCinemaHallsFullResponseEntry(int Id, string Name, bool Available, GetSeatRowsResponseEntry[] Rows);

/// <summary>
/// GET /management/cinema-hall/list-full
/// </summary>
internal record GetCinemaHallsFullResponse(bool Success, string? Error, GetCinemaHallsFullResponseEntry[] CinemaHalls) : IApiResponse;

/// <summary>
/// POST /management/cinema-hall/update
/// </summary>
internal record UpdateCinemaHallResponse(bool Success, string? Error) : IApiResponse;