import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class DS8_Graphs {
    private static int time = 0;

    public static ArrayList<String> bridges(String[] edges, String vertices) {
        int V = vertices.length();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (String edge : edges) {
            int u = vertices.indexOf(edge.charAt(0));
            int v = vertices.indexOf(edge.charAt(1));
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        ArrayList<String> bridges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            parent[i] = -1;
            visited[i] = false;
        }
        for (int i = 0; i < V; i++)
            if (!visited[i])
                bridgeUtil(i, visited, disc, low, parent, adj, vertices, bridges, edges);
        return bridges.isEmpty() ? null : bridges;
    }

    private static void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, ArrayList<ArrayList<Integer>> adj, String vertices, ArrayList<String> bridges, String[] edges) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u))
        {
            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent, adj, vertices, bridges, edges);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u])
                {
                    String bridge = "" + vertices.charAt(u) + vertices.charAt(v);
                    for (String edge : edges)
                    {
                        if (edge.equals(bridge) || edge.equals("" + vertices.charAt(v) + vertices.charAt(u)))
                        {
                            bridges.add(edge);
                            break;
                        }
                    }
                }
            }
            else if (v != parent[u])
            {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
    
}