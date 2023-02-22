namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateScreeningRequest(int MovieId, int CinemaHallId);

internal record DeleteScreeningRequest(int ScreeningId);

internal record UpdateScreeningRequest(int ScreeningId, int MovieId, int CinemaHallId);