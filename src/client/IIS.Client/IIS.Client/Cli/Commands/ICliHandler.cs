using System.CommandLine;
using System.CommandLine.Invocation;

namespace IIS.Client.Cli.Commands;

internal interface ICliHandler<TCommand> where TCommand : ICliCommandBuilder
{
    TCommand Command { get; }
}

internal interface ICliHandler<TCommand, THandler> : ICliHandler<TCommand>
    where TCommand : ICliCommandBuilder 
    where THandler : class, ICliHandler<TCommand, THandler> 
{
    void RegisterOn(Command command);

    static abstract THandler Create(TCommand command);
}
