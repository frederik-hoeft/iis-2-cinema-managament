using System.CommandLine;
using System.Reflection;
using IIS.Client.Cli.Extensions;
using IIS.Client.Cli.Commands.Management;
using IIS.Client.Cli.Commands.User;

RootCommand rootCommand = new($"The IIS cinema client v{Assembly.GetExecutingAssembly().GetName().Version}");
rootCommand
    .Register<ManagementCommand>()
    .Register<UserCommand>();
return rootCommand.Invoke(args);
