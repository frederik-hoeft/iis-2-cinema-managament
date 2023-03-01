using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Admin.Requests;

/// <summary>
/// POST /admin/revenue/list-movies
/// </summary>
internal record GetMovieRevenueResponse(bool Success, string? Error, decimal TotalRevenue) : IApiResponse;

/// <summary>
/// POST /admin/revenue/list-screenings
/// </summary>
internal record GetScreeningRevenueResponse(bool Success, string? Error, decimal TotalRevenue) : IApiResponse;