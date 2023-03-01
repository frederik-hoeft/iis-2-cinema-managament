using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal abstract class UserBookingOperationBase : UserOperationBase
{
    protected sealed override Uri Uri => base.Uri.CombineWith("booking");

    protected UserBookingOperationBase(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    protected (string Email, GetMovieScreeningsFullResponseEntry MovieScreening, GetSeatsResponseEntry Seat)? GetBookingContext()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            userIdentifier = InputProvider.RequestStringFor("email");
        }
        using HttpRequestMessage availableMoviesRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-movies"));
        using HttpResponseMessage availableMoviesResponseMessage = ApiContext.HttpClient.Send(availableMoviesRequestMessage);
        GetMoviesResponse? availableMoviesResponse = availableMoviesResponseMessage.Content.ReadFromJson<GetMoviesResponse>();
        GetMoviesResponseEntry[] availableMovies = availableMoviesResponse.AssertIsValid().Movies;
        InputOptionPrompt<GetMoviesResponseEntry> moviePrompt = new(availableMovies, "Which movie would you like to watch?");
        if (!moviePrompt.TryRequestInput(out GetMoviesResponseEntry? movie))
        {
            return null;
        }
        GetMovieScreeningsRequest availableScreeningsRequest = new(movie.Id);
        using HttpRequestMessage availableScreeningsRequestMessage = new(HttpMethod.Post, Uri.CombineWith("available-screenings"));
        availableMoviesRequestMessage.Content = JsonContent.Create(availableScreeningsRequest);
        using HttpResponseMessage availableScreeningsResponseMessage = ApiContext.HttpClient.Send(availableScreeningsRequestMessage);
        GetMovieScreeningsFullResponse? availableScreeningsResponse = availableScreeningsResponseMessage.Content.ReadFromJson<GetMovieScreeningsFullResponse>();
        GetMovieScreeningsFullResponseEntry[] availableScreenings = availableScreeningsResponse.AssertIsValid().Screenings;
        InputOptionPrompt<GetMovieScreeningsFullResponseEntry> screeningPrompt = new(availableScreenings, "Which movie screening should be booked?");
        if (!screeningPrompt.TryRequestInput(out GetMovieScreeningsFullResponseEntry? screening))
        {
            return null;
        }
        GetSeatRowRequest availableSeatRowsRequest = new(screening.CinemaHall.Id);
        using HttpRequestMessage availableSeatRowsRequestMessage = new(HttpMethod.Post, Uri.CombineWith("available-seat-rows"));
        availableSeatRowsRequestMessage.Content = JsonContent.Create(availableSeatRowsRequest);
        using HttpResponseMessage availableSeatRowsResponseMessage = ApiContext.HttpClient.Send(availableSeatRowsRequestMessage);
        GetAvailableSeatRowsResponse? availableSeatRowsResponse = availableSeatRowsResponseMessage.Content.ReadFromJson<GetAvailableSeatRowsResponse>();
        GetAvailableSeatRowsResponseEntry[] availableSeatRows = availableSeatRowsResponse.AssertIsValid().Rows;
        InputOptionPrompt<GetAvailableSeatRowsResponseEntry> seatRowPrompt = new(availableSeatRows, "Which seat row would you like to select?");
        if (!seatRowPrompt.TryRequestInput(out GetAvailableSeatRowsResponseEntry? seatRow))
        {
            return null;
        }
        GetSeatsRequest availableSeatsRequest = new(seatRow.RowId);
        using HttpRequestMessage availableSeatsRequestMessage = new(HttpMethod.Post, Uri.CombineWith("available-seats"));
        availableSeatsRequestMessage.Content = JsonContent.Create(availableSeatsRequest);
        using HttpResponseMessage availableSeatsResponseMessage = ApiContext.HttpClient.Send(availableSeatsRequestMessage);
        GetSeatsResponse? availableSeatsResponse = availableSeatsResponseMessage.Content.ReadFromJson<GetSeatsResponse>();
        GetSeatsResponseEntry[] availableSeats = availableSeatsResponse.AssertIsValid().Seats;
        InputOptionPrompt<GetSeatsResponseEntry> seatPrompt = new(availableSeats, "Which seat should be booked?");
        return !seatPrompt.TryRequestInput(out GetSeatsResponseEntry? seat) 
            ? null : 
            (userIdentifier!, screening, seat);
    }
}
