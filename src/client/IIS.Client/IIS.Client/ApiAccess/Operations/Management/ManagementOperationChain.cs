using IIS.Client.ApiAccess.Network;
using IIS.Client.Cli.Commands.Management;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.ApiAccess.Operations.Management;

using static CommonOperationProvider;

public class ManagementOperationChain
{
    private readonly IManagementOperation[] _handlers;

    public ManagementOperationChain(ApiContext context)
    {
        _handlers = new IManagementOperation[]
        {
            new CinemaHallOperation(context),
            new MovieOperation(context),
            new MovieScreeningOperation(context),
            new SeatOperation(context),
            new SeatRowOperation(context),
        };
    }

    public IManagementOperation GetHandler(ManagementOperationTarget target)
    {
        foreach (IManagementOperation handler in _handlers)
        {
            if (handler.CanHandle(target))
            {
                return handler;
            }
        }
        return new UnsupportedTargetHandler($"Unable to handle target '{target}'.");
    }
}

file readonly record struct UnsupportedTargetHandler(string Message) : IManagementOperation
{
    public bool CanHandle(ManagementOperationTarget target) => true;

    public void Create() => Fail(Message);

    public void Delete() => Fail(Message);

    public void Read() => Fail(Message);

    public void Update() => Fail(Message);
}