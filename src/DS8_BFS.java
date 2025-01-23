import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;

public class DS8_BFS {
    public static Point[][] visitor;
    public static DS8_Queue<Point> queue = new DS8_Queue<>();

    public static int breadthFirstSearch_Portals(char[][] maze)
    {
        queue = new DS8_Queue<>();
        visitor = new Point[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++)
        {
            for(int e = 0; e < maze[0].length; e++)
            {
                if(maze[i][e] == 'S')
                {
                    queue.offer(new Point(i, e));
                    visitor[i][e] = new Point(-1, -1);
                    break;
                }
            }
        }
        while(!queue.isEmpty())
        {
            Point location = queue.poll();
            if(maze[location.x][location.y] == 'E')
            {
                return getDistance(findChar(maze, 'S'), location);
            }
            addToQueue(maze, new Point(location.x + 1, location.y), location);
            addToQueue(maze, new Point(location.x - 1, location.y), location);
            addToQueue(maze, new Point(location.x, location.y + 1), location);
            addToQueue(maze, new Point(location.x, location.y - 1), location);
            if ((maze[location.x][location.y] <= 'A' && maze[location.x][location.y] >= 'D') || (maze[location.x][location.y] <= 'a' && maze[location.x][location.y] >= 'd'))
            {
                Point portal = findChar(maze, inverseCase(maze[location.x][location.y]));
                if (visitor[portal.x][portal.y] == null)
                {
                    addToQueue(maze, portal, location);
                }
            }
        }
        return getDistance(findChar(maze, 'S'), findChar(maze, 'E'));
    }

    public static String breadthFirstSearch_Unweighted(String[] edges, String vertices, char start, char end)
    {
        DS8_Queue<ArrayList<Point>> Queue = new DS8_Queue<>();
        Arrays.sort(edges);
        for(int i = 0; i < vertices.length(); i++)
        {
            if(vertices.charAt(i) == start)
            {
                Queue.offer(new ArrayList<>(Arrays.asList(new Point(i, -1))));
                visitor[i][0] = new Point(-1, -1);
                break;
            }
        }
        TreeMap<Character, Character> yay = (createMap(edges));
        boolean[] visited = new boolean[yay.entrySet().size()];
        while(!Queue.isEmpty())
        {
            ArrayList<Point> location = Queue.poll();
            Point cur = location.getLast();
            if(vertices.charAt(cur.x) == end)
            {
                String k = "";
                for (Point p : location)
                {
                    k += vertices.charAt(p.x);
                }
            }
            addToQueue(location, yay, visited);
        }
        return "";
    }
    public static void addToQueue(ArrayList<Point> location, TreeMap<Character, Character> yay, boolean[] visited)
    {
        try
        {
            
        }
        catch (Exception e)
        {

        }
    }

    private static int getDistance(Point start, Point end) {
        if(visitor[end.x][end.y] == null)
        {
            return -1;
        }
        int distance = 0;
        Point cur = new Point(end);
        while(!cur.equals(start))
        {
            System.out.print(cur.x + " " + cur.y + " ");
            cur = visitor[cur.x][cur.y];
            distance++;
        }
        return distance;
    }
    public static char inverseCase(char c)
    {
        if (c >= 'a' && c <= 'z')
        {
            return (char)(c - 32);
        }
        else if (c >= 'A' && c <= 'Z')
        {
            return (char)(c + 32);
        }
        System.out.println(c);
        return c;
    }
    public static Point findChar(char[][] maze, char a)
    {
        for(int r = 0; r < maze.length; r++)
        {
            for(int c = 0; c < maze[0].length; c++)
            {
                if(maze[r][c] == a)
                {
                    return new Point(r, c);
                }
            }
        }
        return null;
    }
    public static void addToQueue(char[][] maze, Point goTo, Point cur)
    {
        try
        {
            if (maze[goTo.x][goTo.y] == 'W' || visitor[goTo.x][goTo.y] != null)
            {
                return;
            }
            queue.offer(goTo);
            visitor[goTo.x][goTo.y] = cur;
        }
        catch (Exception e)
        {
            return;
        }
    }
    public static TreeMap<Character, Character> createMap(String[] edges)
    {
        TreeMap<Character, Character> map = new TreeMap<>();
        for(String edge : edges)
        {
            map.put(edge.charAt(0), edge.charAt(1));
            map.put(edge.charAt(1), edge.charAt(0));
        }
        return map;
    }

}
