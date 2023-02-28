using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Create;
internal class UserCreateCommand : UserSubCommandBase<UserCreateCommand, UserCreateCommandHandler>
{
    public Argument<UserCreateCommandTarget> Target { get; private set; } = null!;

    public UserCreateCommand(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        // TODO
        Command command = new("create", "TODO");
        Target = new Argument<UserCreateCommandTarget>("target", () => UserCreateCommandTarget.User, "TODO");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}
