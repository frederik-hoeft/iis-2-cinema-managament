using System.Text.Json;

namespace IIS.Client.ApiAccess.Network.Extensions;

internal static class HttpContentExtensions
{
    private static readonly JsonSerializerOptions _options = new()
    {
        PropertyNameCaseInsensitive = true,
    };

    public static T? ReadFromJson<T>(this HttpContent content)
    {
        using Stream stream = content.ReadAsStream();
        return JsonSerializer.Deserialize<T>(stream, _options);
    }
}
