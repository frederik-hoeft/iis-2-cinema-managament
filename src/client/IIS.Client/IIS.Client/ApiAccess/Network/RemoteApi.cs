using IIS.Client.ApiAccess.Operations;
using System.Net;

namespace IIS.Client.ApiAccess.Network;

internal static class RemoteApi
{
    private static ApiContext? _apiContext;

    public static void LoadConfig(Config config)
    {
        HttpClient httpClient = new(new SocketsHttpHandler()
        {
            CookieContainer = new CookieContainer(),
            UseCookies = true
        });
        Uri apiBase = new(config.ApiEndpoint);
        _apiContext = new ApiContext(httpClient, apiBase);
    }

    public static void Execute(RemoteOperation operation)
    {
        _ = _apiContext ?? throw new InvalidOperationException("config was not loaded!");
        operation.Invoke(_apiContext);
    }
}
