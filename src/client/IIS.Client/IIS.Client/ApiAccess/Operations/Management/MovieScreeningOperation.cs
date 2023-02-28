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

internal class MovieScreeningOperation : OperationBase, IManagementOperation
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
        GetAvailableMoviesResponse? availableMovieResponse = availableMoviesResponseMessage.Content.ReadFromJson<GetAvailableMoviesResponse>();
        GetAvailableMoviesResponseEntry[] availableMovies = availableMovieResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetAvailableMoviesResponseEntry> moviePrompt = new(availableMovies, "Which movie should be screened?");
        if (!moviePrompt.TryRequestInput(out GetAvailableMoviesResponseEntry? movie))
        {
            return;
        }
        using HttpRequestMessage availableHallsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-halls"));
        using HttpResponseMessage availableHallsResponseMessage = ApiContext.HttpClient.Send(availableHallsRequestMessage);
        GetAvailableHallsResponse? availableHallsResponse = availableHallsResponseMessage.Content.ReadFromJson<GetAvailableHallsResponse>();
        GetAvailableHallsResponseEntry[] availableHalls = availableHallsResponse.AssertIsValid().Halls;
        InputOptionPrompt<GetAvailableHallsResponseEntry> hallPrompt = new(availableHalls, "Which cinema hall should be used?");
        if (!hallPrompt.TryRequestInput(out GetAvailableHallsResponseEntry? hall))
        {
            return;
        }
        string? screeningName = InputProvider.RequestValueFor("screening name");
        CreateMovieScreeningRequest request = new(movie.Id, hall.Id, screeningName!);
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
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetMovieScreeningsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetMovieScreeningsResponse>();
        GetMovieScreeningsResponseEntry[] screenings = listResponse.AssertIsValid().Screenings;
        Stdout.WriteLine("Ok. These are the movie screenings:", ConsoleColor.Green);
        foreach (GetMovieScreeningsResponseEntry screening in screenings)
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
        string? name = InputProvider.RequestValueFor($"new movie screening name [{screening.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = screening.Name;
        }
        UpdateMovieScreeningRequest request = new(screening.Id, name!);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateMovieScreeningResponse? response = responseMessage.Content.ReadFromJson<UpdateMovieScreeningResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated movie screening {screening} to {request}", ConsoleColor.Green);
    }
}
