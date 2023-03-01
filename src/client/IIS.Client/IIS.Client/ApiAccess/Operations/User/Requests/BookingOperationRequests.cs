using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /user/booking/book
/// </summary>
internal record UserBookingRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ScreeningId, 
    [Required][Range(1, int.MaxValue)] int SeatId
);

/// <summary>
/// POST /user/booking/cancel
/// </summary>
internal record UserCancelReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ReservationId
);

/// <summary>
/// POST /user/booking/reserve
/// </summary>
internal record UserReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ScreeningId, 
    [Required][Range(1, int.MaxValue)] int SeatId
);

/// <summary>
/// POST /user/booking/upgrade
/// </summary>
internal record UserUpgradeReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ReservationId
);

/// <summary>
/// POST /user/booking/get-bookings
/// </summary>
internal record GetUserBookingsRequest([Required][EmailAddress] string Email);

/// <summary>
/// POST /user/booking/get-reservation
/// </summary>
internal record GetUserReservationsRequest([Required][EmailAddress] string Email);