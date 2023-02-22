using IIS.Client.ApiAccess.Operations;
using System.Net;

namespace IIS.Client.ApiAccess.Network;

internal static class RemoteApi
{
    private static readonly HttpClient _httpClient;

    static RemoteApi()
    {
        _httpClient = new HttpClient(new SocketsHttpHandler()
        {
            CookieContainer = new CookieContainer(),
            UseCookies = true
        });
    }

    public static void Execute(RemoteOperation operation) => 
        operation.Invoke(_httpClient);
}
