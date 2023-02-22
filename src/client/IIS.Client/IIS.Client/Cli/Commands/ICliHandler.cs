using System.CommandLine;
using System.CommandLine.Invocation;

namespace IIS.Client.Cli.Commands;

internal interface ICliHandler
{
    static abstract void RegisterOn(Command command);
}
