namespace IIS.Client.ApiAccess.Operations;
internal static class CommonOperationProvider
{
    public static RemoteOperation Fail(string message) => (_) => Console.WriteLine(message);
}
