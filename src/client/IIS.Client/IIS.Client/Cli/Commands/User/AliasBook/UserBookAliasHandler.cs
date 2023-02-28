using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.User;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.AliasBook;

internal class UserBookAliasHandler : UserSubCommandHandlerBase<UserBookAlias, UserBookAliasHandler>, 
    ICliHandler<UserBookAlias, UserBookAliasHandler>
{
    public UserBookAliasHandler(UserBookAlias command) : base(command)
    {
    }

    public static UserBookAliasHandler Create(UserBookAlias command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity);

    private void Handle(string? userIdentity)
    {
        RemoteOperation remoteOperation = UserBookingOperation.Create(Command.ApiContext, userIdentity).Book;
        RemoteApi.Execute(remoteOperation);
    }
}
