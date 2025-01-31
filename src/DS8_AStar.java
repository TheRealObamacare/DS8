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
            Collections.sort(open -> open);
            DS8_AStar_Node cur = open.remove(0);
            if (cur.getLocation().equals('E'))
            {
                return new DS8_Path_Solution(cur.getPath(), cur.getG());
            }
            closed.add(cur);
            addToList(maze, );
            addToList(maze, );
            addToList(maze, );
            addToList(maze, );
        }
    }
}
