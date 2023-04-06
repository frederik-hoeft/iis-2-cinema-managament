package IIS.Server.management;

import java.util.function.Supplier;

public class IsolationService 
{
    private static final IsolationService instance = new IsolationService();

    private final WorkloadManager _scheduler = new WorkloadManager(WorkloadOperatingMode.FirstInFirstOut, 1);

    private IsolationService() { }

    public static IsolationService getInstance() 
    {
        return instance;
    }

    public <T> AsyncWorkload<T> schedule(Supplier<T> c)
    {
        AsyncWorkload<T> workload = new AsyncWorkload<>(c);
        _scheduler.scheduleWorkload(workload);
        return workload;
    }
}
