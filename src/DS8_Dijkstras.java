public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
    {
        int distance = 0;
        DS8_Queue<String> Queue = new DS8_Queue<>();
        Queue.offer(start + "0");
        boolean[] visited = new boolean[edges.length];
        while(!Queue.isEmpty())
        {
            String cur = Queue.poll();
            if(cur.charAt(cur.length() - 1) == end)
            {
                return Integer.parseInt(cur.substring(cur.length() - 1));
            }
            for(int i = 0; i < edges.length; i++)
            {
                if (edges[i].charAt(0) == cur.charAt(cur.length() - 2) && !visited[i])
                {
                    cur = cur.substring(0, cur.length() - 1);
                    distance += Integer.parseInt(cur.substring(cur.length() - 1));
                    Queue.offer(cur + edges[i].charAt(1) + String.valueOf(distance));
                    visited[i] = true;
                }
            }
        }
        return -1;
    }
}
