using System.CommandLine;

namespace IIS.Client.Cli.Commands.User;

internal class UserCommand : CommandBase<UserCommand, UserCommandHandler>, ICliCommandBuilder
{
    public Option<string?> UserIdentity { get; private set; } = null!;

    public Command Build()
    {
        Command command = new("user", "perform a user operation.");
        UserIdentity = new Option<string?>("--as-identity", () => null, "specify the identity (email address) of the existing user with which to perform the operation.");
        command.AddGlobalOption(UserIdentity);
        RegisterHandler(this, command);
        return command;
    }
}
