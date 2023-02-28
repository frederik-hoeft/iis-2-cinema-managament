package IIS.Server.management;

public enum SchedulingStatus {
    /**
     * The job is scheduled to be executed by a {@link WorkloadManager}.
     */
    SCHEDULED,

    /**
     * The job is currently running.
     */
    EXECUTING,

    /**
     * The job has been completed.
     */
    COMPLETED,

    /**
     * The job was canceled and will not be executed.
     */
    CANCELED,

    /**
     * The job is currently not being tracked by any {@link WorkloadManager}.
     */
    NOT_TRACKED
}