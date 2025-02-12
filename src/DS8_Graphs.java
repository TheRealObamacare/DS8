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

    public static String[] stronglyConnectedRegions(String[] edges, String vertices) {
        int n = vertices.length();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<Integer>());
        for (String edge : edges)
        {
            if (edge == null || edge.length() < 2)
                continue;
            char fromChar = edge.charAt(0);
            char toChar = edge.charAt(1);
            int fromIndex = vertices.indexOf(fromChar);
            int toIndex = vertices.indexOf(toChar);
            if (fromIndex != -1 && toIndex != -1)
                graph.get(fromIndex).add(toIndex);
        }


        int[] ids = new int[n];
        int[] low = new int[n];
        boolean[] onStack = new boolean[n];
        for (int i = 0; i < n; i++)
            ids[i] = -1;
        DS8_Stack<Integer> stack = new DS8_Stack<>();
        ArrayList<ArrayList<Integer>> sccList = new ArrayList<>();
        int[] idCounter = new int[]{0};
        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) {
                dfs(i, graph, ids, low, onStack, stack, idCounter, sccList);
            }
        }
        ArrayList<String> regions = new ArrayList<>();
        for (ArrayList<Integer> component : sccList)
        {
            if (component.size() > 1)
            {
                Collections.reverse(component);
                StringBuilder sb = new StringBuilder();
                for (int index : component)
                {
                    sb.append(vertices.charAt(index));
                }
                regions.add(sb.toString());
            }
        }
        if (regions.size() == 0)
            return null;
        String[] result = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            result[i] = regions.get(i);
        }
        return result;
    }
    private static void dfs(int at, ArrayList<ArrayList<Integer>> graph, int[] ids, int[] low,
                            boolean[] onStack, DS8_Stack<Integer> stack, int[] idCounter,
                            ArrayList<ArrayList<Integer>> sccList) {
        ids[at] = idCounter[0];
        low[at] = idCounter[0];
        idCounter[0]++;
        stack.push(at);
        onStack[at] = true;
        for (int to : graph.get(at)) 
        {
            if (ids[to] == -1)
            {
                dfs(to, graph, ids, low, onStack, stack, idCounter, sccList);
                low[at] = Math.min(low[at], low[to]);
            }
            else if (onStack[to])
                low[at] = Math.min(low[at], ids[to]);
        }
        if (ids[at] == low[at])
        {
            ArrayList<Integer> component = new ArrayList<>();
            int node;
            do
            {
                node = stack.pop();
                onStack[node] = false;
                component.add(node);
            } while (node != at);
            sccList.add(component);
        }
    }
}