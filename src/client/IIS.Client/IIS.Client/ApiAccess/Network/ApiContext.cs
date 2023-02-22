namespace IIS.Client.ApiAccess.Network;

public class ApiContext
{
    public ApiContext(HttpClient httpClient, Uri apiBase)
    {
        HttpClient = httpClient;
        ApiBase = apiBase;
    }

    public HttpClient HttpClient { get; }

    public Uri ApiBase { get; }
}
