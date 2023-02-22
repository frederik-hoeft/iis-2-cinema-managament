using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Admin;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;

namespace IIS.Client.Cli.Commands.Admin;

using static CommonOperationProvider;

internal class AdminCommandHandler : HandlerBase<AdminCommand>, ICliHandler<AdminCommand, AdminCommandHandler>
{
    public AdminCommandHandler(AdminCommand command) : base(command)
    {
    }

    public static AdminCommandHandler Create(AdminCommand command) => new(command);

    public void RegisterOn(Command command) => command.Handler = CommandHandler.Create(Handle);

    private void Handle(AdminOperationTarget target, AdminOperation operation)
    {
        RemoteOperation remoteOperation = operation switch
        {
            AdminOperation.Revenue => RevenueOperation.Parse(target),
            _ => Fail($"Undefined management target: '{target}'")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
