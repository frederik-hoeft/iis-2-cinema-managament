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
        Command command = new("customer", "Perform an operation as a cinema customer.");
        command.AddAlias("user");
        UserIdentity = new Option<string?>(new string[] { "--as-identity" , "-i" }, () => null, "Specifies the identity (email address) of the existing user with which to perform the operation.");
        command.AddOption(UserIdentity);
        return RegisterHandler(this, command);
    }
}
