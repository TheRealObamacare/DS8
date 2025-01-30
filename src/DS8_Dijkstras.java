import java.util.ArrayList;
import java.util.Collections;

public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
    {
        int distance = 0;
        ArrayList<DS8_Weighted_Node> nodes = new ArrayList<>();
        nodes.add(new DS8_Weighted_Node(start, distance));
        boolean[] visited = new boolean[edges.length];
        for (String yay : edges)
        {
            if (yay.charAt(0) == start)
            {
                continue;
            }
            else
                nodes.add(new DS8_Weighted_Node(yay.charAt(0), Integer.MAX_VALUE));
        }
        ArrayList<DS8_Weighted_Node> sorted = new ArrayList<>();
        for (DS8_Weighted_Node yay : nodes)
        {
            sorted.add(yay);
        }
        Collections.sort(sorted);
        while(!sorted.isEmpty())
        {
            DS8_Weighted_Node cur = sorted.removeFirst();
            if (cur.getDistance() == Integer.MAX_VALUE)
            {
                break;
            }
            for(int i = 0; i < edges.length; i++)
            {
                if (edges[i].charAt(0) == cur.getLocation())
                {
                    for (String x: edges)
                    {
                        if (x.charAt(0) == edges[i].charAt(1))
                        {
                            distance = distFromStart(edges, start,x.charAt(0), 0);
                            System.out.println("Distance from " + start + " to " + x.charAt(0) + " is " + distFromStart(edges, start,x.charAt(0), 0));
                            cur.setDistance(distance);
                            System.out.println(cur.toString());
                            visited[i] = true;
                        }
                    }
                }
            }
            Collections.sort(sorted);
        }
        for (DS8_Weighted_Node yay : nodes)
        {
            if (yay.getLocation() == end)
            {
                if (yay.getDistance() != Integer.MAX_VALUE)
                {
                    return yay.getDistance();
                }
            }
        }
        return -1;
    }

    public static int distFromStart(String[] edges, char start,char location, int distance)
    {
        if (location == start)
        {
            return distance;
        }
        for (String yay : edges)
        {
            if (yay.charAt(0) == location)
            {
                return 1 + distFromStart(edges, start, yay.charAt(1), distance + Integer.parseInt(yay.substring(2)));
            }
        }
        return 0;
    }
}