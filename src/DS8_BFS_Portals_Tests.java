
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

public class DS8_BFS_Portals_Tests
{
    char[][][] staticGrids =
            {
                    {{'S','-','E'}},

                    {{'E','W','A','W'},
                            {'a','W','-','S'}},

                    {{'E','-','W','-','-','-','-','a','W','-','D'},
                            {'W','A','W','-','-','W','W','-','W','c','W'},
                            {'W','-','W','-','b','W','-','-','W','W','W'},
                            {'W','-','-','-','W','B','-','-','W','d','S'},
                            {'W','W','W','W','W','W','W','-','-','W','W'},
                            {'W','W','W','W','W','W','W','-','-','C','-'}},

                    {{'b','-','a','W','-'},
                            {'W','S','W','-','-'},
                            {'C','B','W','-','-'},
                            {'W','A','W','-','W'},
                            {'W','W','W','-','W'},
                            {'c','-','-','-','E'}},

                    {{'S','-','W','-','-','d'},
                            {'W','-','W','B','-','W'},
                            {'W','a','W','-','-','W'},
                            {'E','W','A','-','-','D'},
                            {'W','C','W','W','c','W'},
                            {'W','W','W','b','-','W'}}
            };

    int[] staticResults = {2,4,15,7,-1};

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
        allowedImports.add("java.awt.Point");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS8_BFS";
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
            Class<?> dfs = Class.forName(generateClassName("DS8_BFS"));
            Method simple = dfs.getMethod("breadthFirstSearch_Portals", char[][].class);

            for(int g=0;g<staticGrids.length; g++)
                assertEquals("breadthFirstSearch_Portals("+ Arrays.deepToString(staticGrids[g])+") failed to produce the correct result."
                        ,staticResults[g],simple.invoke(dfs,(Object)staticGrids[g]));
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
            Class<?> dfs = Class.forName(generateClassName("DS8_BFS"));
            Method simple = dfs.getMethod("breadthFirstSearch_Portals", char[][].class);

