package IIS.Server.management;

import java.util.function.Supplier;
import java.util.concurrent.CompletableFuture;

public class AsyncWorkload<TResult> extends ConcurrentWorkload 
{
    private final Supplier<TResult> func;
    private final CompletableFuture<GenericAsyncResult<TResult>> future = new CompletableFuture<>();

    public AsyncWorkload(Supplier<TResult> func) 
    {
        this.func = func;
    }

    @Override
    public void execute() 
    {
        try 
        {
            TResult result = func.get();
            if (compareExchangeStatusInterlocked(SchedulingStatus.EXECUTING, SchedulingStatus.COMPLETED)) 
            {
                future.complete(GenericAsyncResult.of(result));
            } 
            else 
            {
                cancel();
            }
        } 
        catch (Exception e) 
        {
            synchronized (statusLock) 
            {
                future.complete(GenericAsyncResult.error(e));
                status = SchedulingStatus.COMPLETED;
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancel() 
    {
        synchronized (statusLock) 
        {
            future.complete(GenericAsyncResult.of(null));
            status = SchedulingStatus.CANCELED;
        }
    }

    public CompletableFuture<GenericAsyncResult<TResult>> getResultAsync() 
    {
        return future;
    }
}