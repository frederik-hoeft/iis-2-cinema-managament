﻿namespace IIS.Client.ApiAccess.ModelValidation;

internal static class ApiResponseExtensions
{
    public static T AssertIsValid<T>(this T? response) where T : class, IApiResponse
    {
        if (response?.Success is true)
        {
            return response;
        }
        string reason = response is null ? $"Response of type '{typeof(T).Name}' was null" : $"{typeof(T).Name}.Success was false";
        throw new ApiResponseValidationException($"Failed to perform the requested operation. Reason: \"{response?.Error ?? reason}\".");
    }
}