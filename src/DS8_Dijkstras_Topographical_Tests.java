
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DS8_Dijkstras_Topographical_Tests
{
    char[][][] testGraphs1 =
            {
                    {{'S','M','E'},
                            {'G','G','G'}},

                    {{'G','E','F','F','G'},
                            {'M','M','M','F','G'},
                            {'G','F','M','F','F'},
                            {'G','S','M','F','G'},
                            {'G','G','F','F','G'}},

                    {{'G','M','M','G','G'},
                            {'G','I','I','M','G'},
                            {'G','I','M','F','F'},
                            {'G','I','M','F','G'},
                            {'S','I','E','F','G'}},

                    {{'G','G','W','G','S'},
                            {'G','W','W','M','F'},
                            {'W','W','W','W','W'},
                            {'F','W','M','F','G'},
                            {'E','M','W','F','G'}},

                    {{'S','G','M','G','G','G'},
                            {'I','F','I','M','I','I'},
                            {'F','F','F','E','F','F'}},

                    {{'G','E','M'},
                            {'G','I','F'},
                            {'W','I','M'},
                            {'W','I','M'},
                            {'W','S','F'}}
            };

    String[][] costs1 = {
            {"S0","E1","M8","G1"},
            {"S0","E1","M8","F4","G1","W6"},
            {"S0","E1","M8","F4","G1","I-1"},
            {"S0","E3","M8","F4","G1","W6"},
            {"S0","E1","M8","F4","G1","I-1"},
            {"S0","E1","M8","F4","G1","I-1","W5"}
    };

    int[] answers1 = {4,12,34,23,14,18};

    char[][][] testGraphs2 =
            {
                    {{'S','M','E'},
                            {'G','G','G'}},

                    {{'G','E','F','F','G'},
                            {'M','M','M','F','G'},
                            {'G','F','M','F','F'},
                            {'G','S','M','F','G'},
                            {'G','G','F','F','G'}},

                    {{'G','M','M','G','G'},
                            {'G','I','I','M','G'},
                            {'G','I','M','F','F'},
                            {'G','I','M','F','G'},
                            {'S','I','E','F','G'}},

                    {{'G','G','W','G','S'},
                            {'G','W','W','M','F'},
                            {'W','W','W','W','W'},
                            {'F','W','M','F','G'},
                            {'E','M','W','F','G'}},

                    {{'S','G','M','G','G','G'},
                            {'I','F','I','M','I','I'},
                            {'F','F','F','E','F','F'}},

                    {{'G','E','M'},
                            {'G','I','F'},
                            {'W','I','M'},
                            {'W','I','M'},
                            {'W','S','F'}}
            };

    String[][] costs2= {
            {"S0","E1","M8","G1"},
            {"S0","E1","M8","F4","G1","W6"},
            {"S0","E1","M8","F4","G1","I-1"},
            {"S0","E3","M8","F4","G1","W6"},
            {"S0","E1","M8","F4","G1","I-1"},
            {"S0","E1","M8","F4","G1","I-1","W5"}
    };

    int[] answers2 = {4,12,34,23,14,18};

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
        String className = "DS8_Dijkstras";
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
            Class<?> dijkstras = Class.forName(generateClassName("DS8_Dijkstras"));
            Method topigraphical = dijkstras.getMethod("dijkstras_Topographical", char[][].class,ArrayList.class);

            for(int x=0; x<testGraphs1.length; x++)
            {
                ArrayList<DS8_TerrainCost> terrainCosts = new ArrayList<>();
                for(String s: costs1[x])
                {
                    char type = s.charAt(0);
                    int cost = Integer.parseInt(s.substring(1));
                    terrainCosts.add(new DS8_TerrainCost(type,cost));
                }
                int distStudent = (int)topigraphical.invoke(dijkstras,testGraphs1[x],terrainCosts);
                assertEquals("Path.dijkstras("+Arrays.deepToString(testGraphs1[x])+","+terrainCosts+") produced the correct distance.",answers1[x],distStudent);

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
            Class<?> dijkstras = Class.forName(generateClassName("DS8_Dijkstras"));
            Method topigraphical = dijkstras.getMethod("dijkstras_Topographical", char[][].class,ArrayList.class);

            for(int x=0; x<testGraphs2.length; x++)
            {
                ArrayList<DS8_TerrainCost> terrainCosts = new ArrayList<>();
                for(String s: costs2[x])
                {
                    char type = s.charAt(0);
                    int cost = Integer.parseInt(s.substring(1));
                    terrainCosts.add(new DS8_TerrainCost(type,cost));
                }
                int distStudent = (int)topigraphical.invoke(dijkstras,testGraphs2[x],terrainCosts);
                assertEquals("Path.dijkstras("+Arrays.deepToString(testGraphs2[x])+","+terrainCosts+") produced the correct distance.",answers1[x],distStudent);

            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
