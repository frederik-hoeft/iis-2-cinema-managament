using System.CommandLine;

namespace IIS.Client.Cli.Commands.Admin;

internal class AdminCommand : CommandBase<AdminCommand, AdminCommandHandler>, ICliCommandBuilder
{
    public Command Build()
    {
        Command command = new("admin", "The admin API to perform operational tasks like inspecting revenue.")
        {
            new Argument<AdminOperationTarget>("target", "The target of the operational task."),
            new Argument<AdminOperation>("operation", "The operational task to perform on the target.")
        };
        return RegisterHandler(this, command);
    }
}
