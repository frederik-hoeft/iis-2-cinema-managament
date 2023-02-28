using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.User.CancelAlias;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.AliasCancel;

internal class UserCancelAlias : UserSubCommandBase<UserCancelAlias, UserCancelAliasHandler>
{
    public UserCancelAlias(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        Command command = new("cancel", "An alias for 'delete reservation'.");
        return RegisterHandler(this, command);
    }
}