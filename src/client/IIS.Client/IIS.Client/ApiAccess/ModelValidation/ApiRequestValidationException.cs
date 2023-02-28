namespace IIS.Client.ApiAccess.ModelValidation;

internal class ApiRequestValidationException : Exception
{
    public ApiRequestValidationException(string message) : base(message)
    {
    }
}
