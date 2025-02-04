import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class DS8_AStar
{
    public static DS8_Path_Solution aStar_Simple(char[][] maze)
    {
        ArrayList<DS8_AStar_Node<Point>> open = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closed = new ArrayList<>();
        Point start = findChar(maze, 'S');
        Point end = findChar(maze, 'E');
        DS8_AStar_Node<Point> startNode = new DS8_AStar_Node<>(start, null, 0, heuristic(start, end));
        open.add(startNode);
        while (!open.isEmpty()) {
            open.sort(DS8_AStar_Node::compareTo);
            DS8_AStar_Node<Point> current = open.remove(0);
            if (current.getLocation().equals(end)) {
                return new DS8_Path_Solution<>(reconstructPath(current), current.getG());
            }
            closed.add(current);
            for (Point neighbor : getNeighbors(current.getLocation(), maze)) {
                if (containsLocation(closed, neighbor)) {
                    continue;
                }
                int tentativeG = current.getG() + 1;
                DS8_AStar_Node<Point> neighborNode = findNode(open, neighbor);
                if (neighborNode == null) {
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, tentativeG, heuristic(neighbor, end));
                    open.add(neighborNode);
                } 
                else if (tentativeG < neighborNode.getG()) {
                    open.remove(neighborNode);
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, tentativeG, neighborNode.getH());
                    open.add(neighborNode);
                }
            }
        }
        return null;
    }
    public static int aStar_JetPack(char[][] grid) {
        ArrayList<DS8_AStar_Node<Point>> open = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closed = new ArrayList<>();
        Point start = findChar(grid, 'S');
        Point end = findChar(grid, 'E');
        DS8_AStar_Node<Point> startNode = new DS8_AStar_Node<>(start, null, 0, heuristic(start, end));
        open.add(startNode);
        while (!open.isEmpty()) {
            open.sort(DS8_AStar_Node::compareTo);
            DS8_AStar_Node<Point> current = open.remove(0);
            if (current.getLocation().equals(end)) {
                return current.getG();
            }
            closed.add(current);
            for (Point neighbor : getNeighborsJetPack(current.getLocation(), grid)) {
                if (containsLocation(closed, neighbor)) {
                    continue;
                }
                int tentativeG = current.getG() + (grid[neighbor.x][neighbor.y] == 'O' ? 1 : 0);
                DS8_AStar_Node<Point> neighborNode = findNode(open, neighbor);
                if (neighborNode == null) {
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, tentativeG, heuristic(neighbor, end));
                    open.add(neighborNode);
                } 
                else if (tentativeG < neighborNode.getG()) {
                    open.remove(neighborNode);
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, tentativeG, neighborNode.getH());
                    open.add(neighborNode);
                }
            }
        }
        
        return -1;
    }
    private static int heuristic(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    private static ArrayList<Point> reconstructPath(DS8_AStar_Node<Point> node) {
        ArrayList<Point> path = new ArrayList<>();
        while (node != null) {
            path.add(node.getLocation());
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }
    private static boolean containsLocation(ArrayList<DS8_AStar_Node<Point>> list, Point location) {
        for (DS8_AStar_Node<Point> node : list) {
            if (node.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }
    private static DS8_AStar_Node<Point> findNode(ArrayList<DS8_AStar_Node<Point>> list, Point location) {
        for (DS8_AStar_Node<Point> node : list) {
            if (node.getLocation().equals(location)) {
                return node;
            }
        }
        return null;
    }
    private static ArrayList<Point> getNeighbors(Point p, char[][] maze) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int x = p.x + dx[i];
            int y = p.y + dy[i];
            if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 'W') {
                neighbors.add(new Point(x, y));
            }
        }
        return neighbors;
    }
    
    private static ArrayList<Point> getNeighborsJetPack(Point p, char[][] grid) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
        for (int i = 0; i < 8; i++) {
            int x = p.x + dx[i];
            int y = p.y + dy[i];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 'W') {
                neighbors.add(new Point(x, y));
            }
        }
        return neighbors;
    }
    public static Point findChar(char[][] maze, char a) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == a) {
                    return new Point(r, c);
                }
            }
        }
        return null;
    }
}