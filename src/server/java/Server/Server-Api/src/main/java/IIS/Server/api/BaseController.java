package IIS.Server.api;

import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;

public abstract class BaseController 
{
    protected static <T> ResponseEntity<T> scheduled(Supplier<ResponseEntity<T>> requestAction)
    {
        final AsyncWorkload<ResponseEntity<T>> workload = PersistencyService.getInstance().schedule(requestAction);
        final GenericAsyncResult<ResponseEntity<T>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
