using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.ApiAccess.Operations.User.Responses;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /user/booking/book
/// </summary>
internal record UserBookingResponse(bool Success, string? Error) : IApiResponse;

internal record UserCancelReservationResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /user/booking/reserve
/// </summary>
internal record UserReservationResponse(bool Success, string? Error): IApiResponse;

/// <summary>
/// POST /user/booking/upgrade
/// </summary>
internal record UserUpgradeReservationResponse(bool Success, string? Error): IApiResponse;

internal record GetAvailableSeatRowsResponseEntry(int RowId, string Name, int AvailableSeatCount, PriceCategory PriceCategory);

/// <summary>
/// POST /user/booking/available-seat-rows
/// </summary>
internal record GetAvailableSeatRowsResponse(bool Success, string? Error, GetAvailableSeatRowsResponseEntry[] Rows) : IApiResponse;

internal record GetUserBookingsResponseEntry(int Id, GetMovieScreeningsFullResponseEntry MovieScreening, GetSeatsFullResponseEntry Seat, GetUserAccountsResponseEntry User);

/// <summary>
/// GET /user/booking/list-bookings
/// POST /user/booking/get-bookings
/// </summary>
internal record GetUserBookingsResponse(bool Success, string? Error, GetUserBookingsResponseEntry[] Bookings) : IApiResponse;

internal record GetUserReservationsResponseEntry(int Id, GetMovieScreeningsFullResponseEntry MovieScreening, GetSeatsFullResponseEntry Seat, GetUserAccountsResponseEntry User);

/// <summary>
/// GET /user/booking/list-reservations
/// POST /user/booking/get-reservations
/// </summary>
internal record GetUserReservationsResponse(bool Success, string? Error, GetUserReservationsResponseEntry[] Reservations) : IApiResponse;