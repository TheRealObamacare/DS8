package SolutionsAndTests.CS_DS.u8.ChallengeSet4;

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

public class DS8_AStar_JetPack_Tests {
    char[][][] testGraphs1 =
            {
                            {{'S','P','O'},
                            {'P','O','P'},
                            {'O','O','E'}},

                           {{'O','S','O','O','P','P','O','O'},
                            {'O','O','O','P','O','P','O','O'},
                            {'O','O','O','O','O','P','O','O'},
                            {'O','O','E','P','P','O','O','O'}},

                            {{'O','O','O','O','O','O','O','O'},
                            {'E','O','P','P','O','P','O','S'},
                            {'O','O','O','O','O','P','O','O'},
                            {'O','O','P','O','O','O','O','O'}},

                    {       {'P','O','O','O','O','O','O','P','O','O','P'},
                            {'P','O','O','P','O','P','P','O','O','O','P'},
                            {'P','O','P','O','P','O','O','O','O','P','O'},
                            {'P','O','P','O','O','O','O','O','O','O','S'},
                            {'O','E','O','P','O','O','O','P','P','P','O'},},
            };

    int [] distances1 = {0,1,3,1};

    char[][][] testGraphs2 = {
            {{'P', 'P', 'E', 'O', 'P', 'P', 'P'}, {'P', 'P', 'S', 'P', 'P', 'P', 'P'}, {'P', 'P', 'O', 'P', 'P', 'P', 'P'}},
            {{'P', 'P'}, {'S', 'P'}, {'O', 'P'}, {'O', 'O'}, {'O', 'P'}, {'O', 'O'}, {'E', 'O'}, {'O', 'O'}},
            {{'O', 'O', 'S', 'E'}},
            {{'S', 'O', 'E', 'O', 'P', 'P'}},
            {{'S', 'E'}},
            {{'P', 'P', 'O', 'O'}, {'E', 'O', 'O', 'P'}, {'P', 'S', 'P', 'P'}},
            {{'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'P'}, {'O', 'E', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'S', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}},
            {{'S'}, {'O'}, {'O'}, {'P'}, {'O'}, {'E'}, {'O'}},
            {{'O', 'O', 'O', 'O', 'P', 'O'}, {'O', 'P', 'P', 'P', 'P', 'E'}, {'O', 'P', 'O', 'S', 'O', 'P'}},
            {{'O', 'S', 'O', 'O'}, {'O', 'O', 'P', 'E'}, {'O', 'O', 'O', 'O'}},
            {{'O', 'O', 'O'}, {'E', 'O', 'S'}},
            {{'O', 'O', 'O', 'O', 'P', 'S', 'E'}},
            {{'O'}, {'O'}, {'E'}, {'O'}, {'S'}, {'O'}},
            {{'S', 'O', 'O', 'O', 'O', 'E', 'O', 'O'}},
            {{'P', 'O', 'O', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'P', 'O', 'O', 'O'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'O', 'O', 'E', 'O'}, {'O', 'O', 'P', 'O', 'O'}},
            {{'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'E'}, {'O', 'S', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'P', 'O', 'O'}, {'O', 'O', 'O', 'O'}},
            {{'P', 'E', 'O', 'P', 'S', 'O', 'O'}},
            {{'P', 'P'}, {'P', 'P'}, {'P', 'P'}, {'P', 'E'}, {'O', 'P'}, {'P', 'P'}, {'P', 'P'}, {'P', 'S'}},
            {{'S', 'E', 'O', 'O', 'O'}},
            {{'O', 'O', 'P', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'O', 'P', 'O', 'O', 'O'}, {'P', 'P', 'O', 'O', 'O'}, {'O', 'E', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}},
            {{'S'}, {'P'}, {'P'}, {'E'}, {'P'}, {'O'}},
            {{'P', 'P', 'O'}, {'O', 'S', 'E'}},
            {{'O', 'S', 'P', 'E', 'O'}},
            {{'P', 'P', 'E', 'P', 'O'}, {'S', 'P', 'P', 'P', 'P'}, {'P', 'O', 'P', 'P', 'P'}},
            {{'O'}, {'O'}, {'O'}, {'O'}, {'O'}, {'S'}, {'O'}, {'E'}},
            {{'E'}, {'S'}, {'O'}},
            {{'P', 'P', 'P', 'P', 'O', 'S'}, {'O', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'O', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'E', 'P', 'O', 'P', 'P', 'O'}},
            {{'E', 'O', 'O', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'P', 'O'}},
            {{'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'S', 'O', 'O', 'O', 'O'}, {'O', 'O', 'E', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'P', 'O', 'O'}},
            {{'P', 'E', 'P', 'P', 'O', 'P', 'S'}},
    };
    int[] distances2 =
            {0, 2, 0, 1, 0, 0, 2, 3, 0, 0, 1, 0, 1, 4, 3, 1, 1, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 3, 1, 1};

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
        allowedImports.add("java.util.Arrays");
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
            Method jetPack = aStar.getMethod("aStar_JetPack", char[][].class);
            for(int x=0; x<testGraphs1.length; x++)
            {
                DS8_Path_Solution solution=(DS8_Path_Solution) jetPack.invoke(aStar,(Object)testGraphs1[x]);

                Assert.assertNotNull("aStar_JetPack("+Arrays.deepToString(testGraphs1[x])+") failed to produce a solution.",solution);
                checkSolution(testGraphs1[x], distances1[x], solution);


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
            Method jetPack = aStar.getMethod("aStar_JetPack", char[][].class);
            for(int x=0; x<testGraphs2.length; x++)
            {
                DS8_Path_Solution solution=(DS8_Path_Solution) jetPack.invoke(aStar,(Object)testGraphs2[x]);

                Assert.assertNotNull("aStar_JetPack("+Arrays.deepToString(testGraphs2[x])+") failed to produce a solution.",solution);
                checkSolution(testGraphs2[x], distances2[x], solution);
            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public void checkSolution(char[][] grid, int distance,DS8_Path_Solution student)
    {
        Assert.assertEquals("aStar_JetPack("+Arrays.deepToString(grid)+") failed to produce the distance.",distance,student.getDistance());

        ArrayList<Point> points = student.getPath();
        Assert.assertEquals("aStar_JetPack("+Arrays.deepToString(grid)+") failed to produce a path of that begins at the start location."+points,""+'S',""+grid[points.get(0).y][points.get(0).x]);
        Assert.assertEquals("aStar_JetPack("+Arrays.deepToString(grid)+") failed to produce a path of that ends at the end location.",""+'E',""+grid[points.get(points.size()-1).y][points.get(points.size()-1).x]);

        int pathCost=0;
        for(int x=1; x<points.size(); x++)
        {
            int pc = (int)points.get(x-1).getX();
            int pr = (int)points.get(x-1).getY();
            int cc = (int)points.get(x).getX();
            int cr = (int)points.get(x).getY();

            if(grid[cr][cc]=='O')
                pathCost++;
            Assert.assertTrue("aStar_JetPack("+Arrays.deepToString(grid)+") produced invalid moves. The moves were "+points,
                    (Math.abs(pc-cc)==0 && Math.abs(pr-cr)==1) ||
                            (Math.abs(pc-cc)==1 && Math.abs(pr-cr)==0) ||
                            (Math.abs(pc-cc)==1 && Math.abs(pr-cr)==1));

        }
        Assert.assertEquals("aStar_JetPack("+Arrays.deepToString(grid)+") actual path cost was not equals to the solution's cost.",distance,pathCost);

    }

    public boolean adjacent(Point a, Point b)
    {
        return ((a.x == b.x &&(a.y==b.y-1 || a.y==b.y+1)) ||
                (a.y == b.y &&(a.x==b.x-1 || a.x==b.x+1)));
    }
}
