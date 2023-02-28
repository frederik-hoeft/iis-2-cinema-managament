using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management;

public abstract class ManagementSubCommandBase<TCommand, THandler> : CommandBase<TCommand, THandler>
    where TCommand : ManagementSubCommandBase<TCommand, THandler>, IManagementOperationCommand
    where THandler : class, ICliHandler<TCommand, THandler>
{
    protected Argument<ManagementOperationTarget>? Target { get; private set; }

    protected ManagementSubCommandBase(ApiContext apiContext) : base(apiContext)
    {
    }

    protected void AddDefaultArguments(Command command)
    {
        Target = new Argument<ManagementOperationTarget>("target", $"The target to {TCommand.OperationVerbPassive}.");
        command.AddArgument(Target);
    }
}