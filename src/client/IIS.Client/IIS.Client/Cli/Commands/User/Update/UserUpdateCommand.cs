using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Upgrade;

internal class UserUpdateCommand : UserSubCommandBase<UserUpdateCommand, UserUpdateCommandHandler>
{
    public Argument<UserUpdateCommandTarget> Target { get; private set; } = null!;

    public UserUpdateCommand(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        // TODO
        Command command = new("update", "TODO");
        command.AddAlias("upgrade");
        Target = new Argument<UserUpdateCommandTarget>("target", () => UserUpdateCommandTarget.Reservation, "TODO");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}