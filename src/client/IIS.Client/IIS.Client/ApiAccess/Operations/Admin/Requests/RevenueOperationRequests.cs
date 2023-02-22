using System.ComponentModel.DataAnnotations;

namespace IIS.Client.ApiAccess.Operations.Admin.Requests;

internal record GetMovieRevenueRequest([Required] int MovieId);

internal record GetScreeningRevenueRequest([Required] int ScreeningId);