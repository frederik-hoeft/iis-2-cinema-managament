package IIS.Server.management.collections;

import java.util.Stack;

import IIS.Server.management.ConcurrentWorkload;

public class WorkloadStack implements IWorkloadCollection<ConcurrentWorkload> 
{
    private final Stack<ConcurrentWorkload> stack = new Stack<ConcurrentWorkload>();
    private final Object lock = new Object();

    @Override
    public void add(ConcurrentWorkload item) 
    {
        synchronized (lock)
        {
            stack.push(item);
        }
    }

    @Override
    public ConcurrentWorkload poll() 
    {
        synchronized (lock)
        {
            if (stack.empty()) 
            {
                return null;
            }
            return stack.pop();
        }
    }

    @Override
    public int size() 
    {
        synchronized (lock)
        {
            return stack.size();
        }
    }
}