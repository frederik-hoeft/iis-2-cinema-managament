namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateSeatRowRequest(int CinemaHallId);

internal record DeleteSeatRowRequest(int SeatRowId);

internal record UpdateSeatRowRequest(int CinemaHallId, int SeatRowId);