using IIS.Client.ApiAccess.Operations;
using System.Net;

namespace IIS.Client.ApiAccess.Network;

internal static class RemoteApi
{
    public static ApiContext CreateApiContext(Config config)
    {
        HttpClient httpClient = new(new SocketsHttpHandler()
        {
            CookieContainer = new CookieContainer(),
            UseCookies = true
        });
        Uri apiBase = new(config.ApiEndpoint);
        return new ApiContext(httpClient, apiBase);
    }

    public static void Execute(RemoteOperation operation) => 
        operation.Invoke();
}
