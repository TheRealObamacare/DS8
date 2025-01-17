import java.awt.Point;

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
            if ((maze[location.x][location.y] <= 'A' && maze[location.x][location.y] >= 'D') || (maze[location.x][location.y] <= 'a' && maze[location.x][location.y] >= 'd'))
            {
                Point teleport = findChar(maze, inverseCase(maze[location.x][location.y]));
                if (visitor[teleport.x][teleport.y] == null)
                {
                    queue.offer(teleport);
                    visitor[teleport.x][teleport.y] = location;
                }
            }
            addToQueue(maze, new Point(location.x + 1, location.y), location);
            addToQueue(maze, new Point(location.x - 1, location.y), location);
            addToQueue(maze, new Point(location.x, location.y + 1), location);
            addToQueue(maze, new Point(location.x, location.y - 1), location);
        }
        return getDistance(findChar(maze, 'S'), findChar(maze, 'E'));
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
}
