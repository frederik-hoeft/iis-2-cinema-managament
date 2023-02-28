using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations.Management.Requests;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;

internal record CreateSeatResponse(bool Success) : IApiResponse;

internal record DeleteSeatResponse(bool Success) : IApiResponse;

internal record GetSeatsResponseEntry(int Id, string Name);

internal record GetSeatsResponse(bool Success, GetSeatsResponseEntry[] Seats) : IApiResponse;

internal record UpdateSeatResponse(bool Success) : IApiResponse;

internal record GetAvailableRowsResponseEntry(int Id, int SeatCount, PriceCategory PriceCategory);

internal record GetAvailableRowsResponse(bool Success, GetAvailableRowsResponseEntry[] Rows) : IApiResponse;