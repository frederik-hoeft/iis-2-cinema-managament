using IIS.Client.Cli.Commands.User.Booking;
using System;
using System.Collections.Generic;
using System.CommandLine;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Cli.Commands.User.Account;

internal class UserAccountCommand : CommandBase<UserAccountCommand, UserAccountCommandHandler>, ICliCommandBuilder
{
    public Option<string?> UserIdentity { get; }

    public Argument<UserAccountOperation> Operation { get; private set; } = null!;

    public UserAccountCommand(Option<string?> userIdentity) => UserIdentity = userIdentity;

    public Command Build()
    {
        Command command = new("account", "perform an account-management operation.");
        Operation = new Argument<UserAccountOperation>("operation", () => UserAccountOperation.Show, "the account operation to be performed.");
        command.AddArgument(Operation);
        return RegisterHandler(this, command);
    }
}
