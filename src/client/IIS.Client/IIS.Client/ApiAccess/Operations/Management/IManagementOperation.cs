namespace IIS.Client.ApiAccess.Operations.Management;

internal interface IManagementOperation
{
    static abstract void Create(HttpClient httpClient);

    static abstract void Delete(HttpClient httpClient);

    static abstract void Update(HttpClient httpClient);

    static abstract void ShowAll(HttpClient httpClient);
}
