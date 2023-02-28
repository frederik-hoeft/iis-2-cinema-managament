using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands;

public abstract class CommandBase : ICliCommand
{
    public ApiContext ApiContext { get; }

    protected CommandBase(ApiContext apiContext) => ApiContext = apiContext;

    public abstract Command Build();
}
