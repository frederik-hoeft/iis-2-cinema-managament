namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateSeatRequest(int SeatRowId);

internal record DeleteSeatRequest(int SeatId);

internal record UpdateSeatRequest(int SeatId, int SeatRowId);