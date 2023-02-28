using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Read;

internal class UserReadCommand : UserSubCommandBase<UserReadCommand, UserReadCommandHandler>
{
    public Argument<UserReadCommandTarget> Target { get; private set; } = null!;

    public UserReadCommand(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        // TODO
        Command command = new("read", "TODO");
        Target = new Argument<UserReadCommandTarget>("target", () => UserReadCommandTarget.Users, "TODO");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}