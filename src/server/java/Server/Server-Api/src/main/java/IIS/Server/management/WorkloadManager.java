package IIS.Server.management;

import java.util.ArrayList;
import java.util.List;

import IIS.Server.management.collections.IWorkloadCollection;
import IIS.Server.management.collections.WorkloadQueue;
import IIS.Server.management.collections.WorkloadStack;

public class WorkloadManager 
{
    private final Object collectionLock = new Object();
    private final List<ConcurrentWorkload> currentWorkloads = new ArrayList<ConcurrentWorkload>();
    private final Object isClearingLock = new Object();
    private final Object workerThreadCountLock = new Object();
    private final WorkloadOperatingMode operatingMode;
    private volatile int maxWorkerCount;
    private volatile boolean isClearing = false;
    private volatile int workerCount = 0;
    private volatile int workloadCount = 0;
    protected final IWorkloadCollection<ConcurrentWorkload> workloadCollection;

    public WorkloadManager(WorkloadOperatingMode operatingMode, int maxWorkerCount) 
    {
        this.operatingMode = operatingMode;
        this.maxWorkerCount = maxWorkerCount;
        this.workloadCollection = operatingMode == WorkloadOperatingMode.LastInFirstOut
            ? new WorkloadStack()
            : new WorkloadQueue();
    }

    public int getMaxWorkerCount() 
    {
        return maxWorkerCount;
    }

    public void setMaxWorkerCount(int maxWorkerCount) 
    {
        synchronized (workerThreadCountLock) 
        {
            if (maxWorkerCount < 0) 
            {
                throw new IllegalArgumentException("Worker count cannot be less than 0.");
            }
            this.maxWorkerCount = maxWorkerCount;
        }
    }

    public WorkloadOperatingMode getOperatingMode() 
    {
        return operatingMode;
    }

    public int getWorkerCount() 
    {
        return workerCount;
    }

    public int getWorkloadCount() 
    {
        return workloadCount;
    }

    public void clear() 
    {
        synchronized (isClearingLock) 
        {
            if (isClearing) 
            {
                return;
            }
            isClearing = true;
        }
        try 
        {
            synchronized (collectionLock) 
            {
                // cancel running tasks (threads will block, waiting for collectionLock)
                for (ConcurrentWorkload workload : currentWorkloads) 
                {
                    workload.cancel();
                }

                // clear scheduled tasks
                ConcurrentWorkload workload;
                while ((workload = workloadCollection.poll()) != null) 
                {
                    workload.cancel();
                }
                workloadCount = 0;
            }
        } 
        finally 
        {
            isClearing = false;
        }
    }

    public void scheduleWorkload(ConcurrentWorkload workload) 
    {
        synchronized (collectionLock) 
        {
            workload.exchangeStatusInterlocked(SchedulingStatus.SCHEDULED);
            workloadCollection.add(workload);
            workloadCount = workloadCollection.size();
            // if possible allocate a new worker thread.
            adjustCheckWorkerThreadCountInterlocked(false);
        }
    }

    private boolean adjustCheckWorkerThreadCountInterlocked(boolean isWorker) 
    {
        // lock will be reentrant for the same thread.
        // if we already have the collectionLock, doesn't matter.
        synchronized (collectionLock) 
        {
            synchronized (workerThreadCountLock) 
            {
                if (isWorker && workerCount > maxWorkerCount) 
                {
                    // we are using more threads than we are allowed to.
                    // notify calling thread.
                    // if it is a worker thread it will terminate if we return false.
                    // THIS NEED TO HAPPEN HERE
                    signalThreadExiting();
                    return false;
                }
                if (workerCount >= maxWorkerCount) 
                {
                    // we cannot allocate more threads but that's fine.
                    return true;
                }
                // we are not at our full threading capacity yet.
                // if needed we can allocate more threads.
                if (workloadCollection.size() > 0) 
                {
                    // we have more workloads than threads.
                    // allocate a new thread.
                    workerCount++;
                    new Thread(new WorkerThread(this)).start();
                }
                // we have no more workloads.
                // no need to allocate more threads.
                return true;
            }
        }
    }

    private void signalThreadExiting() 
    {
        synchronized (workerThreadCountLock) 
        {
            if (workerCount > 0) 
            {
                workerCount--;
            }
        }
    }

    private class WorkerThread implements Runnable 
    {
        private final WorkloadManager manager;

        public WorkerThread(WorkloadManager manager) 
        {
            this.manager = manager;
        }

        private ConcurrentWorkload tryGetWorkload(ConcurrentWorkload workload)
        {
            // need full access to workload collection
            synchronized (manager.collectionLock)
            {
                // discard completed workload
                if (workload != null)
                {
                    manager.currentWorkloads.remove(workload);
                }
                // check if maximum allowed number of worker threads has changed
                if (!manager.adjustCheckWorkerThreadCountInterlocked(true))
                {
                    // we are terminating! already signaled that we're exiting!
                    return null;
                }
                // get next workload
                if ((workload = manager.workloadCollection.poll()) != null)
                {
                    // update workloads in manager
                    manager.workloadCount = manager.workloadCollection.size();
                    manager.currentWorkloads.add(workload);
                    // return non-zero
                    return workload;
                }
                // there was no next workload (was null)
                // must update thread count!
                manager.signalThreadExiting();
                return null;
            }
        }

        @Override
        public void run() 
        {
            try 
            {
                ConcurrentWorkload workload = null;
                while ((workload = tryGetWorkload(workload)) != null)
                {
                    if (workload.compareExchangeStatusInterlocked(SchedulingStatus.SCHEDULED, SchedulingStatus.EXECUTING))
                    {
                        workload.execute();
                    }
                    else
                    {
                        workload.cancel();
                    }
                }
            } 
            catch (Exception e) 
            {
                // must notify parent
                manager.signalThreadExiting();
            }
        }
    }
}