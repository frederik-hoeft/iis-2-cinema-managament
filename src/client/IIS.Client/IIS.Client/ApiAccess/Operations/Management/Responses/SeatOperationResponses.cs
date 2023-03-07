using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations.Management.Requests;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

/// <summary>
/// POST /management/seat/create
/// </summary>
internal record CreateSeatResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/seat/delete
/// </summary>
internal record DeleteSeatResponse(bool Success, string? Error) : IApiResponse;

internal record GetSeatsResponseEntry(int Id, string Name);

internal record GetSeatsFullResponseEntry(int Id, string Name, GetSeatRowsResponseEntry Row);

/// <summary>
/// GET /management/seat/list-full
/// </summary>
internal record GetSeatsFullResponse(bool Success, string? Error, GetSeatsFullResponseEntry[] Seats) : IApiResponse;

/// <summary>
/// POST /management/seat/list
/// POST /user/booking/available-seats
/// </summary>
internal record GetSeatsResponse(bool Success, string? Error, GetSeatsResponseEntry[] Seats) : IApiResponse;

/// <summary>
/// POST /management/seat/update
/// </summary>
internal record UpdateSeatResponse(bool Success, string? Error) : IApiResponse;