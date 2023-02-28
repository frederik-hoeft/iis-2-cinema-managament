using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Management;

namespace IIS.Client.ApiAccess.Operations.Management;

internal class MovieOperation : OperationBase, IManagementOperation
{
    public MovieOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public bool CanHandle(ManagementOperationTarget target) =>
        target is ManagementOperationTarget.Movies;

    public void Create() => throw new NotImplementedException();
    public void Delete() => throw new NotImplementedException();
    public void Read() => throw new NotImplementedException();
    public void Update() => throw new NotImplementedException();
}
