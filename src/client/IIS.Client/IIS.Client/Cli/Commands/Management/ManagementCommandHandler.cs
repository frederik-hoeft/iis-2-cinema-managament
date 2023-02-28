using IIS.Client.Cli.Commands.Management.Create;
using IIS.Client.Cli.Commands.Management.Delete;
using IIS.Client.Cli.Commands.Management.Read;
using IIS.Client.Cli.Commands.Management.Update;
using IIS.Client.Cli.Extensions;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management;

internal class ManagementCommandHandler : HandlerBase<ManagementCommand>, ICliHandler<ManagementCommand, ManagementCommandHandler>
{
    private ManagementCommandHandler(ManagementCommand command) : base(command)
    {
    }

    public static ManagementCommandHandler Create(ManagementCommand command) => new(command);

    public void RegisterOn(Command command)
    {
        ICliCommand create = new ManagementCreateCommand(Command.ApiContext);
        command.RegisterSubCommand(create);
        ICliCommand read = new ManagementReadCommand(Command.ApiContext);
        command.RegisterSubCommand(read);
        ICliCommand update = new ManagementUpdateCommand(Command.ApiContext);
        command.RegisterSubCommand(update);
        ICliCommand delete = new ManagementDeleteCommand(Command.ApiContext);
        command.RegisterSubCommand(delete);
    }
}