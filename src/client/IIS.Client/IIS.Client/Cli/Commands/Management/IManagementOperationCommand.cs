namespace IIS.Client.Cli.Commands.Management;

public interface IManagementOperationCommand : ICliCommand
{
    internal static abstract string OperationVerbPassive { get; }
}
