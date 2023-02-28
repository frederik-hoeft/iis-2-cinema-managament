using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.AliasBook;

internal class UserBookAlias : UserSubCommandBase<UserBookAlias, UserBookAliasHandler>
{
    public UserBookAlias(ApiContext apiContext, Option<string?> userIdentity) : base(apiContext, userIdentity)
    {
    }

    public override Command Build()
    {
        Command command = new("book", "An alias for 'create booking'.");
        return RegisterHandler(this, command);
    }
}