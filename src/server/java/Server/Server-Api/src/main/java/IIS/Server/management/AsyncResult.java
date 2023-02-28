package IIS.Server.management;

public final class AsyncResult implements IAsyncResult {
    private final boolean isSuccess;

    private AsyncResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * Constructs a new {@code AsyncResult} using the provided parameters.
     *
     * @param isSuccess Indicates whether the execution of the corresponding asynchronous operation was successful.
     */
    public static AsyncResult of(boolean isSuccess) {
        return new AsyncResult(isSuccess);
    }

    /**
     * Indicates whether the execution of the corresponding asynchronous operation was successful.
     */
    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Converts an {@code AsyncResult} instance to its {@link #isSuccess()} boolean value.
     */
    public static boolean toBoolean(AsyncResult result) {
        return result.isSuccess();
    }
}