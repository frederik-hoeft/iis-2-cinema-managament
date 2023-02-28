using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Admin.Requests;

internal record GetMovieRevenueResponse(bool Success, decimal TotalRevenue) : IApiResponse;

internal record GetScreeningRevenueResponse(bool Success, decimal TotalRevenue) : IApiResponse;