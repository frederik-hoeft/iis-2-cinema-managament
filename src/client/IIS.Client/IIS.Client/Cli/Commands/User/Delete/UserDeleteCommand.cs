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
        // TODO
        Command command = new("delete", "TODO");
        Target = new Argument<UserDeleteCommandTarget>("target", () => UserDeleteCommandTarget.User, "TODO");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}