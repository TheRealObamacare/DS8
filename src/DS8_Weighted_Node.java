
public class DS8_Weighted_Node implements Comparable
{
    private char location;
    private int distance;

    public DS8_Weighted_Node(char location, int distance)
    {
        this.location = location;
        this.distance = distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int getDistance()
    {
        return distance;
    }

    public char getLocation()
    {
        return location;
    }

    public String toString()
    {
        return location + "-"+distance;
    }

    public int compareTo(Object o)
    {
        return distance - ((DS8_Weighted_Node)o).distance;
    }
}
