import java.awt.Point;
public class DS8_DFS
{
    public static DS8_Stack<Point> stack = new DS8_Stack<>();
    public static boolean[][] visited;
    public static boolean depthFirstSearch_Simple(char[][]maze)
    {
        visited = new boolean[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++)
        {
            for(int e = 0; e < maze[0].length; e++)
            {
                if(maze[i][e] == 'S')
                {
                    stack.push(new Point(i, e));
                    visited[i][e] = true;
                    break;
                }
            }
        }
        while(!stack.isEmpty())
        {
            Point location = stack.pop();
            visited[location.x][location.y] = true;
            if (maze[location.x][location.y] == 'E')
            {
                return true;
            }
            addToStack(maze, new Point(location.x + 1, location.y));
            addToStack(maze, new Point(location.x - 1, location.y));
            addToStack(maze, new Point(location.x, location.y + 1));
            addToStack(maze, new Point(location.x, location.y - 1));
        }
        return false;
    }
    public static void addToStack(char[][] maze, Point cur)
    {
        try
        {
            if (maze[cur.x][cur.y] != 'W' && !visited[cur.x][cur.y])
            {
                stack.push(cur);
                visited[cur.x][cur.y] = true;
            }
        }
        catch (Exception e)
        {
            return;
        }
    }
}