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
        Command command = new("list", "Lists all existing instances of the specified <target>.");
        command.AddAlias("-l");
        command.AddAlias("show");
        command.AddAlias("view");
        command.AddAlias("display");
        Target = new Argument<UserReadCommandTarget>("target", () => UserReadCommandTarget.Users, "The things to be listed");
        command.AddArgument(Target);
        return RegisterHandler(this, command);
    }
}