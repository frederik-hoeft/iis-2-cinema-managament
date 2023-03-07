using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;
using System;
using System.Net.Http.Json;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class SeatRowOperation : ManagementOperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("seat-row");

    public SeatRowOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Rows;

    public void Create()
    {
        if (GetHall("In which cinema hall should the new seat row be created?") is not GetCinemaHallsResponseEntry hall)
        {
            return;
        }
        string? name = InputProvider.RequestStringFor("seat row name");
        InputOptionPrompt<PriceCategory?> priceCategoryPrompt = new(Enum.GetValues(typeof(PriceCategory)).Cast<PriceCategory?>().ToArray(), "Which price category should be used?");
        if (!priceCategoryPrompt.TryRequestInput(out PriceCategory? priceCategory))
        {
            return;
        }
        CreateSeatRowRequest request = new(hall.Id, name!, priceCategory!.Value);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateSeatRowResponse? response = responseMessage.Content.ReadFromJson<CreateSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created seat row {request}", ConsoleColor.Green);
    }

    public void Delete()
    {
        if (GetHall("In which cinema hall is the row you want to delete?") is not GetCinemaHallsResponseEntry hall)
        {
            return;
        }
        GetSeatRowsRequest listRequest = new(hall.Id);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("list"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatRowsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatRowsResponse>();
        GetSeatRowsResponseEntry[] rows = listResponse.AssertIsValid().Rows;
        InputOptionPrompt<GetSeatRowsResponseEntry> rowPrompt = new(rows, "Which seat row should be deleted?");
        if (!rowPrompt.TryRequestInput(out GetSeatRowsResponseEntry? row))
        {
            return;
        }
        DeleteSeatRowRequest request = new(row.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteSeatRowResponse? response = responseMessage.Content.ReadFromJson<DeleteSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted seat row {row}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-full"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatRowsFullResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatRowsFullResponse>();
        GetSeatRowsFullResponseEntry[] rows = listResponse.AssertIsValid().Rows;
        Stdout.WriteLine("Ok. These are the seat rows:", ConsoleColor.Green);
        foreach (GetSeatRowsFullResponseEntry row in rows)
        {
            Stdout.WriteLine($"  {row with { Seats = null! }}", ConsoleColor.Green);
            foreach (GetSeatsResponseEntry seat in row.Seats)
            {
                Stdout.WriteLine($"    {seat}", ConsoleColor.Green);
            }
        }
    }

    public void Update()
    {
        if (GetHall("In which cinema hall is the seat row you want to update?") is not GetCinemaHallsResponseEntry hall)
        {
            return;
        }
        GetSeatRowsRequest listRequest = new(hall.Id);
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Post, Uri.CombineWith("list"));
        listRequestMessage.Content = JsonContent.Create(listRequest);
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatRowsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatRowsResponse>();
        GetSeatRowsResponseEntry[] rows = listResponse.AssertIsValid().Rows;
        InputOptionPrompt<GetSeatRowsResponseEntry> rowPrompt = new(rows, "Which seat row should be updated?");
        if (!rowPrompt.TryRequestInput(out GetSeatRowsResponseEntry? row))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? name = InputProvider.RequestStringFor($"new seat row name [{row.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = row.Name;
        }
        InputOptionPrompt<PriceCategory?> priceCategoryPrompt = new(Enum.GetValues(typeof(PriceCategory)).Cast<PriceCategory?>().ToArray(), $"new seat row price category [{row.Price}]");
        if (!priceCategoryPrompt.TryRequestInput(out PriceCategory? priceCategory))
        {
            return;
        }
        priceCategory ??= row.Price;
        UpdateSeatRowRequest request = new(row.Id, name!, priceCategory!.Value);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        requestMessage.Content = JsonContent.Create(request);
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateSeatRowResponse? response = responseMessage.Content.ReadFromJson<UpdateSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated seat row {row} to {request}", ConsoleColor.Green);
    }

    private GetCinemaHallsResponseEntry? GetHall(string prompt)
    {
        using HttpRequestMessage availableHallsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-halls"));
        using HttpResponseMessage availableHallsResponseMessage = ApiContext.HttpClient.Send(availableHallsRequestMessage);
        GetCinemaHallsResponse? availableHallsResponse = availableHallsResponseMessage.Content.ReadFromJson<GetCinemaHallsResponse>();
        GetCinemaHallsResponseEntry[] availableHalls = availableHallsResponse.AssertIsValid().CinemaHalls;
        InputOptionPrompt<GetCinemaHallsResponseEntry> hallPrompt = new(availableHalls, prompt);
        hallPrompt.TryRequestInput(out GetCinemaHallsResponseEntry? hall);
        return hall;
    }
}
