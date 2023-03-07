using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserReservationOperation : UserBookingOperationBase, IUserOperationFactory<UserReservationOperation>
{
    private UserReservationOperation(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    public static UserReservationOperation Create(ApiContext apiContext, string? userIdentity) =>
        new(apiContext, userIdentity);

    public void Reserve()
    {
        if (GetBookingContext() is not (var userIdentifier, var screening, var seat))
        {
            return;
        }
        UserReservationRequest request = new(userIdentifier!, screening.Id, seat.Id);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("reserve"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserReservationResponse? response = responseMessage.Content.ReadFromJson<UserReservationResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully reserved seat {seat} for screening {screening}", ConsoleColor.Green);
    }

    public void Read()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-reservations"));
            using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
            GetUserReservationsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserReservationsResponse>();
            GetUserReservationsResponseEntry[] reservations = listResponse.AssertIsValid().Reservations;
            Stdout.WriteLine("Ok. These are all reservations of all users:", ConsoleColor.Green);
            foreach (GetUserReservationsResponseEntry reservation in reservations)
            {
                Stdout.WriteLine($"  {reservation}", ConsoleColor.Green);
            }
        }
        else
        {
            GetUserReservationsRequest request = new(userIdentifier!);
            using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("get-reservations"));
            requestMessage.Content = JsonContent.Create(request);
            using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
            GetUserReservationsResponse? response = responseMessage.Content.ReadFromJson<GetUserReservationsResponse>();
            GetUserReservationsResponseEntry[] reservations = response.AssertIsValid().Reservations;
            Stdout.WriteLine($"Ok. These are the user reservations for {userIdentifier}:", ConsoleColor.Green);
            foreach (GetUserReservationsResponseEntry reservation in reservations)
            {
                Stdout.WriteLine($"  {reservation}", ConsoleColor.Green);
            }
        }
    }

    public void Cancel()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            userIdentifier = InputProvider.RequestStringFor("email");
        }
        GetUserReservationsRequest listRequest = new(userIdentifier!);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("get-reservations"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetUserReservationsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserReservationsResponse>();
        GetUserReservationsResponseEntry[] reservations = listResponse.AssertIsValid().Reservations;
        InputOptionPrompt<GetUserReservationsResponseEntry> reservationPrompt = new(reservations, "Which reservation should be cancelled?");
        if (!reservationPrompt.TryRequestInput(out GetUserReservationsResponseEntry? reservation))
        {
            return;
        }
        UserCancelReservationRequest request = new(userIdentifier!, reservation.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("cancel"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserCancelReservationResponse? response = responseMessage.Content.ReadFromJson<UserCancelReservationResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully cancelled reservation {reservation}", ConsoleColor.Green);
    }

    public void Upgrade()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            userIdentifier = InputProvider.RequestStringFor("email");
        }
        GetUserReservationsRequest listRequest = new(userIdentifier!);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("get-reservations"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetUserReservationsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserReservationsResponse>();
        GetUserReservationsResponseEntry[] reservations = listResponse.AssertIsValid().Reservations;
        InputOptionPrompt<GetUserReservationsResponseEntry> reservationPrompt = new(reservations, "Which reservation should be upgraded?");
        if (!reservationPrompt.TryRequestInput(out GetUserReservationsResponseEntry? reservation))
        {
            return;
        }
        UserUpgradeReservationRequest request = new(userIdentifier!, reservation.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("upgrade"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserUpgradeReservationResponse? response = responseMessage.Content.ReadFromJson<UserUpgradeReservationResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully upgraded reservation {reservation}", ConsoleColor.Green);
    }
}
