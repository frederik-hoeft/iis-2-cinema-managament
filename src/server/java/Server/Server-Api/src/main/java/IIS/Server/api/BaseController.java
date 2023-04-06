package IIS.Server.api;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.IsolationService;

public abstract class BaseController 
{
    protected static <T> ResponseEntity<T> isolated(Supplier<ResponseEntity<T>> requestAction)
    {
        final AsyncWorkload<ResponseEntity<T>> workload = IsolationService.getInstance().schedule(requestAction);
        final GenericAsyncResult<ResponseEntity<T>> result = workload.getResultAsync().join();
        if (result.isSuccess())
        {
            return result.getValue();
        }
        return Response.error(result.getError());
    }
}
