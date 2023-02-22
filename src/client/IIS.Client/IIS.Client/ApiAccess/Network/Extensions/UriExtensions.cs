namespace IIS.Client.ApiAccess.Network.Extensions;

internal static class UriExtensions
{
    public static Uri CombineWith(this Uri uri, string path) =>
        uri.AbsoluteUri.EndsWith('/')
            ? new Uri(uri.AbsoluteUri + path)
            : new Uri($"{uri.AbsoluteUri}/{path}");
}
