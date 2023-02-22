using IIS.Client.ApiAccess.Network;

namespace IIS.Client.ApiAccess.Operations.Management;

internal interface IManagementOperation
{
    static abstract void Create(ApiContext apiContext);

    static abstract void Delete(ApiContext apiContext);

    static abstract void Update(ApiContext apiContext);

    static abstract void ShowAll(ApiContext apiContext);
}
