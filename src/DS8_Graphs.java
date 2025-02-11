import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class DS8_Graphs
{
    // see what a bridge is in a graph and return which edge is a bridge, if it can connect to fewer vertices than with that path, it is a bridge
    public static ArrayList<String> bridges(String[] edges, String vertices)
    {
        int max = breadthFirstSearch_Unweighted(edges, vertices, vertices.charAt(0));
        ArrayList<String> bridges = new ArrayList<>();
        for(int i = 0; i < edges.length; i++)
        {
            String[] temp = new String[edges.length-1];
            for (int j = 0; j < edges.length; j++)
            {
                if(j < i)
                {
                    temp[j] = edges[j];
                }
                else if(j > i)
                {
                    temp[j-1] = edges[j];
                }
            }
            if(breadthFirstSearch_Unweighted(temp, vertices, vertices.charAt(0)) < max)
            {
                bridges.add(edges[i]);
            }
        }
        return bridges;
    }
    public static int breadthFirstSearch_Unweighted(String[] edges, String vertices, char start)
    {
        DS8_Queue<String> Queue = new DS8_Queue<>();
        Queue.offer(start + "");
        int max = 0;
        boolean[] visited = new boolean[edges.length];
        while(!Queue.isEmpty())
        {
            String cur = Queue.poll();
            for(int i = 0; i < edges.length; i++)
            {
                if (edges[i].charAt(0) == cur.charAt(cur.length() - 1) && !visited[i])
                {
                    max++;
                    Queue.offer(cur + 1);
                    visited[i] = true;
                }
                else if (edges[i].charAt(1) == cur.charAt(cur.length() - 1) && !visited[i])
                {
                    max++;
                    Queue.offer(cur + 1);
                    visited[i] = true;
                }
            }
        }
        return max;
    }
}
