using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record CreateSeatRowRequest
(
    [Required][Range(1, int.MaxValue)] int CinemaHallId,
    [Required]string Name,
    [Required][Range(1, 3)] PriceCategory PriceCategory
);

/// <summary>
/// POST /management/seat-row/delete
/// </summary>
internal record DeleteSeatRowRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/seat-row/update
/// </summary>
internal record UpdateSeatRowRequest
(
    [Required][Range(1, int.MaxValue)] int Id,
    [Required] string Name,
    [Required][Range(1, 3)] PriceCategory PriceCategory
);

/// <summary>
/// POST /management/seat-row/list
/// POST /management/seat/available-rows
/// </summary>
internal record GetSeatRowsRequest([Required][Range(1, int.MaxValue)] int CinemaHallId);

/// <summary>
/// POST /user/booking/available-seat-rows
/// </summary>
internal record GetAvailableSeatRowsRequest([Required][Range(1, int.MaxValue)] int ScreeningId);