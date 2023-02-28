using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class MovieOperation : ManagementOperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("movie");

    public MovieOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Movies;

    public void Create()
    {
        string? title = InputProvider.RequestValueFor("movie title");
        string? description = InputProvider.RequestValueFor("movie description");
        CreateMovieRequest request = new(title!, description!);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateMovieResponse? response = responseMessage.Content.ReadFromJson<CreateMovieResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created movie {title}", ConsoleColor.Green);
    }

    public void Delete()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMoviesResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] movies = listResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetMoviesResponseEntry> moviePrompt = new(movies, "Which movie should be deleted?");
        if (!moviePrompt.TryRequestInput(out GetMoviesResponseEntry? movie))
        {
            return;
        }
        DeleteMovieRequest request = new(movie.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteMovieResponse? response = responseMessage.Content.ReadFromJson<DeleteMovieResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted movie {movie}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMoviesResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] movies = listResponse.AssertIsValid().Movies;
        Stdout.WriteLine("Ok. These are the movies:", ConsoleColor.Green);
        foreach (GetMoviesResponseEntry movie in movies)
        {
            Stdout.WriteLine($"  {movie}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMoviesResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] movies = listResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetMoviesResponseEntry> moviePrompt = new(movies, "Which movie should be updated?");
        if (!moviePrompt.TryRequestInput(out GetMoviesResponseEntry? movie))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? title = InputProvider.RequestValueFor($"new movie title [{movie.Title}]");
        if (string.IsNullOrEmpty(title))
        {
            title = movie.Title;
        }
        string? description = InputProvider.RequestValueFor($"new movie description [{movie.Description}]");
        if (string.IsNullOrEmpty(description))
        {
            description = movie.Description;
        }
        UpdateMovieRequest request = new(movie.Id, title!, description!);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateMovieResponse? response = responseMessage.Content.ReadFromJson<UpdateMovieResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated movie {movie} to {request}", ConsoleColor.Green);
    }
}
