using System.CommandLine;

namespace IIS.Client.Cli.Commands;

internal interface ICliCommand
{
    static abstract Command CreateCommand();
}
