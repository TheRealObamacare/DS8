import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class DS8_Dijkstras
{
    public static int dijkstras_Weighted(String[] edges, String vertices, char start, char end)
    {
        ArrayList<Point> graph = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        for (String edge : edges)
        {
            char from = edge.charAt(0);
            char to = edge.charAt(1);
            int weight = Integer.parseInt(edge.substring(2));
            graph.add(new Point(from, to));
            weights.add(weight);
        }

        ArrayList<DS8_Weighted_Node> nodes = new ArrayList<>();
        for (char vertex : vertices.toCharArray())
        {
            if (vertex == start)
            {
                nodes.add(new DS8_Weighted_Node(vertex, 0));
            }
            else
            {
                nodes.add(new DS8_Weighted_Node(vertex, Integer.MAX_VALUE));
            }
        }

        ArrayList<DS8_Weighted_Node> visited = new ArrayList<>();

        while (!nodes.isEmpty())
        {
            Collections.sort(nodes);
            DS8_Weighted_Node current = nodes.remove(0);
            if (current.getDistance() == Integer.MAX_VALUE) {
                break;
            }

            char currentLocation = current.getLocation();
            int currentDistance = current.getDistance();
            visited.add(current);

            for (int i = 0; i < graph.size(); i++)
            {
                Point edge = graph.get(i);
                if (edge.x == currentLocation)
                {
                    char neighbor = (char) edge.y;
                    int newDist = currentDistance + weights.get(i);
                    for (DS8_Weighted_Node node : nodes)
                    {
                        if (node.getLocation() == neighbor && newDist < node.getDistance())
                            node.setDistance(newDist);
                    }
                }
            }
        }

        for (DS8_Weighted_Node node : visited)
        {
            if (node.getLocation() == end)
            {
                return node.getDistance() == Integer.MAX_VALUE ? -1 : node.getDistance();
            }
        }

        return -1;
    }
    public static int dijkstras_Topographical(char[][] grid, ArrayList<DS8_TerrainCost> travelCosts)
    {
        int rows = grid.length;
        int cols = grid[0].length;
        Point start = null;
        Point end = null;
        int[][] distances = new int[rows][cols];
        ArrayList<DS8_Terrian_Node> nodes = new ArrayList<>();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (grid[i][j] == 'S')
                {
                    start = new Point(i, j);
                    distances[i][j] = 0;
                } else
                {
                    distances[i][j] = Integer.MAX_VALUE;
                }
                if (grid[i][j] == 'E') {
                    end = new Point(i, j);
                }
                nodes.add(new DS8_Terrian_Node(new Point(i, j), distances[i][j]));
            }
        }
        if (start == null || end == null)
            return -1;

        while (!nodes.isEmpty())
        {
            Collections.sort(nodes);
            DS8_Terrian_Node current = nodes.remove(0);
            Point currentLocation = current.getLocation();
            int currentDistance = current.getDistance();
            if (currentDistance == Integer.MAX_VALUE)
                break;
            int x = currentLocation.x;
            int y = currentLocation.y;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions)
            {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols)
                {
                    char terrain = grid[newX][newY];
                    int terrainCost = getTerrainCost(terrain, travelCosts);
                    if (terrainCost != -1)
                    {
                        int newDist = currentDistance + terrainCost;
                        if (newDist < distances[newX][newY])
                        {
                            distances[newX][newY] = newDist;
                            for (DS8_Terrian_Node node : nodes)
                            {
                                if (node.getLocation().equals(new Point(newX, newY)))
                                {
                                    node.setDistance(newDist);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        int result = distances[end.x][end.y];
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int getTerrainCost(char terrain, ArrayList<DS8_TerrainCost> travelCosts) {
        for (DS8_TerrainCost cost : travelCosts)
        {
            if (cost.getType() == terrain)
            {
                return cost.getCost();
            }
        }
        return -1;
    }
}