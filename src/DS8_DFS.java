import java.awt.Point;
public class DS8_DFS
{
    public static DS8_Stack<Point> stack = new DS8_Stack<>();
    public static boolean[][] visited;
    public static boolean depthFirstSearch_Simple(char[][]maze)
    {
        stack = new DS8_Stack<>();
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
                System.out.println("Pushing: " + cur.x + ", " + cur.y);
                visited[cur.x][cur.y] = true;
            }
        }
        catch (Exception e)
        {
            return;
        }
    }
    public static boolean depthFirstSearch_Portals(char[][] maze)
    {
        stack = new DS8_Stack<>();
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
            if (maze[location.x][location.y] == 'A' || maze[location.x][location.y] == 'B' || maze[location.x][location.y] == 'C' || maze[location.x][location.y] == 'D'|| maze[location.x][location.y] == 'a'|| maze[location.x][location.y] == 'b'|| maze[location.x][location.y] == 'c'|| maze[location.x][location.y] == 'd')
            {

                Point teleport = findChar(maze, inverseCase(maze[location.x][location.y]));
                if (!visited[teleport.x][teleport.y])
                {
                    stack.push(teleport);
                    visited[teleport.x][teleport.y] = true;
                }
            }
            addToStack(maze, new Point(location.x + 1, location.y));
            addToStack(maze, new Point(location.x - 1, location.y));
            addToStack(maze, new Point(location.x, location.y + 1));
            addToStack(maze, new Point(location.x, location.y - 1));
        }
        return false;
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
}
/*
if (maze[location.x][location.y] == 'A' || maze[location.x][location.y] == 'B' || maze[location.x][location.y] == 'C' || maze[location.x][location.y] == 'D'|| maze[location.x][location.y] == 'a'|| maze[location.x][location.y] == 'b'|| maze[location.x][location.y] == 'c'|| maze[location.x][location.y] == 'd')
            {
                Point teleport = findChar(maze, inverseCase(maze[location.x][location.y]));
                stack.push(teleport);
                visited[teleport.x][teleport.y] = true;
            }
 */