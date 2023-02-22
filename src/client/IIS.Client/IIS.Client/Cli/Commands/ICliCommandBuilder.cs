using System.CommandLine;

namespace IIS.Client.Cli.Commands;

internal interface ICliCommandBuilder
{
    Command Build();
}
