namespace IIS.Client.ApiAccess.ModelValidation;

internal static class ApiResponseExtensions
{
    public static T AssertIsValid<T>(this T? response) where T : class, IApiResponse
    {
        if (response?.Success is true)
        {
            return response;
        }
        string reason = response is null ? $"Response of type '{typeof(T).Name}' was null" : $"{typeof(T).Name}.Success was false";
        string? error = response?.Error ?? "No details could be read. The cause is probably an internal server error. Go fix your code.";
        throw new ApiResponseValidationException($"Failed to perform the requested operation. Reason: \"{reason}\". Details: \"{error}\"");
    }
}