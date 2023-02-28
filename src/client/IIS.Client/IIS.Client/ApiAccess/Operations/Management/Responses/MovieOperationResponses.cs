using IIS.Client.ApiAccess.ModelValidation;

namespace IIS.Client.ApiAccess.Operations.Management.Responses;
    
internal record CreateMovieResponse(bool Success): IApiResponse;

internal record DeleteMovieResponse(bool Success): IApiResponse;

internal record GetMoviesResponseEntry(int Id, string Title, string Description);

internal record GetMoviesResponse(bool Success, GetMoviesResponseEntry[] Movies) : IApiResponse;

internal record UpdateMovieResponse(bool Success): IApiResponse;