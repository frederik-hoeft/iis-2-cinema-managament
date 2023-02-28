using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.User.Create;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Delete;

internal class UserDeleteCommand : UserSubCommandBase<UserDeleteCommand, UserDeleteCommandHandler>
{
    public Argument<UserDeleteCommandTarget> Target { get; private set; } = null!;

    public UserDeleteCommand(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        Command command = new("delete", "Deletes an existing instance of the specified <target>.");
        Target = new Argument<UserDeleteCommandTarget>("target", () => UserDeleteCommandTarget.User, "The thing to delete.");
        command.AddAlias("-d");
        command.AddAlias("remove");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}