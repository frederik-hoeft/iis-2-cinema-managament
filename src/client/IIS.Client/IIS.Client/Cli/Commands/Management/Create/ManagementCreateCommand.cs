﻿using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Management.Create;

internal class ManagementCreateCommand : ManagementSubCommandBase<ManagementCreateCommand, ManagementCreateCommandHandler>, IManagementOperationCommand
{
    public ManagementCreateCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    static string IManagementOperationCommand.OperationVerbPassive => "be created";

    public override Command Build()
    {
        Command command = new("create", "Adds a new instance of <target> to the cinema infrastructure.");
        command.AddAlias("-c");
        command.AddAlias("add");
        AddDefaultArguments(command);
        return RegisterHandler(this, command);
    }
}
