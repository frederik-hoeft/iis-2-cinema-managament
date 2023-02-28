using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User;

internal class UserCommand : CommandBase<UserCommand, UserCommandHandler>
{
    public UserCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    public Option<string?> UserIdentity { get; private set; } = null!;

    public override Command Build()
    {
        Command command = new("user", "perform a user operation.");
        UserIdentity = new Option<string?>("--as-identity", () => null, "specify the identity (email address) of the existing user with which to perform the operation.");
        command.AddGlobalOption(UserIdentity);
        return RegisterHandler(this, command);
    }
}
