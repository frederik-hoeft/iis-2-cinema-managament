using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int SeatRowId,
    [Required] string Name
);

internal record DeleteSeatRequest
(
    [Required][Range(1, int.MaxValue)] int SeatId
);

internal record UpdateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int SeatId, 
    [Required] string Name
);

internal record GetAvailableRowsRequest
(
    [Required][Range(1, int.MaxValue)] int CinemahallId
);
