using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;
using System.Xml.Linq;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class MovieScreeningOperation : ManagementOperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("movie-screening");

    public MovieScreeningOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Screenings;

    public void Create()
    {
        using HttpRequestMessage availableMoviesRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-movies"));
        using HttpResponseMessage availableMoviesResponseMessage = ApiContext.HttpClient.Send(availableMoviesRequestMessage);
        GetMoviesResponse? availableMovieResponse = availableMoviesResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] availableMovies = availableMovieResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetMoviesResponseEntry> moviePrompt = new(availableMovies, "Which movie should be screened?");
        if (!moviePrompt.TryRequestInput(out GetMoviesResponseEntry? movie))
        {
            return;
        }
        using HttpRequestMessage availableHallsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-halls"));
        using HttpResponseMessage availableHallsResponseMessage = ApiContext.HttpClient.Send(availableHallsRequestMessage);
        GetCinemaHallsResponse? availableHallsResponse = availableHallsResponseMessage.Content.ReadFromJson<GetCinemaHallsResponse>();
        GetCinemaHallsResponseEntry[] availableHalls = availableHallsResponse.AssertIsValid().CinemaHalls;
        InputOptionPrompt<GetCinemaHallsResponseEntry> hallPrompt = new(availableHalls, "Which cinema hall should be used?");
        if (!hallPrompt.TryRequestInput(out GetCinemaHallsResponseEntry? hall))
        {
            return;
        }
        string? screeningName = InputProvider.RequestStringFor("screening name");
        bool hasExpired = InputProvider.RequestBoolFor("did this movie screening already take place?", false);
        CreateMovieScreeningRequest request = new(movie.Id, hall.Id, screeningName!, hasExpired);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateMovieScreeningResponse? response = responseMessage.Content.ReadFromJson<CreateMovieScreeningResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created movie screening {request}", ConsoleColor.Green);
    }

    public void Delete()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMovieScreeningsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMovieScreeningsResponse>();
        GetMovieScreeningsResponseEntry[] screenings = listResponse.AssertIsValid().Screenings;
        InputOptionPrompt<GetMovieScreeningsResponseEntry> screeningPrompt = new(screenings, "Which movie screening should be deleted?");
        if (!screeningPrompt.TryRequestInput(out GetMovieScreeningsResponseEntry? screening))
        {
            return;
        }
        DeleteMovieScreeningRequest request = new(screening.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteMovieScreeningResponse? response = responseMessage.Content.ReadFromJson<DeleteMovieScreeningResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted movie screening {screening}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-full"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMovieScreeningsFullResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMovieScreeningsFullResponse>();
        GetMovieScreeningsFullResponseEntry[] screenings = listResponse.AssertIsValid().Screenings;
        Stdout.WriteLine("Ok. These are the movie screenings:", ConsoleColor.Green);
        foreach (GetMovieScreeningsFullResponseEntry screening in screenings)
        {
            Stdout.WriteLine($"  {screening}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMovieScreeningsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMovieScreeningsResponse>();
        GetMovieScreeningsResponseEntry[] screenings = listResponse.AssertIsValid().Screenings;
        InputOptionPrompt<GetMovieScreeningsResponseEntry> screeningPrompt = new(screenings, "Which movie screening should be updated?");
        if (!screeningPrompt.TryRequestInput(out GetMovieScreeningsResponseEntry? screening))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? name = InputProvider.RequestStringFor($"new movie screening name [{screening.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = screening.Name;
        }
        bool hasExpired = InputProvider.RequestBoolFor("did this movie screening already take place?", screening.HasExpired);
        UpdateMovieScreeningRequest request = new(screening.Id, name!, hasExpired);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateMovieScreeningResponse? response = responseMessage.Content.ReadFromJson<UpdateMovieScreeningResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated movie screening {screening} to {request}", ConsoleColor.Green);
    }
}
