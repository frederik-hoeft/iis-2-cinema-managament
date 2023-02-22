using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Management;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;
using System.IO.Pipes;

namespace IIS.Client.Cli.Commands.Management;

using static CommonOperationProvider;

internal class ManagementCommandHandler : ICliHandler
{
    public static void RegisterOn(Command command) =>
        command.Handler = CommandHandler.Create<ManagementTarget, ManagementOperation>(Handle);

    private static void Handle(ManagementTarget target, ManagementOperation operation)
    {
        RemoteOperation remoteOperation = target switch
        {
            ManagementTarget.Movies => ManagementDispatch<MovieOperation>.Parse(operation),
            ManagementTarget.Screenings => ManagementDispatch<MovieScreeningOperation>.Parse(operation),
            ManagementTarget.Rooms => ManagementDispatch<CinemaHallOperation>.Parse(operation),
            ManagementTarget.Rows => ManagementDispatch<SeatRowOperation>.Parse(operation),
            ManagementTarget.Seats => ManagementDispatch<SeatOperation>.Parse(operation),
            _ => Fail($"Undefined management target: '{target}'")
        };
        RemoteApi.Execute(remoteOperation);
    }
}