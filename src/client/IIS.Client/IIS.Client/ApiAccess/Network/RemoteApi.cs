using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations;
using System.Net;
using IIS.Client.Cli.IO;

namespace IIS.Client.ApiAccess.Network;

internal static class RemoteApi
{
    public static ApiContext CreateApiContext(Config config)
    {
        HttpClient httpClient = new(new SocketsHttpHandler()
        {
            CookieContainer = new CookieContainer(),
            UseCookies = true
        });
        Uri apiBase = new(config.ApiEndpoint);
        return new ApiContext(httpClient, apiBase);
    }

    public static void Execute(RemoteOperation operation)
    {
        try
        {
            operation.Invoke();
        }
        catch (ApiRequestValidationException e)
        {
            Stdout.WriteLine("You supplied invalid values :/", ConsoleColor.Red);
            Stdout.WriteLine(e.Message, ConsoleColor.Red);
            Stdout.WriteLine("This was considered fatal. Bye :P", ConsoleColor.Cyan);
        }
        catch (ApiResponseValidationException e)
        {
            Stdout.WriteLine("The application died :/", ConsoleColor.Red);
            Stdout.WriteLine(e.Message, ConsoleColor.Red);
            Stdout.WriteLine("This was considered fatal. Bye :P", ConsoleColor.Cyan);
        }
        catch (Exception e)
        {
            Stdout.WriteLine("The application died :/", ConsoleColor.Red);
            Stdout.WriteLine($"Reason: \n{e.Message}", ConsoleColor.Red);
            Stdout.WriteLine($"in: \n{e.StackTrace}", ConsoleColor.Yellow);
            Stdout.WriteLine("This was considered fatal. Bye :P", ConsoleColor.Cyan);
        }
    }
}
