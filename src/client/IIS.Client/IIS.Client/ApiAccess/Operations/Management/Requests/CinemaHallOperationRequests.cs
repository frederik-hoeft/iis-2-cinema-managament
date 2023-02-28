using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateCinemaHallRequest([Required] string CinemaHallName);

internal record DeleteCinemaHallRequest([Required] int CinemaHallId);

internal record UpdateCinemaHallRequest([Required] int CinemaHallId, [Required] string NewCinemaHallName);