using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.AliasReserve;

internal class UserReserveAlias : UserSubCommandBase<UserReserveAlias, UserReserveAliasHandler>
{
    public UserReserveAlias(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        Command command = new("reserve", "An alias for 'create reservation'.");
        return RegisterHandler(this, command);
    }
}