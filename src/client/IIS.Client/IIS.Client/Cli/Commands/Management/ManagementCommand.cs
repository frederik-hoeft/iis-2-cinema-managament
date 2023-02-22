using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management;

internal class ManagementCommand : CommandBase<ManagementCommand, ManagementCommandHandler>, ICliCommandBuilder
{
    public Command Build()
    {
        Command command = new("manage", "The admin management API to create, update, or delete elements of the cinema infrastructure.")
        {
            new Argument<ManagementOperationTarget>("target", "The element of the cinema infrastructure to be managed."),
            new Argument<ManagementOperation>("operation", () => ManagementOperation.Show, "What to do with the target.")
        };
        return RegisterHandler(this, command);
    }
}
