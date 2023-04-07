package IIS.Server.management;

public final class GenericAsyncResult<TResult> implements IGenericAsyncResult<TResult> 
{
    private final boolean isSuccess;
    private final TResult value;
    private final Exception error;

    private GenericAsyncResult(boolean isSuccess, TResult value, Exception error) 
    {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
    }

    /**
     * Indicates whether the execution of the corresponding asynchronous operation was successful.
     */
    @Override
    public boolean isSuccess() 
    {
        return isSuccess;
    }

    /**
     * The value returned by the corresponding asynchronous operation or {@code null} if the execution did not run to completion.
     */
    @Override
    public TResult getValue() 
    {
        return value;
    }

    @Override
    public Exception getError() 
    {
        return error;
    }

    public static <TResult> GenericAsyncResult<TResult> of(TResult result) 
    {
        return new GenericAsyncResult<TResult>(true, result, null);
    }

    public static <TResult> GenericAsyncResult<TResult> error(Exception error) 
    {
        return new GenericAsyncResult<TResult>(false, null, error);
    }
}