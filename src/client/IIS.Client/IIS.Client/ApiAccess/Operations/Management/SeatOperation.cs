using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class SeatOperation : ManagementOperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("seat");

    public SeatOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Seats;

    public void Create()
    {
        if (GetContext() is not (int, int rowId))
        {
            return;
        }
        string? name = InputProvider.RequestStringFor("seat name");
        CreateSeatRequest request = new(rowId, name!);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateSeatResponse? response = responseMessage.Content.ReadFromJson<CreateSeatResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created seat {request}", ConsoleColor.Green);
    }

    public void Delete()
    {
        if (GetContext() is not (int, int rowId))
        {
            return;
        }
        GetSeatsRequest listRequest = new(rowId);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("list"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatsResponse>();
        GetSeatsResponseEntry[] seats = listResponse.AssertIsValid().Seats;
        InputOptionPrompt<GetSeatsResponseEntry> seatPrompt = new(seats, "Which seat should be deleted?");
        if (!seatPrompt.TryRequestInput(out GetSeatsResponseEntry? seat))
        {
            return;
        }
        DeleteSeatRequest request = new(seat.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteSeatResponse? response = responseMessage.Content.ReadFromJson<DeleteSeatResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted seat {seat}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatsResponse>();
        GetSeatsResponseEntry[] seats = listResponse.AssertIsValid().Seats;
        Stdout.WriteLine("Ok. These are the seats:", ConsoleColor.Green);
        foreach (GetSeatsResponseEntry seat in seats)
        {
            Stdout.WriteLine($"  {seat}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        if (GetContext() is not (int, int rowId))
        {
            return;
        }
        GetSeatsRequest listRequest = new(rowId);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("list"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatsResponse>();
        GetSeatsResponseEntry[] seats = listResponse.AssertIsValid().Seats;
        InputOptionPrompt<GetSeatsResponseEntry> seatPrompt = new(seats, "Which seat should be updated?");
        if (!seatPrompt.TryRequestInput(out GetSeatsResponseEntry? seat))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? name = InputProvider.RequestStringFor($"new seat name [{seat.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = seat.Name;
        }
        UpdateSeatRequest request = new(seat.Id, name!);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateSeatResponse? response = responseMessage.Content.ReadFromJson<UpdateSeatResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated seat {seat} to {request}", ConsoleColor.Green);
    }

    private (int HallId, int RowId)? GetContext()
    {
        using HttpRequestMessage availableHallsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-halls"));
        using HttpResponseMessage availableHallsResponseMessage = ApiContext.HttpClient.Send(availableHallsRequestMessage);
        GetCinemaHallsResponse? availableHallsResponse = availableHallsResponseMessage.Content.ReadFromJson<GetCinemaHallsResponse>();
        GetCinemaHallsResponseEntry[] availableHalls = availableHallsResponse.AssertIsValid().CinemaHalls;
        InputOptionPrompt<GetCinemaHallsResponseEntry> hallPrompt = new(availableHalls, "Which cinema hall should be used?");
        if (!hallPrompt.TryRequestInput(out GetCinemaHallsResponseEntry? hall))
        {
            return null;
        }
        GetSeatRowRequest availableRowsRequest = new(hall.Id);
        using HttpRequestMessage availableRowsRequestMessage = new(HttpMethod.Post, Uri.CombineWith("available-rows"));
        using HttpResponseMessage availableRowsResponseMessage = ApiContext.HttpClient.Send(availableRowsRequestMessage);
        GetSeatRowsResponse? availableRowsResponse = availableRowsResponseMessage.Content.ReadFromJson<GetSeatRowsResponse>();
        GetSeatRowsResponseEntry[] availableRows = availableRowsResponse.AssertIsValid().Rows;
        InputOptionPrompt<GetSeatRowsResponseEntry> rowPrompt = new(availableRows, "Which seat row should be used?");
        return !rowPrompt.TryRequestInput(out GetSeatRowsResponseEntry? row) 
            ? null 
            : (hall.Id, row.Id);
    }
}
