package IIS.Server.management.collections;

import java.util.concurrent.ConcurrentLinkedQueue;

import IIS.Server.management.ConcurrentWorkload;

public class WorkloadQueue implements IWorkloadCollection<ConcurrentWorkload> 
{
    private final ConcurrentLinkedQueue<ConcurrentWorkload> queue = new ConcurrentLinkedQueue<ConcurrentWorkload>();

    @Override
    public void add(ConcurrentWorkload item) 
    {
        queue.add(item);
    }

    @Override
    public ConcurrentWorkload poll() 
    {
        return queue.poll();
    }

    @Override
    public int size() 
    {
        return queue.size();
    }
}