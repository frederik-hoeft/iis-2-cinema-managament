namespace IIS.Client.ApiAccess.ModelValidation;

internal interface IApiResponse
{
    bool Success { get; }

    string? Error { get; }
}
