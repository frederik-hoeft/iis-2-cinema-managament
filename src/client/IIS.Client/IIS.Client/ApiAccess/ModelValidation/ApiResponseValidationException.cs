namespace IIS.Client.ApiAccess.ModelValidation;

internal class ApiResponseValidationException : Exception
{
    public ApiResponseValidationException(string message) : base(message)
    {
    }
}
