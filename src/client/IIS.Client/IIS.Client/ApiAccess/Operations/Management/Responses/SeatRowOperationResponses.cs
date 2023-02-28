using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations.Management.Requests;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

internal record CreateSeatRowResponse(bool Success) : IApiResponse;

internal record DeleteSeatRowResponse(bool Success) : IApiResponse;

internal record GetSeatRowsResponseEntry(int Id, string CinemaHallName, string Name, PriceCategory PriceCategory);

internal record GetSeatRowsResponse(bool Success, GetSeatRowsResponseEntry[] Rows) : IApiResponse;

internal record UpdateSeatRowResponse(bool Success) : IApiResponse;