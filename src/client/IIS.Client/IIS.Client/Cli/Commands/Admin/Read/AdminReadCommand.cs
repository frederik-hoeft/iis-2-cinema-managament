using IIS.Client.ApiAccess.Network;
using System.CommandLine;

namespace IIS.Client.Cli.Commands.Admin.Read;

internal class AdminReadCommand : CommandBase<AdminReadCommand, AdminReadCommandHandler>
{
    public AdminReadCommand(ApiContext apiContext) : base(apiContext)
    {
    }

    public override Command Build()
    {
        Command command = new("read", "Displays data regarding the specified <target>.")
        {
            new Argument<AdminReadCommandTarget>("target", () => AdminReadCommandTarget.Revenue, "Inspect the revenue made from a movie or movie screening.")
        };
        command.AddAlias("-r");
        command.AddAlias("show");
        command.AddAlias("display");
        return RegisterHandler(this, command);
    }
}
