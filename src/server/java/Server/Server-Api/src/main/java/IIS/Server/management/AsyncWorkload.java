package IIS.Server.management;

import java.util.function.Supplier;
import java.util.concurrent.CompletableFuture;

public class AsyncWorkload<TResult> extends ConcurrentWorkload {
    private final Supplier<TResult> func;
    private final CompletableFuture<GenericAsyncResult<TResult>> future = new CompletableFuture<>();

    public AsyncWorkload(Supplier<TResult> func) {
        this.func = func;
    }

    @Override
    public void execute() {
        TResult result = func.get();
        if (compareExchangeStatusInterlocked(SchedulingStatus.EXECUTING, SchedulingStatus.COMPLETED)) {
            future.complete(new GenericAsyncResult<TResult>(true, result));
        } else {
            cancel();
        }
    }

    @Override
    public void cancel() {
        synchronized (statusLock) {
            future.complete(new GenericAsyncResult<TResult>(false, null));
            status = SchedulingStatus.CANCELED;
        }
    }

    public CompletableFuture<GenericAsyncResult<TResult>> getResultAsync() {
        return future;
    }
}