using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class SeatRowOperation : OperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("seat-row");

    public SeatRowOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Rows;

    public void Create()
    {
        using HttpRequestMessage availableHallsRequestMessage = new(HttpMethod.Get, Uri.CombineWith("available-halls"));
        using HttpResponseMessage availableHallsResponseMessage = ApiContext.HttpClient.Send(availableHallsRequestMessage);
        GetAvailableHallsResponse? availableHallsResponse = availableHallsResponseMessage.Content.ReadFromJson<GetAvailableHallsResponse>();
        GetAvailableHallsResponseEntry[] availableHalls = availableHallsResponse.AssertIsValid().Halls;
        InputOptionPrompt<GetAvailableHallsResponseEntry> hallPrompt = new(availableHalls, "In which cinema hall should the new seat row be created?");
        if (!hallPrompt.TryRequestInput(out GetAvailableHallsResponseEntry? hall))
        {
            return;
        }
        string? name = InputProvider.RequestValueFor("seat row name");
        InputOptionPrompt<PriceCategory?> priceCategoryPrompt = new(Enum.GetValues(typeof(PriceCategory)).Cast<PriceCategory?>().ToArray(), "Which price category should be used?");
        if (!priceCategoryPrompt.TryRequestInput(out PriceCategory? priceCategory))
        {
            return;
        }
        CreateSeatRowRequest request = new(hall.Id, name!, priceCategory!.Value);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateSeatRowResponse? response = responseMessage.Content.ReadFromJson<CreateSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created seat row {request}", ConsoleColor.Green);
    }

    public void Delete()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
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
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteSeatRowResponse? response = responseMessage.Content.ReadFromJson<DeleteSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted seat row {row}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatRowsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatRowsResponse>();
        GetSeatRowsResponseEntry[] rows = listResponse.AssertIsValid().Rows;
        Stdout.WriteLine("Ok. These are the seat rows:", ConsoleColor.Green);
        foreach (GetSeatRowsResponseEntry row in rows)
        {
            Stdout.WriteLine($"  {row}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetSeatRowsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetSeatRowsResponse>();
        GetSeatRowsResponseEntry[] rows = listResponse.AssertIsValid().Rows;
        InputOptionPrompt<GetSeatRowsResponseEntry> rowPrompt = new(rows, "Which seat row should be updated?");
        if (!rowPrompt.TryRequestInput(out GetSeatRowsResponseEntry? row))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? name = InputProvider.RequestValueFor($"new seat row name [{row.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = row.Name;
        }
        InputOptionPrompt<PriceCategory?> priceCategoryPrompt = new(Enum.GetValues(typeof(PriceCategory)).Cast<PriceCategory?>().ToArray(), $"new seat row price category [{row.PriceCategory}]");
        if (!priceCategoryPrompt.TryRequestInput(out PriceCategory? priceCategory))
        {
            return;
        }
        priceCategory ??= row.PriceCategory;
        UpdateSeatRowRequest request = new(row.Id, name!, priceCategory!.Value);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateSeatRowResponse? response = responseMessage.Content.ReadFromJson<UpdateSeatRowResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated seat row {row} to {request}", ConsoleColor.Green);
    }
}
