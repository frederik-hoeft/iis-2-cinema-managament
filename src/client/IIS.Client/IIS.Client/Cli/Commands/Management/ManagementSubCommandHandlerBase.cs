using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations.Management;
using IIS.Client.ApiAccess.Operations;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;

namespace IIS.Client.Cli.Commands.Management;

public abstract class ManagementSubCommandHandlerBase<TCommand, THandler> : HandlerBase<TCommand> 
    where TCommand : CommandBase<TCommand, THandler>, ICliCommand
    where THandler : ManagementSubCommandHandlerBase<TCommand, THandler>, ICliHandler<TCommand, THandler>
{
    protected ManagementSubCommandHandlerBase(TCommand command) : base(command)
    {
    }

    protected void RegisterDefaults(Command command) =>
        command.Handler = CommandHandler.Create<ManagementOperationTarget>(Handle);

    protected virtual void Handle(ManagementOperationTarget target)
    {
        ManagementOperationChain operationChain = new(Command.ApiContext);
        IManagementOperation operation = operationChain.GetHandler(target);
        RemoteOperation remoteOperation = GetRemoteOperation(operation);
        RemoteApi.Execute(remoteOperation);
    }

    protected abstract RemoteOperation GetRemoteOperation(IManagementOperation operation);
}