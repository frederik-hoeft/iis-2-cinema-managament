using IIS.Client.Cli.Commands.User.Create;
using IIS.Client.Cli.Commands.User.Delete;
using IIS.Client.Cli.Commands.User.Read;
using IIS.Client.Cli.Commands.User.Upgrade;
using IIS.Client.Cli.Extensions;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.User;

internal class UserCommandHandler : HandlerBase<UserCommand>, ICliHandler<UserCommand, UserCommandHandler>
{
    private UserCommandHandler(UserCommand command) : base(command)
    {
    }

    public static UserCommandHandler Create(UserCommand command) => new(command);

    public void RegisterOn(Command command)
    {
        ICliCommand create = new UserCreateCommand(Command.ApiContext, Command.UserIdentity);
        command.RegisterSubCommand(create);
        ICliCommand read = new UserReadCommand(Command.ApiContext, Command.UserIdentity);
        command.RegisterSubCommand(read);
        ICliCommand update = new UserUpdateCommand(Command.ApiContext, Command.UserIdentity);
        command.RegisterSubCommand(update);
        ICliCommand delete = new UserDeleteCommand(Command.ApiContext, Command.UserIdentity);
        command.RegisterSubCommand(delete);
    }
}