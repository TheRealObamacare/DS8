import java.awt.Point;
import java.util.ArrayList;


public class DS8_BFS {
    public static boolean[][] visitor;
    public static DS8_Queue<ArrayList<Point>> queue = new DS8_Queue<>();
    public static ArrayList<Point> list;
    public static int breadthFirstSearch_Portals(char[][] maze)
    {
        queue = new DS8_Queue<>();
        visitor = new boolean[maze.length][maze[0].length];
        list = new ArrayList<>();
        for (int i = 0; i < maze.length; i++) {
            for (int e = 0; e < maze[0].length; e++) {
                if (maze[i][e] == 'S') {
                    list.add(new Point(i, e));
                    queue.offer(list);
                    visitor[i][e] = true;
                    break;
                }
            }
        }
        while(!queue.isEmpty())
        {
            list = queue.poll();
            Point location = list.getLast();
            if(maze[location.x][location.y] == 'E')
            {
                return list.size() - 1;
            }
            addToQueue(maze, new Point(location.x + 1, location.y), location);
            addToQueue(maze, new Point(location.x - 1, location.y), location);
            addToQueue(maze, new Point(location.x, location.y + 1), location);
            addToQueue(maze, new Point(location.x, location.y - 1), location);
            if ((maze[location.x][location.y] <= 'A' && maze[location.x][location.y] >= 'D') || (maze[location.x][location.y] <= 'a' && maze[location.x][location.y] >= 'd'))
            {
                Point portal = findChar(maze, inverseCase(maze[location.x][location.y]));
                if (!visitor[portal.x][portal.y])
                {
                    addToQueue(maze, portal, location);
                }
            }
        }
        return -1;
    }

    public static String breadthFirstSearch_Unweighted(String[] edges, String vertices, char start, char end)
    {

        DS8_Queue<String> Queue = new DS8_Queue<>();
        Queue.offer(start + "");
        boolean[] visited = new boolean[edges.length];
        while(!Queue.isEmpty())
        {
            String cur = Queue.poll();
            if(cur.charAt(cur.length() - 1) == end)
            {
                return cur;
            }
            for(int i = 0; i < edges.length; i++)
            {
                if (edges[i].charAt(0) == cur.charAt(cur.length() - 1) && !visited[i])
                {
                    Queue.offer(cur + edges[i].charAt(1));
                    visited[i] = true;
                }
                else if (edges[i].charAt(1) == cur.charAt(cur.length() - 1) && !visited[i])
                {
                    Queue.offer(cur + edges[i].charAt(0));
                    visited[i] = true;
                }
            }
        }
        return null;
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
            if (maze[goTo.x][goTo.y] == 'W' || visitor[goTo.x][goTo.y])
            {
                return;
            }
            list.add(goTo);
            queue.offer(list);
            visitor[goTo.x][goTo.y] = true;
        }
        catch (Exception e)
        {
            return;
        }
    }
}
