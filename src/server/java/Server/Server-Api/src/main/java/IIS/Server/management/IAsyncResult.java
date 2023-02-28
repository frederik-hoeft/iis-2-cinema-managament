package IIS.Server.management;

public interface IAsyncResult {
    /**
     * Indicates whether the execution of the corresponding asynchronous operation was successful.
     */
    boolean isSuccess();
}