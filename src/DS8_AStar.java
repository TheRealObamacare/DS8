import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class DS8_AStar {

    public static DS8_Path_Solution<Point> aStar_Simple(char[][] grid) {
        Point start = null, end = null;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'S') start = new Point(x, y);
                if (grid[y][x] == 'E') end = new Point(x, y);
            }
        }

        ArrayList<DS8_AStar_Node<Point>> openList = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closedList = new ArrayList<>();
        openList.add(new DS8_AStar_Node<>(start, null, 0, heuristic(start, end)));

        while (!openList.isEmpty()) {
            DS8_AStar_Node<Point> current = Collections.min(openList);
            if (current.getLocation().equals(end)) {
                return constructPath(current);
            }

            openList.remove(current);
            closedList.add(current);

            for (Point neighbor : getNeighbors(current.getLocation(), grid)) {
                if (isInList(closedList, neighbor)) continue;

                int G = current.getG() + 1;
                DS8_AStar_Node<Point> neighborNode = getNodeFromList(openList, neighbor);
                if (neighborNode == null) {
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, heuristic(neighbor, end));
                    openList.add(neighborNode);
                } else if (G < neighborNode.getG()) {
                    openList.remove(neighborNode);
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, heuristic(neighbor, end));
                    openList.add(neighborNode);
                }
            }
        }
        return null;
    }

    private static int heuristic(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static ArrayList<Point> getNeighbors(Point p, char[][] grid) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i], ny = p.y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < grid[0].length && ny < grid.length && grid[ny][nx] != 'W') {
                neighbors.add(new Point(nx, ny));
            }
        }
        return neighbors;
    }

    private static boolean isInList(ArrayList<DS8_AStar_Node<Point>> list, Point p) {
        for (DS8_AStar_Node<Point> node : list) {
            if (node.getLocation().equals(p)) return true;
        }
        return false;
    }

    private static DS8_AStar_Node<Point> getNodeFromList(ArrayList<DS8_AStar_Node<Point>> list, Point p) {
        for (DS8_AStar_Node<Point> node : list) {
            if (node.getLocation().equals(p)) return node;
        }
        return null;
    }

    private static DS8_Path_Solution<Point> constructPath(DS8_AStar_Node<Point> node) {
        ArrayList<Point> path = new ArrayList<>();
        int distance = 0;
        while (node != null) {
            path.add(node.getLocation());
            node = node.getParent();
            distance++;
        }
        Collections.reverse(path);
        return new DS8_Path_Solution<>(path, distance - 1);
    }
    private static DS8_Path_Solution<Point> constructPath(DS8_AStar_Node<Point> node, int distance) {
        ArrayList<Point> path = new ArrayList<>();
        while (node != null) {
            path.add(node.getLocation());
            node = node.getParent();
        }
        Collections.reverse(path);
        return new DS8_Path_Solution<>(path, distance);
    }
    public static DS8_Path_Solution<Point> aStar_JetPack(char[][] grid) {
        Point start = null, end = null;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'S') start = new Point(x, y);
                if (grid[y][x] == 'E') end = new Point(x, y);
            }
        }
        ArrayList<DS8_AStar_Node<Point>> openList = new ArrayList<>();
        ArrayList<DS8_AStar_Node<Point>> closedList = new ArrayList<>();
        int G = 0;
        openList.add(new DS8_AStar_Node<>(start, null, G, 0));

        while (!openList.isEmpty()) {
            DS8_AStar_Node<Point> current = Collections.min(openList);
            if (current.getLocation().equals(end)) {
                return constructPath(current, current.getG());
            }

            openList.remove(current);
            closedList.add(current);

            for (Point neighbor : getNeighbors(current.getLocation(), grid)) {
                if (isInList(closedList, neighbor)) continue;
                G = current.getG();
                if (grid[neighbor.y][neighbor.x] == 'O') {
                    G+=1;
                }
                DS8_AStar_Node<Point> neighborNode = getNodeFromList(openList, neighbor);
                if (neighborNode == null) {
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, 0);
                    openList.add(neighborNode);
                }
                else if (G < neighborNode.getG()) {
                    openList.remove(neighborNode);
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, 0);
                    openList.add(neighborNode);
                }
            }
            ArrayList<Point> neighbors = new ArrayList<>();
            int[] dx = {-1, 1, -1, 1};
            int[] dy = {1, -1, -1, 1};
            for (int i = 0; i < 4; i++) {
                int nx = current.getLocation().x + dx[i], ny = current.getLocation().y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < grid[0].length && ny < grid.length && grid[ny][nx] != 'W') {
                    neighbors.add(new Point(nx, ny));
                }
            }
            for (Point neighbor : neighbors) {
                if (isInList(closedList, neighbor)) continue;
                G = current.getG();
                if (grid[neighbor.y][neighbor.x] == 'O') {
                    G+=1;
                }
                DS8_AStar_Node<Point> neighborNode = getNodeFromList(openList, neighbor);
                if (neighborNode == null) {
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, 0);
                    openList.add(neighborNode);
                }
                else if (G < neighborNode.getG()) {
                    openList.remove(neighborNode);
                    neighborNode = new DS8_AStar_Node<>(neighbor, current, G, 0);
                    openList.add(neighborNode);
                }
            }
        }
        return null;
    }
}