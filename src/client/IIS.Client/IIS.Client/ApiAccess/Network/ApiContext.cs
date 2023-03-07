namespace IIS.Client.ApiAccess.Network;

public record ApiContext(HttpClient HttpClient, Uri ApiBase, RuntimeConfig Config);