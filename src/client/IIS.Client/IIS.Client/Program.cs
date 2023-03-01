using System.CommandLine;
using System.Reflection;
using IIS.Client.Cli.Extensions;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.Commands.User;
using IIS.Client.Cli.Commands.Admin;
using System.Text.Json;
using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess;
using IIS.Client.Cli.Commands;
using IIS.Client.Cli.IO;

namespace IIS.Client;

public class Program
{
    public static Version? Version { get; }

    static Program()
    {
        Version = Assembly.GetExecutingAssembly().GetName().Version;
    }

    public static int Main(string[] args)
    {
        Stdout.WriteLine($"Welcome to the IIS cinema client v{Version}!");
        Command rootCommand = BuildCommandTree();
        return rootCommand.Invoke(args);
    }

    public static Command BuildCommandTree()
    {
        const string CFG_FILE_NAME = "config.json";

        Config? config;
        using (Stream configStream = File.OpenRead(CFG_FILE_NAME))
        {
            config = JsonSerializer.Deserialize<Config>(configStream);
        }
        _ = config ?? throw new FormatException($"'{CFG_FILE_NAME}' has an invalid format and could not be read!");

        ApiContext apiContext = RemoteApi.CreateApiContext(config);

        RootCommand rootCommand = new($"The IIS cinema client v{Version}");

        ICliCommand management = new ManagementCommand(apiContext);
        rootCommand.RegisterSubCommand(management);
        ICliCommand user = new UserCommand(apiContext);
        rootCommand.RegisterSubCommand(user);
        ICliCommand admin = new AdminCommand(apiContext);
        rootCommand.RegisterSubCommand(admin);

        return rootCommand;
    }
}