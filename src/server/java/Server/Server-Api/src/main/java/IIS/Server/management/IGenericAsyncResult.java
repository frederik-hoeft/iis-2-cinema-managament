package IIS.Server.management;

public interface IGenericAsyncResult<TResult> extends IAsyncResult {
    /**
     * The value returned by the corresponding asynchronous operation or {@code null} if the execution did not run to completion.
     */
    TResult getValue();
}