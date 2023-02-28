using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Admin;

namespace IIS.Client.ApiAccess.Operations.Admin;

using static CommonOperationProvider;

internal class AdminRevenueOperation : OperationBase
{
    public AdminRevenueOperation(ApiContext apiContext) : base(apiContext)
    {
    }

    public void ShowRevenue() => throw new NotImplementedException();

    private void ShowRevenueOfMovie() => throw new NotImplementedException();

    private void ShowRevenueOfMovieScreening() => throw new NotImplementedException();
}
