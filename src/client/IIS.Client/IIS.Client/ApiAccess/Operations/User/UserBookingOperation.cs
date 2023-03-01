using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.Cli.IO;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.User;

internal class UserBookingOperation : UserBookingOperationBase, IUserOperationFactory<UserBookingOperation>
{
    private UserBookingOperation(ApiContext apiContext, string? userIdentity) : base(apiContext, userIdentity)
    {
    }

    public static UserBookingOperation Create(ApiContext apiContext, string? userIdentity) => 
        new(apiContext, userIdentity);

    public void Book()
    {
        if (GetBookingContext() is not (var userIdentifier, var screening, var seat))
        {
            return;
        }
        UserBookingRequest request = new(userIdentifier, screening.Id, seat.Id);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("book"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UserBookingResponse? response = responseMessage.Content.ReadFromJson<UserBookingResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully booked seat {seat} for screening {screening}", ConsoleColor.Green);
    }

    public void Read()
    {
        string? userIdentifier = UserIdentity;
        if (string.IsNullOrEmpty(userIdentifier))
        {
            using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-bookings"));
            using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
            GetUserBookingsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetUserBookingsResponse>();
            GetUserBookingsResponseEntry[] bookings = listResponse.AssertIsValid().Bookings;
            Stdout.WriteLine("Ok. These are all bookings of all users:", ConsoleColor.Green);
            foreach (GetUserBookingsResponseEntry booking in bookings)
            {
                Stdout.WriteLine($"  {booking}", ConsoleColor.Green);
            }
        }
        else
        {
            GetUserBookingsRequest request = new(userIdentifier!);
            using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("get-bookings"));
            requestMessage.Content = JsonContent.Create(request);
            using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
            GetUserBookingsResponse? response = responseMessage.Content.ReadFromJson<GetUserBookingsResponse>();
            GetUserBookingsResponseEntry[] bookings = response.AssertIsValid().Bookings;
            Stdout.WriteLine($"Ok. These are the user bookings for {userIdentifier}:", ConsoleColor.Green);
            foreach (GetUserBookingsResponseEntry booking in bookings)
            {
                Stdout.WriteLine($"  {booking}", ConsoleColor.Green);
            }
        }
    }
}
