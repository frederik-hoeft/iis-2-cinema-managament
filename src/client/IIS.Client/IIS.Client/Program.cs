using System.CommandLine;
using System.Reflection;
using IIS.Client.Cli.Extensions;
using IIS.Client.Cli.Commands.User.Booking;
using IIS.Client.Cli.Commands.Management;

RootCommand rootCommand = new($"The IIS cinema client v{Assembly.GetExecutingAssembly().GetName().Version}");
rootCommand
    .Register<ManagementCommand>()
    .Register<UserBookingCommand>();
return rootCommand.Invoke(args);
