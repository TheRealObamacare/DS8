import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DS8_AStar
{
    public static DS8_Path_Solution aStar_Simple(char[][] maze)
    {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        ArrayList<DS8_AStar_Node> open = new ArrayList<DS8_AStar_Node>();
        ArrayList<DS8_AStar_Node> closed = new ArrayList<DS8_AStar_Node>();
        DS8_AStar_Node start = new DS8_AStar_Node(0, null,0,1);
        open.add(start);
        while(!open.isEmpty())
        {
            open.sort(DS8_AStar_Node::compareTo);
            DS8_AStar_Node cur = open.remove(0);
            if (cur.getLocation().equals('E'))
            {
                return new DS8_Path_Solution(cur.getPath(), cur.getG());
            }
            closed.add(cur);
            Point p = findChar(maze, cur.getLocation());
            addToList(maze, open, closed, cur,  p.x+ 1, p.y);
            addToList(maze, open, closed, cur, p.x - 1, p.y);
            addToList(maze, open, closed, cur, p.x, p.y + 1);
            addToList(maze, open, closed, cur, p.x, p.y - 1);
        }
    }
    public static void addToList(char[][] maze, ArrayList<DS8_AStar_Node> open, ArrayList<DS8_AStar_Node> closed, DS8_AStar_Node cur, int x, int y)
    {
        try
        {
            if(!closed.contains(maze[x][y]) && maze[x][y] != 'W')
            {
                if (open.contains(maze[x][y]))
                {

                }
            }
        }
        catch (Exception e)
        {
            return;
        }
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
