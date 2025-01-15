
import SolutionsAndTests.CS_DS.u8.ChallengeSet3.DS8_TerrainCost;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DS8_AStar_Simple_Tests
{
    char[][][] testGraphs1 =
            {
                    {{'S','-','E'}},

                    {{'E','W','W'},
                            {'-','-','S'}},

                    {{'E','-','W','-','-','-','-','-','W','-','-'},
                            {'W','-','W','-','-','W','W','-','-','-','W'},
                            {'W','-','W','-','-','W','-','-','W','W','W'},
                            {'W','-','-','-','W','-','-','-','W','-','S'},
                            {'W','W','W','W','W','W','W','-','-','W','-'},
                            {'W','W','W','W','W','W','W','-','-','-','-'}},

                    {{'-','-','-','-','-'},
                            {'W','S','W','-','-'},
                            {'W','-','W','-','-'},
                            {'W','-','W','W','-'},
                            {'W','W','W','E','-'},
                            {'W','W','W','-','-'}},

                    {{'S','-','W','-','-','E'},
                            {'W','-','-','-','-','W'},
                            {'W','-','W','-','-','W'},
                            {'W','-','-','-','-','-'},
                            {'W','-','-','W','-','W'},
                            {'W','-','W','-','-','W'}},

                    {{'S','-','W','-','W','E'},
                            {'W','-','-','-','-','W'},
                            {'W','-','W','-','-','W'},
                            {'W','-','-','-','-','-'},
                            {'W','-','-','W','-','W'},
                            {'W','-','W','-','-','W'}}
            };

    Integer[] testDistances1 = {2,3,23,9,7,null};

    char[][][] testGraphs2 = {
            {{'S', 'W'}, {'W', '-'}, {'-', 'W'}, {'-', 'E'}, {'W', 'W'}, {'W', 'W'}},
            {{'-', '-', '-', '-', '-', '-', '-', '-'}, {'-', 'W', '-', '-', 'W', '-', 'W', '-'}, {'-', '-', '-', '-', '-', '-', '-', 'S'}, {'-', '-', '-', 'W', 'W', '-', '-', '-'}, {'-', 'W', 'W', '-', '-', '-', '-', '-'}, {'W', '-', 'E', '-', '-', '-', 'W', 'W'}, {'-', '-', 'W', '-', '-', '-', 'W', '-'}},
            {{'-'}, {'S'}, {'E'}, {'-'}},
            {{'-', '-', '-', 'E', '-'}, {'W', '-', '-', '-', '-'}, {'-', '-', '-', '-', '-'}, {'-', 'W', '-', '-', '-'}, {'-', '-', '-', 'S', '-'}, {'-', '-', '-', 'W', '-'}},
            {{'E'}, {'-'}, {'-'}, {'W'}, {'S'}},
            {{'W', '-', '-', '-', '-', '-', '-', 'W'}, {'-', '-', '-', '-', '-', '-', '-', '-'}, {'-', '-', 'W', '-', '-', '-', 'W', '-'}, {'-', 'S', '-', 'W', '-', '-', '-', 'W'}, {'-', '-', '-', '-', '-', 'E', '-', 'W'}},
            {{'-', 'E', 'S', '-'}},
            {{'-', '-', '-', '-', 'S'}, {'-', 'W', 'W', 'E', 'W'}},
            {{'-', 'S', 'W'}, {'W', 'E', '-'}},
            {{'S', '-', 'W', 'W', 'E', '-', '-', 'W'}, {'-', '-', '-', 'W', '-', '-', 'W', 'W'}},
            {{'-', '-', '-'}, {'W', '-', '-'}, {'W', '-', 'S'}, {'-', '-', '-'}, {'-', '-', 'W'}, {'E', 'W', 'W'}, {'-', '-', '-'}},
            {{'W', 'S', 'W', '-'}, {'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W'}, {'-', 'W', '-', 'W'}, {'W', '-', 'E', '-'}, {'W', 'W', 'W', 'W'}},
            {{'-', 'W', '-', 'W', 'W', '-', '-', '-'}, {'-', '-', '-', '-', '-', 'W', 'S', '-'}, {'W', '-', '-', '-', 'W', '-', 'E', '-'}},
            {{'S', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'E', 'W'}, {'W', '-', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', '-', 'W', '-'}, {'-', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'W', 'W'}},
            {{'-', 'S', '-', 'E', '-'}},
            {{'S', 'W', 'W', 'W', 'W', 'W', 'W'}, {'W', '-', 'W', 'W', 'W', 'E', '-'}, {'W', 'W', 'W', 'W', 'W', 'W', 'W'}},
            {{'W', 'W'}, {'W', '-'}, {'W', '-'}, {'W', 'E'}, {'S', 'W'}, {'W', 'W'}, {'-', 'W'}},
            {{'-', 'S', 'W', 'W', '-', '-'}, {'W', 'W', '-', 'W', 'W', '-'}, {'W', '-', 'W', 'W', '-', '-'}, {'W', '-', 'W', '-', 'E', 'W'}, {'-', '-', '-', '-', 'W', '-'}, {'W', '-', '-', '-', '-', '-'}, {'W', 'W', '-', 'W', '-', '-'}},
            {{'S', '-', 'W', 'W', '-', '-', 'W'}, {'W', 'E', '-', 'W', 'W', '-', 'W'}, {'W', 'W', '-', '-', '-', 'W', 'W'}, {'W', 'W', 'W', 'W', '-', '-', 'W'}},
            {{'W', 'W', 'W'}, {'S', 'W', '-'}, {'W', 'W', 'W'}, {'W', '-', 'W'}, {'-', 'W', 'W'}, {'-', 'E', 'W'}},
            {{'-', 'W', 'W', 'E', 'W', '-'}, {'W', 'W', 'W', 'W', '-', 'W'}, {'-', '-', '-', 'S', 'W', '-'}, {'W', '-', '-', 'W', 'W', '-'}, {'-', 'W', '-', '-', 'W', 'W'}, {'-', '-', '-', 'W', '-', 'W'}, {'W', 'W', 'W', 'W', 'W', 'W'}, {'-', '-', 'W', '-', '-', '-'}},
            {{'-', 'W', 'W'}, {'W', 'W', '-'}, {'W', 'W', 'W'}, {'-', 'W', 'W'}, {'W', '-', 'W'}, {'E', '-', 'W'}, {'W', 'S', '-'}, {'-', '-', 'W'}},
            {{'-', '-'}, {'-', '-'}, {'-', 'S'}, {'-', 'W'}, {'E', '-'}},
            {{'-', '-', '-', 'S'}, {'E', '-', '-', '-'}},
            {{'W', 'W', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', '-', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'W', 'W', 'W'}, {'W', 'W', 'W', 'W', 'W', 'E', 'W'}, {'W', 'S', 'W', 'W', '-', 'W', '-'}},
            {{'E', 'W', '-', 'W', '-', '-', '-'}, {'-', '-', '-', '-', '-', '-', '-'}, {'-', '-', '-', '-', 'W', 'W', '-'}, {'-', 'W', '-', '-', '-', 'W', '-'}, {'-', '-', 'W', 'W', 'S', 'W', 'W'}},
            {{'S', 'E'}},
            {{'W', '-'}, {'E', 'S'}, {'W', 'W'}, {'W', 'W'}, {'-', 'W'}, {'-', 'W'}},
            {{'-', '-', 'W', 'S'}, {'-', '-', 'W', '-'}, {'-', '-', '-', 'W'}, {'W', '-', '-', '-'}, {'-', 'E', 'W', '-'}, {'-', '-', '-', '-'}},
            {{'-'}, {'-'}, {'E'}, {'S'}, {'W'}, {'W'}, {'-'}, {'W'}}
    };
    Integer[] testDistances2 =
            {null, 8, 1, 4, null, 5, 1, 2, 1, null, 5, null, 1, null, 2, null, null, null, 2, null, null, 2, 3, 4, null, 8, 1, 1, null, 1};

    public String generateClassName(String name) {
        if (getClass().toString().contains(".")) {
            return getClass().toString().substring(6, getClass().toString().lastIndexOf(".") + 1) + name;
        }
        return name;
    }

    public ArrayList<String> allowedImports = new ArrayList<>();

    @Before
    public void setup()
    {
        allowedImports.add("java.util.ArrayList");
        allowedImports.add("java.util.Collections");
        allowedImports.add("java.awt.Point");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS8_AStar";
        String fileName = "src/"+generateClassName(className).replaceAll("\\.","/")+".java";
        boolean allowedOnly = true;
        ArrayList<String> invalidImport = new ArrayList<>();
        try
        {

            File file = new File(fileName);
            Scanner fromFile = new Scanner(file);
            while(fromFile.hasNextLine())
            {
                String line = fromFile.nextLine().trim();
                if(line.contains("import"))
                {
                    boolean good = false;
                    for(String allowed: allowedImports)
                    {
                        if(line.matches("\\s*import\\s+"+allowed+"\\s*;\s*(//\\.*)?"))
                            good=true;
                    }
                    if(!good)
                    {
                        allowedOnly=false;
                        invalidImport.add(line);
                    }
                }

            }
        }
        catch(Exception e)
        {
            Assert.assertTrue("Missing File: "+className+".java",false);
            allowedOnly = false;
        }

        Assert.assertTrue("Invalid imports: "+invalidImport,allowedOnly);
    }

    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> aStar = Class.forName(generateClassName("DS8_AStar"));
            Method simple = aStar.getMethod("aStar_Simple", char[][].class);
            for(int x=0; x<testGraphs1.length; x++)
            {
                DS8_Path_Solution solution=(DS8_Path_Solution) simple.invoke(aStar,(Object)testGraphs1[x]);
                if(testDistances1[x]==null)
                    Assert.assertNull("aStar_Simiple("+Arrays.deepToString(testGraphs1[x])+") failed to produce null.",solution);
                else
                {
                   Assert.assertNotNull("aStar_Simiple("+Arrays.deepToString(testGraphs1[x])+") failed to produce a solution.",solution);
                    checkSolution(testGraphs1[x], testDistances1[x], solution);

                }
            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        try
        {
            Class<?> aStar = Class.forName(generateClassName("DS8_AStar"));
            Method simple = aStar.getMethod("aStar_Simple", char[][].class);
            for(int x=0; x<testGraphs2.length; x++)
            {
                DS8_Path_Solution solution=(DS8_Path_Solution) simple.invoke(aStar,(Object)testGraphs2[x]);
                if(testDistances2[x]==null)
                    Assert.assertNull("aStar_Simiple("+Arrays.deepToString(testGraphs2[x])+") failed to produce null.",solution);
                else
                {
                    Assert.assertNotNull("aStar_Simiple("+Arrays.deepToString(testGraphs2[x])+") failed to produce a solution.",solution);
                    checkSolution(testGraphs2[x], testDistances2[x], solution);

                }
            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public void checkSolution(char[][] grid, Integer steps,DS8_Path_Solution student)
    {
        assertEquals("aStar("+Arrays.deepToString(grid)+") failed to produce the correct steps.",steps.intValue(),student.getDistance());
        assertEquals("aStar("+Arrays.deepToString(grid)+") failed to produce a path of the correct length.",steps.intValue()+1,student.getPath().size());

        ArrayList<Point> points = student.getPath();
        assertEquals("aStar("+Arrays.deepToString(grid)+") failed to produce a path of that begins at the start location.",""+'S',""+grid[points.get(0).y][points.get(0).x]);
        assertEquals("aStar("+Arrays.deepToString(grid)+") failed to produce a path of that ends at the end location.",""+'E',""+grid[points.get(points.size()-1).y][points.get(points.size()-1).x]);

        for(int pos=1; pos<points.size()-1; pos++)
            assertEquals("aStar("+Arrays.deepToString(grid)+") failed due to creating a solution path that contains wall(s).",""+'-',""+grid[points.get(pos).y][points.get(pos).x]);

        for(int pos=1; pos<points.size(); pos++)
            assertEquals("aStar("+Arrays.deepToString(grid)+") failed due to some points not being adjacent to the prior points. The invalid path is"+points,true,adjacent(points.get(pos-1),points.get(pos)));
    }

    public boolean adjacent(Point a, Point b)
    {
        return ((a.x == b.x &&(a.y==b.y-1 || a.y==b.y+1)) ||
                (a.y == b.y &&(a.x==b.x-1 || a.x==b.x+1)));
    }
}
