using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /management/seat/create
/// </summary>
internal record CreateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int RowId,
    [Required] string Name
);

/// <summary>
/// POST /management/seat/delete
/// </summary>
internal record DeleteSeatRequest
(
    [Required][Range(1, int.MaxValue)] int Id
);

/// <summary>
/// POST /management/seat/update
/// </summary>
internal record UpdateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int Id, 
    [Required] string Name
);

/// <summary>
/// POST /management/seat/list
/// </summary>
internal record GetSeatsRequest
(
    [Required][Range(1, int.MaxValue)] int RowId
);

/// <summary>
/// POST /user/booking/available-seats
/// </summary>
internal record GetAvailableSeatsRequest
(
    [Required][Range(1, int.MaxValue)] int RowId,
    [Required][Range(1, int.MaxValue)] int ScreeningId
);