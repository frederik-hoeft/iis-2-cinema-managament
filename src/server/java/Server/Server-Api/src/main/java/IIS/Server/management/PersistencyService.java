package IIS.Server.management;

import java.util.function.Supplier;

import IIS.Server.management.WorkloadManager.WorkloadOperatingMode;

public class PersistencyService {

    private static final PersistencyService instance = new PersistencyService();

    private final WorkloadManager _scheduler = new WorkloadManager(WorkloadOperatingMode.FirstInFirstOut, 1);

    private PersistencyService() {
    }

    public static PersistencyService getInstance() {
        return instance;
    }

    public <T> AsyncWorkload<T> schedule(Supplier<T> c)
    {
        AsyncWorkload<T> workload = new AsyncWorkload<>(c);
        _scheduler.scheduleWorkload(workload);
        return workload;
    }
}
