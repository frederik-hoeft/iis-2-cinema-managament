using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.User;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User.Account;

using static CommonOperationProvider;

internal class UserAccountCommandHandler : HandlerBase<UserAccountCommand>, ICliHandler<UserAccountCommand, UserAccountCommandHandler>
{
    public UserAccountCommandHandler(UserAccountCommand command) : base(command)
    {
    }

    public static UserAccountCommandHandler Create(UserAccountCommand command) => new(command);

    public void RegisterOn(Command command) => command.SetHandler(Handle, Command.UserIdentity, Command.Operation);

    private void Handle(string? userIdentifier, UserAccountOperation accountOperation)
    {
        AccountOperation operation = new(userIdentifier);
        RemoteOperation remoteOperation = accountOperation switch
        {
            UserAccountOperation.Show => operation.Show,
            UserAccountOperation.Delete => operation.Delete,
            UserAccountOperation.Register => operation.Register,
            _ => Fail($"Undefined account operation: '{accountOperation}'")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