            for(int g=0;g<tests.length; g++)
                assertEquals("breadthFirstSearch_Portals("+ Arrays.deepToString(tests[g])+")"+g+" failed to produce the correct result."
                        ,results[g],simple.invoke(dfs,(Object)tests[g]));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    char[][][] tests = {

            {{'W', 'c', 'W', 'W', 'W', 'W', 'W'},
                    {'W', 'W', 'W', 'W', 'd', '-', 'W'},
                    {'C', 'W', 'W', 'b', 'W', 'W', 'B'},
                    {'-', '-', '-', '-', '-', 'W', 'E'},
                    {'D', 'W', 'S', 'W', 'a', 'A', '-'}},


            {{'W', 'W', 'W', 'W', 'E', 'W'},
                    {'W', '-', 'W', 'W', '-', 'W'},
                    {'W', '-', '-', '-', 'W', 'W'},
                    {'-', 'W', 'a', 'W', 'W', 'W'},
                    {'A', 'W', 'W', '-', 'S', 'W'}},


            {{'A', 'W'},
                    {'a', '-'},
                    {'W', 'W'},
                    {'W', 'E'},
                    {'S', '-'},
                    {'W', 'W'},
                    {'-', '-'}},


            {{'A', 'W', 'b', 'W', 'W', 'W', 'W'},
                    {'W', '-', 'B', 'W', '-', 'a', '-'},
                    {'W', 'W', 'W', 'W', 'W', 'E', 'S'},
                    {'-', 'W', '-', '-', 'W', 'W', 'W'}},


            {{'W', 'B', '-', '-', 'd', 'W', 'S'},
                    {'W', 'C', 'D', 'a', '-', '-', 'W'},
                    {'A', 'E', '-', '-', 'b', 'c', 'W'}},


            {{'a', 'W', 'A', 'b'},
                    {'W', 'S', 'B', 'E'}},


            {{'W', 'E', 'W', 'W'},
                    {'-', 'W', 'A', 'S'},
                    {'-', 'W', 'W', 'W'},
                    {'W', 'W', '-', 'W'},
                    {'W', 'W', 'W', 'a'},
                    {'W', '-', 'W', 'W'}},


            {{'-', 'W', '-', 'C', '-'},
                    {'-', 'W', '-', 'W', '-'},
                    {'W', '-', 'd', 'b', 'a'},
                    {'W', 'S', 'W', '-', '-'},
                    {'W', 'W', '-', 'c', 'W'},
                    {'E', 'D', 'W', 'A', 'B'}},


            {{'W', 'W'},
                    {'E', 'S'},
                    {'A', 'b'},
                    {'-', 'a'},
                    {'-', 'B'}},


            {{'B', 'W', 'W', '-', 'W'},
                    {'c', 'W', 'S', 'C', 'W'},
                    {'E', 'A', 'b', 'W', 'W'},
                    {'-', 'W', 'W', 'W', 'W'},
                    {'-', '-', 'D', 'W', '-'},
                    {'W', 'd', 'W', '-', 'a'}},


            {{'b', 'S', '-', '-', 'c', 'W', '-'},
                    {'-', 'W', '-', '-', 'A', 'D', '-'},
                    {'W', 'W', 'W', 'W', '-', 'W', 'a'},
                    {'C', 'B', 'W', 'W', 'd', 'W', 'E'}},


            {{'W', 'W', '-', 'W', 'b'},
                    {'S', '-', 'W', 'W', 'W'},
                    {'W', '-', 'W', '-', 'W'},
                    {'a', 'W', 'W', 'W', '-'},
                    {'W', '-', '-', 'A', 'E'},
                    {'W', 'B', 'W', 'W', 'W'}},


            {{'W', 'b', 'a', 'W', 'S', 'W', 'W'},
                    {'W', 'W', 'W', 'C', 'W', 'W', 'W'},
                    {'W', 'W', 'W', '-', 'W', 'W', 'B'},
                    {'-', 'W', 'W', 'W', 'W', 'W', 'W'},
                    {'W', 'D', 'W', '-', 'A', 'W', '-'},
                    {'W', 'c', 'd', 'W', 'W', 'W', 'W'},
                    {'-', '-', 'W', 'W', 'E', 'W', 'W'}},


            {{'A', 'E', 'a'},
                    {'W', 'W', '-'},
                    {'W', 'B', 'W'},
                    {'W', 'S', '-'},
                    {'-', '-', 'W'},
                    {'-', 'b', 'W'}},


            {{'B', '-', 'E', 'W', 'c', 'W', 'W'},
                    {'W', '-', 'W', 'W', '-', 'W', 'W'},
                    {'-', 'W', '-', '-', 'A', '-', 'W'},
                    {'W', 'W', 'W', '-', 'W', 'W', 'a'},
                    {'-', 'W', 'W', '-', 'W', 'S', 'W'},
                    {'W', 'W', '-', 'W', '-', '-', '-'},
                    {'-', 'W', 'b', 'C', '-', 'W', '-'}},


            {{'W', '-', 'W', 'B', '-', '-'},
                    {'S', 'E', 'b', 'c', 'C', 'A'},
                    {'a', '-', 'W', 'W', 'W', 'W'}},


            {{'W', 'W', 'W', '-', '-'},
                    {'-', '-', 'A', '-', 'W'},
                    {'S', '-', 'W', 'W', 'E'},
                    {'W', 'W', 'a', '-', 'W'}},


            {{'W', 'c', 'W', '-', 'C'},
                    {'S', '-', 'W', 'b', 'B'},
                    {'W', 'a', '-', 'E', 'A'}},


            {{'-', '-', 'W', 'W', 'W', 'a', 'W'},
                    {'W', 'W', 'W', '-', 'W', 'W', 'W'},
                    {'-', '-', '-', 'A', 'b', '-', 'W'},
                    {'-', 'W', '-', 'E', 'W', 'W', '-'},
                    {'W', '-', 'W', 'W', 'S', 'W', 'W'},
                    {'W', 'W', 'W', '-', '-', '-', '-'},
                    {'W', 'W', 'W', '-', '-', '-', 'B'}},


            {{'-', 'S'},
                    {'A', 'E'},
                    {'a', 'W'}},


            {{'W', 'S', 'a', '-', 'A', 'c'},
                    {'C', 'B', '-', '-', 'W', '-'},
                    {'-', 'E', 'W', 'W', 'W', 'b'}},


            {{'-', 'W', '-', '-', 'W', 'A'},
                    {'-', 'W', 'a', 'E', 'W', 'S'}},


            {{'W', 'B'},
                    {'E', 'b'},
                    {'-', '-'},
                    {'a', 'W'},
                    {'S', 'A'}},


            {{'W', '-'},
                    {'-', 'A'},
                    {'W', 'a'},
                    {'S', 'E'}},


            {{'W', 'W', 'A', '-', '-', 'W'},
                    {'-', 'W', 'W', 'S', 'W', 'a'},
                    {'-', 'E', 'b', 'W', 'W', '-'},
                    {'W', 'W', 'c', '-', 'C', 'B'}},


            {{'W', 'B', 'W', 'W', '-'},
                    {'W', 'S', 'W', '-', 'b'},
                    {'d', '-', 'W', 'W', 'a'},
                    {'-', 'W', '-', '-', 'c'},
                    {'E', '-', 'D', '-', 'C'},
                    {'A', '-', 'W', '-', 'W'}},


            {{'a', 'b', 'W', '-', '-'},
                    {'W', 'B', '-', '-', '-'},
                    {'W', '-', 'S', '-', 'W'},
                    {'W', 'W', 'W', 'c', 'E'},
                    {'-', 'C', '-', '-', '-'},
                    {'-', 'W', 'W', 'A', 'W'}},


            {{'W', 'a'},
                    {'A', 'E'},
                    {'W', 'S'},
                    {'B', 'b'}},


            {{'W', 'b', 'E', '-', 'W', 'W', '-'},
                    {'W', 'W', 'W', 'W', 'W', 'c', 'W'},
                    {'a', 'W', '-', 'B', 'W', '-', 'd'},
                    {'A', 'W', 'W', 'W', 'W', 'W', 'W'},
                    {'-', 'D', 'W', 'C', 'W', 'W', 'S'}},


            {{'W', 'W', '-', '-', '-'},
                    {'S', 'W', '-', 'W', 'W'},
                    {'-', 'W', 'A', 'W', '-'},
                    {'W', 'E', '-', 'W', 'W'},
                    {'W', 'W', 'a', 'W', '-'},
                    {'W', '-', 'W', 'W', 'W'}},

    };
    int[] results = {5, -1, 2, 1, -1,
            2, -1, 4, 1, 3,
            6, -1, -1, -1, 8,
            1, -1, 4, 7, 1,
            2, 3, 3, 1, 7,
            4, 3, 1, -1, -1};

}
