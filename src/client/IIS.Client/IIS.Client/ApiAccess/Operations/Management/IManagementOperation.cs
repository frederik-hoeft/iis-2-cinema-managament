using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Management;

namespace IIS.Client.ApiAccess.Operations.Management;

public interface IManagementOperation
{
    void Create();

    void Delete();

    void Update();

    void Read();

    bool CanHandle(ManagementOperationTarget target);
}
