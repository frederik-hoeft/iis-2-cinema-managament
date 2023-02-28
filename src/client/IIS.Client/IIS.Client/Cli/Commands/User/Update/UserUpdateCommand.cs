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
        Command command = new("upgrade", "Upgrades the specified reservation to a booking.");
        command.AddAlias("update");
        Target = new Argument<UserUpdateCommandTarget>("target", () => UserUpdateCommandTarget.Reservation, "The resevation to upgrade.");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}