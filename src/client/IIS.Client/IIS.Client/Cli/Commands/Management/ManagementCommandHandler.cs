﻿using IIS.Client.ApiAccess.Network;
using IIS.Client.ApiAccess.Operations;
using IIS.Client.ApiAccess.Operations.Management;
using System.CommandLine;
using System.CommandLine.NamingConventionBinder;
using System.IO.Pipes;

namespace IIS.Client.Cli.Commands.Management;

using static CommonOperationProvider;

internal class ManagementCommandHandler : HandlerBase<ManagementCommand>, ICliHandler<ManagementCommand, ManagementCommandHandler>
{
    private ManagementCommandHandler(ManagementCommand command) : base(command)
    {
    }

    public void RegisterOn(Command command) =>
        command.Handler = CommandHandler.Create<ManagementOperationTarget, ManagementOperation>(Handle);

    private void Handle(ManagementOperationTarget target, ManagementOperation operation)
    {
        RemoteOperation remoteOperation = target switch
        {
            ManagementOperationTarget.Movies => ManagementDispatch<MovieOperation>.Parse(operation),
            ManagementOperationTarget.Screenings => ManagementDispatch<MovieScreeningOperation>.Parse(operation),
            ManagementOperationTarget.Rooms => ManagementDispatch<CinemaHallOperation>.Parse(operation),
            ManagementOperationTarget.Rows => ManagementDispatch<SeatRowOperation>.Parse(operation),
            ManagementOperationTarget.Seats => ManagementDispatch<SeatOperation>.Parse(operation),
            _ => Fail($"Undefined management target: '{target}'")
        };
        RemoteApi.Execute(remoteOperation);
    }

    public static ManagementCommandHandler Create(ManagementCommand command) => new(command);
}