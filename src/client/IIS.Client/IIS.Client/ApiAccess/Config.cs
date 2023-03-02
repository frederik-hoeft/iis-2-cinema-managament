namespace IIS.Client.ApiAccess;

public record Config(string ApiEndpoint);

public record RuntimeConfig(string ApiEndpoint, bool IsSlave) : Config(ApiEndpoint);