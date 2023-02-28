using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Admin;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;

namespace IIS.Client.Cli.Commands.Admin.Read;

using static CommonOperationProvider;

internal class AdminReadCommandHandler : HandlerBase<AdminReadCommand>, ICliHandler<AdminReadCommand, AdminReadCommandHandler>
{
    public AdminReadCommandHandler(AdminReadCommand command) : base(command)
    {
    }

    public static AdminReadCommandHandler Create(AdminReadCommand command) => new(command);

    public void RegisterOn(Command command) =>
        command.Handler = CommandHandler.Create<AdminReadCommandTarget>(Handle);

    private void Handle(AdminReadCommandTarget target)
    {
        RemoteOperation remoteOperation = target switch
        {
            AdminReadCommandTarget.Revenue => new AdminRevenueOperation(Command.ApiContext).ShowRevenue,
            _ => Fail($"Unable to handle read target '{target}'.")
        };
        RemoteApi.Execute(remoteOperation);
    }
}
