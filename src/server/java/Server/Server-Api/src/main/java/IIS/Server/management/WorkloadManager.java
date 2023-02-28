package IIS.Server.management;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkloadManager {
    private final Object collectionLock = new Object();
    private final List<ConcurrentWorkload> currentWorkloads = new ArrayList<ConcurrentWorkload>();
    private final Object isClearingLock = new Object();
    private final Object workerThreadCountLock = new Object();
    private volatile int maxWorkerCount;
    private volatile boolean isClearing = false;
    private volatile int workerCount = 0;
    private volatile int workloadCount = 0;
    protected IWorkloadCollection<ConcurrentWorkload> workloadCollection;

    public enum WorkloadOperatingMode {
        LastInFirstOut,
        FirstInFirstOut
    }

    public WorkloadManager(WorkloadOperatingMode operatingMode, int maxWorkerCount) {
        this.operatingMode = operatingMode;
        this.maxWorkerCount = maxWorkerCount;
        this.workloadCollection = operatingMode == WorkloadOperatingMode.LastInFirstOut
                ? new WorkloadStack()
                : new WorkloadQueue();
    }

    public int getMaxWorkerCount() {
        return maxWorkerCount;
    }

    public void setMaxWorkerCount(int maxWorkerCount) {
        synchronized (workerThreadCountLock) {
            if (maxWorkerCount < 0) {
                throw new IllegalArgumentException("Worker count cannot be less than 0.");
            }
            this.maxWorkerCount = maxWorkerCount;
        }
    }

    public WorkloadOperatingMode getOperatingMode() {
        return operatingMode;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public int getWorkloadCount() {
        return workloadCount;
    }

    public void clear() {
        synchronized (isClearingLock) {
            if (isClearing) {
                return;
            }
            isClearing = true;
        }
        try {
            synchronized (collectionLock) {
                // cancel running tasks (threads will block, waiting for collectionLock)
                for (ConcurrentWorkload workload : currentWorkloads) {
                    workload.cancel();
                }

                // clear scheduled tasks
                while (workloadCollection.poll() != null) {
                    workloadCount--;
                }
            }
        } finally {
            isClearing = false;
        }
    }

    public void scheduleWorkload(ConcurrentWorkload workload) {
        synchronized (collectionLock) {
            workload.exchangeStatusInterlocked(SchedulingStatus.SCHEDULED);
            workloadCollection.add(workload);
            workloadCount = workloadCollection.size();
            // if possible allocate a new worker thread.
            adjustCheckWorkerThreadCountInterlocked();
        }
    }

    private boolean adjustCheckWorkerThreadCountInterlocked() {
        // lock will be reentrant for the same thread.
        // if we already have the collectionLock, doesn't matter.
        synchronized (collectionLock) {
            synchronized (workerThreadCountLock) {
                if (workerCount > maxWorkerCount) {
                    // we are using more threads than we are allowed to.
                    // notify calling thread.
                    // if it is a worker thread it will terminate if we return false.
                    // THIS NEED TO HAPPEN HERE
                    signalThreadExiting();
                    return false;
                }
                if (workerCount >= maxWorkerCount) {
                    // we cannot allocate more threads but that's fine.
                    return true;
                }
                // we are not at our full threading capacity yet.
                // if needed we can allocate more threads.
                if (workloadCollection.size() > 0) {
                    // we have more workloads than threads.
                    // allocate a new thread.
                    workerCount++;
                    new Thread(new WorkerThread()).start();
                    return true;
                }
                // we have no more workloads.
                // no need to allocate more threads.
                return true;
            }
        }
    }

    private void signalThreadExiting() {
        synchronized (workerThreadCountLock) {
            workerCount--;
        }
    }

    private final WorkloadOperatingMode operatingMode;

    private class WorkerThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    ConcurrentWorkload workload = null;
                    synchronized (collectionLock) {
                        workload = workloadCollection.poll();
                        if (workload == null) {
                            // no more workloads.
                            // notify calling thread.
                            // if it is a worker thread it will terminate if we return false.
                            // THIS NEED TO HAPPEN HERE
                            signalThreadExiting();
                            return;
                        }
                        workloadCount = workloadCollection.size();
                    }
                    workload.exchangeStatusInterlocked(SchedulingStatus.EXECUTING);
                    currentWorkloads.add(workload);
                    try {
                        workload.execute();
                    } finally {
                        currentWorkloads.remove(workload);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private interface IWorkloadCollection<T> {
        void add(T item);

        T poll();

        int size();
    }

    private class WorkloadQueue implements IWorkloadCollection<ConcurrentWorkload> {
        private final ConcurrentLinkedQueue<ConcurrentWorkload> queue = new ConcurrentLinkedQueue<ConcurrentWorkload>();

        @Override
        public void add(ConcurrentWorkload item) {
            queue.add(item);
        }

        @Override
        public ConcurrentWorkload poll() {
            return queue.poll();
        }

        @Override
        public int size() {
            return queue.size();
        }
    }

    private class WorkloadStack implements IWorkloadCollection<ConcurrentWorkload> {
        private final List<ConcurrentWorkload> stack = new ArrayList<ConcurrentWorkload>();

        @Override
        public void add(ConcurrentWorkload item) {
            stack.add(item);
        }

        @Override
        public ConcurrentWorkload poll() {
            if (stack.size() == 0) {
                return null;
            }
            return stack.remove(stack.size() - 1);
        }

        @Override
        public int size() {
            return stack.size();
        }
    }
}