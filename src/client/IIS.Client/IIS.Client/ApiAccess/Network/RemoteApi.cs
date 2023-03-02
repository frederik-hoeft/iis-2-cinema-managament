using IIS.Client.ApiAccess.ModelValidation;
using IIS.Client.ApiAccess.Operations;
using System.Net;
using IIS.Client.Cli.IO;

namespace IIS.Client.ApiAccess.Network;

internal static class RemoteApi
{
    private static RuntimeConfig? _cfg;

    public static ApiContext CreateApiContext(RuntimeConfig config)
    {
        HttpClient httpClient = new(new SocketsHttpHandler()
        {
            CookieContainer = new CookieContainer(),
            UseCookies = true
        });
        Uri apiBase = new(config.ApiEndpoint);
        _cfg = config;
        return new ApiContext(httpClient, apiBase, config);
    }

    public static void Execute(RemoteOperation operation)
    {
        const string bye = "This was considered fatal. Bye :P";
        try
        {
            operation.Invoke();
        }
        catch (ApiRequestValidationException e)
        {
            const string header = "You supplied invalid values :/";

            if (_cfg is { IsSlave: true})
            {
                using Stream stdErr = Console.OpenStandardError();
                using StreamWriter errWriter = new(stdErr);
                errWriter.WriteLine(header);
                errWriter.WriteLine(e.Message);
                errWriter.WriteLine(bye);
            }
            else
            {
                Stdout.WriteLine(header, ConsoleColor.Red);
                Stdout.WriteLine(e.Message, ConsoleColor.Red);
                Stdout.WriteLine(bye, ConsoleColor.Cyan);
            }
        }
        catch (ApiResponseValidationException e)
        {
            const string header = "The application died :/";

            if (_cfg is { IsSlave: true })
            {
                using Stream stdErr = Console.OpenStandardError();
                using StreamWriter errWriter = new(stdErr);
                errWriter.WriteLine(header);
                errWriter.WriteLine(e.Message);
                errWriter.WriteLine(bye);
            }
            else
            {
                Stdout.WriteLine(header, ConsoleColor.Red);
                Stdout.WriteLine(e.Message, ConsoleColor.Red);
                Stdout.WriteLine(bye, ConsoleColor.Cyan);
            }
        }
        catch (Exception e)
        {
            const string header = "The application died :/";
            string reason = $"Reason: \n{e.Message}";

            if (_cfg is { IsSlave: true })
            {
                using Stream stdErr = Console.OpenStandardError();
                using StreamWriter errWriter = new(stdErr);
                errWriter.WriteLine(header);
                errWriter.WriteLine(reason);
                errWriter.WriteLine(bye);
            }
            else
            {
                Stdout.WriteLine(header, ConsoleColor.Red);
                Stdout.WriteLine(reason, ConsoleColor.Red);
                Stdout.WriteLine($"in: \n{e.StackTrace}", ConsoleColor.Yellow);
                Stdout.WriteLine(bye, ConsoleColor.Cyan);
            }
        }
    }
}
