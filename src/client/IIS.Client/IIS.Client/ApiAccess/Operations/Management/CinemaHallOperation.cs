using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Network.Extensions;
using IIS.Client.ApiAccess.Operations.Management.Requests;
using IIS.Client.ApiAccess.Operations.Management.Responses;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.IO;
using IIS.Client.Cli.Utils;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class CinemaHallOperation : ManagementOperationBase, IManagementOperation
{
    protected override Uri Uri => base.Uri.CombineWith("cinema-hall");

    public CinemaHallOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Halls;

    public void Create()
    {
        string? id = InputProvider.RequestStringFor("cinema hall identifier");
        bool isAvailable = InputProvider.RequestBoolFor("is the cinema hall available for booking?", false);
        CreateCinemaHallRequest request = new(id!, isAvailable);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("create"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        CreateCinemaHallResponse? response = responseMessage.Content.ReadFromJson<CreateCinemaHallResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully created cinema hall with identifier {id}", ConsoleColor.Green);
    }

    public void Delete()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetCinemaHallsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetCinemaHallsResponse>();
        GetCinemaHallsResponseEntry[] halls = listResponse.AssertIsValid().CinemaHalls;
        InputOptionPrompt<GetCinemaHallsResponseEntry> hallPrompt = new(halls, "Which cinema hall should be deleted?");
        if (!hallPrompt.TryRequestInput(out GetCinemaHallsResponseEntry? hall))
        {
            return;
        }
        DeleteCinemaHallRequest request = new(hall.Id);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("delete"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        DeleteCinemaHallResponse? response = responseMessage.Content.ReadFromJson<DeleteCinemaHallResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully deleted cinema hall {hall}", ConsoleColor.Green);
    }

    public void Read()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list-full"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetCinemaHallsFullResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetCinemaHallsFullResponse>();
        GetCinemaHallsFullResponseEntry[] halls = listResponse.AssertIsValid().CinemaHalls;
        Stdout.WriteLine("Ok. These are the cinema halls:", ConsoleColor.Green);
        foreach (GetCinemaHallsFullResponseEntry hall in halls)
        {
            Stdout.WriteLine($"  {hall}", ConsoleColor.Green);
        }
    }

    public void Update()
    {
        using HttpRequestMessage listRequestMessage = new(HttpMethod.Get, Uri.CombineWith("list"));
        using HttpResponseMessage listResponseMessage = ApiContext.HttpClient.Send(listRequestMessage);
        GetCinemaHallsResponse? listResponse = listResponseMessage.Content.ReadFromJson<GetCinemaHallsResponse>();
        GetCinemaHallsResponseEntry[] halls = listResponse.AssertIsValid().CinemaHalls;
        InputOptionPrompt<GetCinemaHallsResponseEntry> hallPrompt = new(halls, "Which cinema hall should be updated?");
        if (!hallPrompt.TryRequestInput(out GetCinemaHallsResponseEntry? hall))
        {
            return;
        }
        Stdout.WriteLine("Enter new values, or press <enter> to keep current ones.");
        string? name = InputProvider.RequestStringFor($"new cinema hall identifier [{hall.Name}]");
        if (string.IsNullOrEmpty(name))
        {
            name = hall.Name;
        }
        bool isAvailable = InputProvider.RequestBoolFor("is the cinema hall available for booking?", hall.IsAvailable);
        UpdateCinemaHallRequest request = new(hall.Id, name, isAvailable);
        ValidationService.AssertIsValid(request);
        using HttpRequestMessage requestMessage = new(HttpMethod.Post, Uri.CombineWith("update"));
        using HttpResponseMessage responseMessage = ApiContext.HttpClient.Send(requestMessage);
        UpdateCinemaHallResponse? response = responseMessage.Content.ReadFromJson<UpdateCinemaHallResponse>();
        response.AssertIsValid();
        Stdout.WriteLine($"Ok. Successfully updated cinema hall {hall} to {request}", ConsoleColor.Green);
    }
}
