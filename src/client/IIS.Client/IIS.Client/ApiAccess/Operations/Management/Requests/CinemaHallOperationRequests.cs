using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

/// <summary>
/// POST /management/cinema-hall/create
/// </summary>
internal record CreateCinemaHallRequest([Required] string Name, [Required] bool Available);

/// <summary>
/// POST /management/cinema-hall/delete
/// </summary>
internal record DeleteCinemaHallRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/cinema-hall/update
/// </summary>
internal record UpdateCinemaHallRequest([Required][Range(1, int.MaxValue)] int Id, [Required] string NewName, [Required] bool IsAvailable);