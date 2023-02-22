using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.ApiAccess.Operations;

internal interface IOperationFactory<T> where T : Enum
{
    static abstract RemoteOperation Parse(T operation);
}
