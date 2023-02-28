using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

internal record CreateCinemaHallResponse(bool Success): IApiResponse;

internal record DeleteCinemaHallResponse(bool Success): IApiResponse;

internal record GetCinemaHallsResponseEntry(int Id, string Name);

internal record GetCinemaHallsResponse(bool Success, GetCinemaHallsResponseEntry[] CinemaHalls) : IApiResponse;

internal record UpdateCinemaHallResponse(bool Success) : IApiResponse;