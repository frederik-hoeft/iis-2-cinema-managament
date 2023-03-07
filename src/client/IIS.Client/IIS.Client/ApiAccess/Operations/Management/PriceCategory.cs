using System.Text.Json;
using System.Text.Json.Serialization;

namespace IIS.Client.ApiAccess.Operations.Management;

internal enum PriceCategory
{
    Stalls = 1,
    Box = 2,
    BoxService = 3
}

// map json string values "STALLS", "BOX", and "BOX_SERVICE"
internal class PriceCategoryConverter : JsonConverter<PriceCategory>
{
    public override PriceCategory Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        // Read the json string value from the reader
        string? jsonValue = reader.GetString();

        // Use switch statement to map the json value to the PriceCategory enum value
        return jsonValue?.ToUpperInvariant() switch
        {
            "STALLS" => PriceCategory.Stalls,
            "BOX" => PriceCategory.Box,
            "BOX_SERVICE" => PriceCategory.BoxService,
            _ => throw new JsonException($"Invalid value '{jsonValue}' for PriceCategory enum.")
        };
    }

    public override void Write(Utf8JsonWriter writer, PriceCategory value, JsonSerializerOptions options) => 
        writer.WriteNumberValue((int)value);
}