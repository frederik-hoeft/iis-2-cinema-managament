namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record UserBookingRequest(int ScreeningId, int SeatId);

internal record UserCancelReservationRequest(int ReservationId);

internal record UserReservationRequest(int ScreeningId, int SeatId);

internal record UserUpgradeReservationRequest(int ReservationId);