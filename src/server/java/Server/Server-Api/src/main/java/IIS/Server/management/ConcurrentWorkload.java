package IIS.Server.management;

public abstract class ConcurrentWorkload 
{
    protected final Object statusLock = new Object();
    protected volatile SchedulingStatus status = SchedulingStatus.NOT_TRACKED;

    public SchedulingStatus getStatus() 
    {
        return status;
    }

    public abstract void execute();

    public void cancel() 
    {
        synchronized (statusLock)
        {
            if (status == SchedulingStatus.NOT_TRACKED || status == SchedulingStatus.SCHEDULED)
            {
                status = SchedulingStatus.CANCELED;
            }
        }
    }

    public boolean compareExchangeStatusInterlocked(SchedulingStatus comparand, SchedulingStatus newStatus) 
    {
        synchronized (statusLock) 
        {
            boolean hasValue = status == comparand;
            if (hasValue) 
            {
                status = newStatus;
            }
            return hasValue;
        }
    }

    public void exchangeStatusInterlocked(SchedulingStatus newStatus) 
    {
        synchronized (statusLock)
        {
            status = newStatus;
        }
    }
}