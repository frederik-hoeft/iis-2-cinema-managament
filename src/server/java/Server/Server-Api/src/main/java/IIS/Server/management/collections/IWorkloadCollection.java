package IIS.Server.management.collections;

public interface IWorkloadCollection<T>
{
    void add(T item);

    T poll();

    int size();
}
