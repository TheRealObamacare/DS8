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
            nodes.add(new DS8_Weighted_Node(yay.charAt(0), Integer.MAX_VALUE));
        }
        ArrayList<DS8_Weighted_Node> sorted = nodes;
        Collections.sort(sorted);
        while(!nodes.isEmpty())
        {
            DS8_Weighted_Node cur = nodes.removeFirst();
            if (cur.getDistance() == Integer.MAX_VALUE)
            {
                break;
            }
            if(cur.getLocation() == end)
            {
                return cur.getDistance();
            }
            for(int i = 0; i < edges.length; i++)
            {
                if (edges[i].charAt(0) == cur.getLocation() && !visited[i])
                {
                    distance += Integer.parseInt(edges[i].substring(2));
                    nodes.add(new DS8_Weighted_Node(edges[i].charAt(1), distance));
                    visited[i] = true;
                }
            }
        }
        return -1;
    }

}