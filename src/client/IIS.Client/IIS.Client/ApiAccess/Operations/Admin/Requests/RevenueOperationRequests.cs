using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Admin.Requests;

/// <summary>
/// POST /admin/revenue/list-movies
/// </summary>
internal record GetMovieRevenueRequest([Required][Range(1, int.MaxValue)] int MovieId);

/// <summary>
/// POST /admin/revenue/list-screenings
/// </summary>
internal record GetScreeningRevenueRequest([Required][Range(1, int.MaxValue)] int ScreeningId);