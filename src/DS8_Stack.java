
import java.util.ArrayList;

public class DS8_Stack <E>
{
    private ArrayList <E> data;

    public DS8_Stack()
    {   data = new ArrayList <E>(); }

    public E peek()
    {
        if(data.size()==0)
            return null;
        else
            return data.get(data.size()-1);
    }

    public void push(E o)
    {   data.add(o);    }

    public E pop()
    {
        if(data.size()==0)
            return null;
        else
            return data.remove(data.size()-1);
    }

    public boolean isEmpty()
    {
        if(data.size()==0)
            return true;

        else
            return false;
    }

    public int size()
    {   return data.size(); }

    public E get(int x)
    {   return data.get(x); }

    public void clear()
    {   data.clear();   }

    public String toString()
    {   return data.toString();     }
}
