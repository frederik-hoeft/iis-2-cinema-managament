namespace IIS.Client.ApiAccess.Operations.Management.Requests;

internal record CreateMovieRequest(string Title);

internal record DeleteMovieRequest(int Id);

internal record UpdateMovieRequest(int Id, string Title);