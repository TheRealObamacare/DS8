import java.awt.Point;


public class DS8_BFS {
    public static boolean[][] visited;
    public static DS8_Queue<Point> queue = new DS8_Queue<>();
    public static Point pointa;
    public static Point pointA;
    public static Point pointb;
    public static Point pointB;
    public static Point pointc;
    public static Point pointC;
    public static Point pointd;
    public static Point pointD;
    public static int breadthFirstSearch_Portals(char[][] maze)
    {
        pointa = findChar(maze, 'a');
        pointA = findChar(maze, 'A');
        pointb = findChar(maze, 'b');
        pointB = findChar(maze, 'B');
        pointc = findChar(maze, 'c');
        pointC = findChar(maze, 'C');
        pointd = findChar(maze, 'd');
        pointD = findChar(maze, 'D');
        int ans = 0;
        queue = new DS8_Queue<>();
        visited = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int e = 0; e < maze[0].length; e++) {
                if (maze[i][e] == 'S') {
                    queue.offer(new Point(i, e));
                    visited[i][e] = true;
                    break;
                }
            }
        }
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                Point location = queue.poll();
                if(maze[location.x][location.y] == 'E')
                {
                    return ans;
                }
                if (location.y + 1 < maze[0].length && !visited[location.x][location.y + 1] && maze[location.x][location.y + 1] != 'W')
                {
                    visited[location.x][location.y + 1] = true;
                    queue.offer(new Point(location.x, location.y + 1));
                }
                if (location.x + 1 < maze.length && !visited[location.x + 1][location.y] && maze[location.x + 1][location.y] != 'W')
                {
                    visited[location.x + 1][location.y] = true;
                    queue.offer(new Point(location.x + 1, location.y));
                }
                if (location.y - 1 >= 0 && visited[location.x][location.y - 1] == false && maze[location.x][location.y - 1] != 'W')
                {
                    visited[location.x][location.y - 1] = true;
                    queue.offer(new Point(location.x, location.y - 1));
                }
                if (location.x - 1 >= 0 && visited[location.x - 1][location.y] == false && maze[location.x - 1][location.y] != 'W')
                {
                    visited[location.x - 1][location.y] = true;
                    queue.offer(new Point(location.x - 1, location.y));
                }
                if (maze[location.x][location.y] == 'A' && visited[pointa.x][pointa.y] == false)
                {
                    visited[pointa.x][pointa.y] = true;
                    queue.offer(pointa);
                }
                else if (maze[location.x][location.y] == 'a' && visited[pointA.x][pointA.y] == false)
                {
                    visited[pointA.x][pointA.y] = true;
                    queue.offer(pointA);
                }
                else if (maze[location.x][location.y] == 'B' && visited[pointb.x][pointb.y] == false)
                {
                    visited[pointb.x][pointb.y] = true;
                    queue.offer(pointb);
                }
                else if (maze[location.x][location.y] == 'b' && visited[pointB.x][pointB.y] == false)
                {
                    visited[pointB.x][pointB.y] = true;
                    queue.offer(pointB);
                }
                else if (maze[location.x][location.y] == 'C' && visited[pointc.x][pointc.y] == false)
                {
                    visited[pointc.x][pointc.y] = true;
                    queue.offer(pointc);
                }
                else if (maze[location.x][location.y] == 'c' && visited[pointC.x][pointC.y] == false)
                {
                    visited[pointC.x][pointC.y] = true;
                    queue.offer(pointC);
                }
                else if (maze[location.x][location.y] == 'D' && visited[pointd.x][pointd.y] == false)
                {
                    visited[pointd.x][pointd.y] = true;
                    queue.offer(pointd);
                }
                else if (maze[location.x][location.y] == 'd' && visited[pointD.x][pointD.y] == false)
                {
                    visited[pointD.x][pointD.y] = true;
                    queue.offer(pointD);
                }

            }
            ans++;
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
            if (maze[goTo.x][goTo.y] == 'W' || visited[goTo.x][goTo.y])
            {
                return;
            }
            queue.offer(goTo);
            visited[goTo.x][goTo.y] = true;
        }
        catch (Exception e)
        {
            return;
        }
    }
}
