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
        Command command = new("create", "Creates a new instance of the specified <target>.");
        command.AddAlias("-c");
        command.AddAlias("add");
        Target = new Argument<UserCreateCommandTarget>("target", () => UserCreateCommandTarget.User, "The thing to create.");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}
