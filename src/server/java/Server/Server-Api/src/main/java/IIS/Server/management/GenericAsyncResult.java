package IIS.Server.management;

public final class GenericAsyncResult<TResult> implements IGenericAsyncResult<TResult> {
    private final boolean isSuccess;
    private final TResult value;

    public GenericAsyncResult(boolean isSuccess, TResult value) {
        this.isSuccess = isSuccess;
        this.value = value;
    }

    /**
     * Indicates whether the execution of the corresponding asynchronous operation was successful.
     */
    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * The value returned by the corresponding asynchronous operation or {@code null} if the execution did not run to completion.
     */
    @Override
    public TResult getValue() {
        return value;
    }

    /**
     * Converts an {@code AsyncResult} instance to its {@link #getValue()} value.
     */
    public static <TResult> TResult toValue(GenericAsyncResult<TResult> result) {
        return result.getValue();
    }
}