namespace IIS.Client.ApiAccess;

public record RuntimeConfig(string ApiEndpoint, bool IsSlave, string ProcessName);