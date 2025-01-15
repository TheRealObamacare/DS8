
import java.awt.*;
import java.util.ArrayList;

public class DS8_Path_Solution<L> {

    private ArrayList<L> path;
    private int distance;

    public DS8_Path_Solution(ArrayList<L> path, int distance)
    {
        this.path = path;
        this.distance = distance;
    }

    public ArrayList<L> getPath()
    {
        return path;
    }

    public int getDistance()
    {
        return distance;
    }

    public String toString()
    {
        return path.toString() + " "+ distance;
    }
}
