using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations.Management.Requests;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record CreateSeatRowResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/seat-row/delete
/// </summary>
internal record DeleteSeatRowResponse(bool Success, string? Error) : IApiResponse;

internal record GetSeatRowsResponseEntry(int Id, string Name, int SeatCount, PriceCategory PriceCategory);

/// <summary>
/// POST /management/seat-row/list
/// POST /management/seat/available-rows
/// </summary>
internal record GetSeatRowsResponse(bool Success, string? Error, GetSeatRowsResponseEntry[] Rows) : IApiResponse;

internal record GetSeatRowsFullResponseEntry(int Id, string Name, string CinemaHallName, PriceCategory PriceCategory, GetSeatsResponseEntry[] Seats);

/// <summary>
/// GET /management/seat-row/list-full
/// </summary>
internal record GetSeatRowsFullResponse(bool Success, string? Error, GetSeatRowsFullResponseEntry[] Rows) : IApiResponse;

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record UpdateSeatRowResponse(bool Success, string? Error) : IApiResponse;