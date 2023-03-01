using System.CommandLine;

namespace IIS.Client.Cli.Commands;

public interface ICliHandler<TCommand> where TCommand : ICliCommand
{
    TCommand Command { get; }
}

public interface ICliHandler<TCommand, THandler> : ICliHandler<TCommand>
    where TCommand : ICliCommand 
    where THandler : class, ICliHandler<TCommand, THandler> 
{
    void RegisterOn(Command command);

    static abstract THandler Create(TCommand command);
}
