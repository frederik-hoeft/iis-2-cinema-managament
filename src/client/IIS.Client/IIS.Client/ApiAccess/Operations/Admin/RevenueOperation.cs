using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Admin;

namespace IIS.Client.ApiAccess.Operations.Admin;

using static CommonOperationProvider;

internal static class RevenueOperation
{
    public static RemoteOperation Parse(AdminOperationTarget target) => target switch
    {
        AdminOperationTarget.Movie => ShowRevenueOfMovie,
        AdminOperationTarget.Screening => ShowRevenueOfMovieScreening,
        _ => Fail($"Undefined revenue target: '{target}'")
    };

    private static void ShowRevenueOfMovie(ApiContext apiContext) => throw new NotImplementedException();

    private static void ShowRevenueOfMovieScreening(ApiContext apiContext) => throw new NotImplementedException();
}
