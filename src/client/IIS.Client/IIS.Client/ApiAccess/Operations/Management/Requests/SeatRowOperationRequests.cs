namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateSeatRowRequest(int CinemaHallId, string Name, PriceCategory PriceCategory);

internal enum PriceCategory
{
    Stalls,
    Box,
    BoxService
}

internal record DeleteSeatRowRequest(int SeatRowId);

internal record UpdateSeatRowRequest(int SeatRowId, string Name, PriceCategory PriceCategory);
