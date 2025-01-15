
import java.util.ArrayList;

public class DS8_Queue<E>
{
    private ArrayList<E> queue;

    public DS8_Queue()
    {
        queue=new ArrayList();
    }
    public boolean add(E o)
    {
        if(offer(o))
            return true;
        else
            throw new IllegalStateException("Queue Full");
    }
    public boolean offer(E o)
    {
        return queue.add(o);
    }
    public E peek()
    {
        return isEmpty() ? null:get(0);
    }
    public E element()
    {
        return get(0);
    }
    public E poll()
    {
        if(isEmpty())
            return null;
        else
        {
            E head=peek();
            queue.remove(0);
            return head;
        }
    }
    public E remove()
    {
        if(isEmpty())
            return null;
        else
        {
            E head=peek();
            queue.remove(0);
            return head;
        }
    }
    public boolean isEmpty()
    {
        return (size()==0);
    }
    public int size()
    {
        int size=0;
        for(E item:queue)
            size++;
        return size;
    }
    public E get(int x)
    {
        if(isEmpty())
            return null;
        else if(x>size())
            throw new ArrayIndexOutOfBoundsException("Invalid value");
        else
            return queue.get(x);
    }
    public void clear(){
    queue.clear();
}

    public String toString()
    {
        return queue.toString();
    }

}
