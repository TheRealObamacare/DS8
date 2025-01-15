

import java.awt.*;

public class DS8_Terrian_Node implements Comparable
{
    private Point location;
    private int distance;

    public DS8_Terrian_Node(Point location, int distance)
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

    public Point getLocation()
    {
        return location;
    }

    public String toString()
    {
        return location + "-"+distance;
    }

    public int compareTo(Object o)
    {
        return distance - ((DS8_Terrian_Node)o).distance;
    }
}
