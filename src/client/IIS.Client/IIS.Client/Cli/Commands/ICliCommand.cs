﻿using System.CommandLine;

namespace IIS.Client.Cli.Commands;

public interface ICliCommand
{
    Command Build();
}
