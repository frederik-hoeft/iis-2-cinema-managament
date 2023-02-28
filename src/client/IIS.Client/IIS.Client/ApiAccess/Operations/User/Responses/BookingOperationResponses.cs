using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record UserBookingResponse(bool Success) : IApiResponse;

internal record UserCancelReservationResponse(bool Success) : IApiResponse;

internal record UserReservationResponse(bool Success): IApiResponse;

internal record UserUpgradeReservationResponse(bool Success): IApiResponse;