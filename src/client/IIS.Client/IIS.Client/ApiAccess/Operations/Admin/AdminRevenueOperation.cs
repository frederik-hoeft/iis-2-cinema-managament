using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Admin.Requests;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.Admin;

internal class AdminRevenueOperation : OperationBase
{
    protected override Uri Uri => base.Uri.CombineWith("revenue");

    public AdminRevenueOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public void ShowRevenue()
    {
        RevenuePromptOption[] options = Enum.GetValues(typeof(RevenuePromptOption)).Cast<RevenuePromptOption>().ToArray();
        const string prompt = "Do you want to see the revenue of a movie or a movie sceening?";
        InputOptionPrompt<RevenuePromptOption> revenueTypePrompt = new(options, prompt);
        if (revenueTypePrompt.TryRequestInput(out RevenuePromptOption selection))
        {
            _ = selection switch
            {
                RevenuePromptOption.Movies => ShowRevenueOfMovie(),
                _ => ShowRevenueOfMovieScreening(),
            };
        }
    }

    private int ShowRevenueOfMovie()
    {
        using HttpRequestMessage movieRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-movies"));
        using HttpResponseMessage getMoviesResponseMessage = ApiContext.HttpClient.Send(movieRequestMessage);
        GetMoviesResponse? getMoviesResponse = getMoviesResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] movies = getMoviesResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetMoviesResponseEntry> moviePrompt = new(movies, "Select the movie");
        if (!moviePrompt.TryRequestInput(out GetMoviesResponseEntry? movie))
        {
            return 0;
        }
        GetMovieRevenueRequest revenueRequest = new(movie.Id);
        using HttpRequestMessage revenueRequestMessage = new(HttpMethod.Post, Uri.CombineWith("movie"));
        revenueRequestMessage.Content = JsonContent.Create(revenueRequest);
        using HttpResponseMessage revenueResponseMessage = ApiContext.HttpClient.Send(revenueRequestMessage);
        GetMovieRevenueResponse? revenueResponse = revenueResponseMessage.Content.ReadFromJson<GetMovieRevenueResponse>();
        decimal revenue = revenueResponse.AssertIsValid().TotalRevenue;
        Console.WriteLine($"The total revenue for {movie.Title} was {revenue}€ so far.");
        return 0;
    }

    private int ShowRevenueOfMovieScreening()
    {
        using HttpRequestMessage screeningsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-screenings"));
        using HttpResponseMessage getScreeningsResponseMessage = ApiContext.HttpClient.Send(screeningsRequestMessage);
        GetMovieScreeningsResponse? getScreeningsResponse = getScreeningsResponseMessage.Content.ReadFromJson<GetMovieScreeningsResponse>();
        GetMovieScreeningsResponseEntry[] screenings = getScreeningsResponse.AssertIsValid().Screenings;
        InputOptionPrompt<GetMovieScreeningsResponseEntry> moviePrompt = new(screenings, "Select the movie screening");
        if (!moviePrompt.TryRequestInput(out GetMovieScreeningsResponseEntry? screening))
        {
            return 0;
        }
        GetScreeningRevenueRequest revenueRequest = new(screening.Id);
        using HttpRequestMessage revenueRequestMessage = new(HttpMethod.Post, Uri.CombineWith("screening"));
        revenueRequestMessage.Content = JsonContent.Create(revenueRequest);
        using HttpResponseMessage revenueResponseMessage = ApiContext.HttpClient.Send(revenueRequestMessage);
        GetScreeningRevenueResponse? revenueResponse = revenueResponseMessage.Content.ReadFromJson<GetScreeningRevenueResponse>();
        decimal revenue = revenueResponse.AssertIsValid().TotalRevenue;
        Console.WriteLine($"The total revenue for {screening.Name} was {revenue}€ so far.");
        return 0;
    }

    private enum RevenuePromptOption
    {
        Movies,
        Screenings
    }
}
