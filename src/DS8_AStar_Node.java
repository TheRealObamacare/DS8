
public class DS8_AStar_Node<L> implements Comparable<DS8_AStar_Node<L>>{
    private L location;
    private DS8_AStar_Node<L> parent;
    private int f,h,g;

    public DS8_AStar_Node(L location,DS8_AStar_Node<L> parent, int g, int h)
    {
        this.location = location;
        this.g = g;
        this.h = h;
        this.parent = parent;
        fixF();
    }

    public void setG(int g)
    {
        this.g = g;
        fixF();
    }

    public void setH(int h)
    {
        this.h = h;
        fixF();
    }

    public int getG()
    {
        return g;
    }

    public int getH()
    {
        return h;
    }

    public int getF()
    {
        return f;
    }

    public L getLocation()
    {
        return location;
    }

    public DS8_AStar_Node<L> getParent()
    {
        return parent;
    }

    public void fixF()
    {
        f=g+h;
    }

    public String toString()
    {
        return location+"("+parent+")" + " "+f+"="+g+"+"+h;
    }

    public int compareTo(DS8_AStar_Node<L> o)
    {
        if(f!=o.f)
            return f - o.f;
        else {
            return h - o.h;
        }
    }

    public boolean equals(DS8_AStar_Node<L> o)
    {
        return location.equals(o.location);
    }
}
