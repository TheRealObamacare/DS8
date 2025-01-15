
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

public class DS8_DFS_Simple_Tests
{
    char[][][] staticGrids =
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

                    {{'S','-','W','-','-','-'},
                            {'W','W','E','-','-','W'},
                            {'W','-','W','-','-','W'},
                            {'W','-','-','-','-','-'},
                            {'W','-','-','W','-','W'},
                            {'W','-','W','-','-','W'}}
            };

    boolean[] staticResults = {true,true,true,true,false};

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
        String className = "DS8_DFS";
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
            Class<?> dfs = Class.forName(generateClassName("DS8_DFS"));
            Method simple = dfs.getMethod("depthFirstSearch_Simple", char[][].class);

            for(int g=0;g<staticGrids.length; g++)
                assertEquals("depthFirstSearch_Simple("+ Arrays.deepToString(staticGrids[g])+") failed to produce the correct result."
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
            Class<?> dfs = Class.forName(generateClassName("DS8_DFS"));
            Method simple = dfs.getMethod("depthFirstSearch_Simple", char[][].class);

            for(int g=0;g<tests.length; g++)
                assertEquals("depthFirstSearch_Simple("+ Arrays.deepToString(tests[g])+") failed to produce the correct result."
                        ,results[g],simple.invoke(dfs,(Object)tests[g]));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
    
    char[][][] tests = {

            {{'W', '-', 'W', '-', 'W',  'W'},
                    {'-', 'W', 'W', '-', 'W',  'W'},
                    {'-', 'W', 'W', 'W', '-',  'S'},
                    {'-', 'W', '-', 'W', 'W',  'W'},
                    {'W', 'W', 'W', '-', 'W',  'W'},
                    {'W', 'W', '-', '-', 'W',  'W'},
                    {'W', 'W', 'W', 'E', 'W',  '-'}},


            {{'-', 'W', 'W', '-', '-',  '-'},
                    {'S', '-', '-', '-', '-',  '-'},
                    {'-', 'W', '-', 'W', '-',  'E'}},


            {{'-', '-', 'W', '-', '-', 'W',  '-'},
                    {'-', '-', '-', '-', 'S', '-',  '-'},
                    {'-', '-', '-', '-', 'E', '-',  '-'}},


            {{'-', 'E', 'S', '-', '-',  'W'},
                    {'-', 'W', '-', '-', 'W',  '-'}},


            {{'-', '-',  '-'},
                    {'-', 'E',  'W'},
                    {'S', 'W',  '-'},
                    {'W', 'W',  '-'},
                    {'-', '-',  '-'},
                    {'-', '-',  '-'}},


            {{'W', 'S', 'E', '-',  'W'},
                    {'W', '-', '-', '-',  'W'},
                    {'W', 'W', 'W', 'W',  '-'}},


            {{'W', 'S', 'E', '-',  'W'},
                    {'W', 'W', 'W', 'W',  'W'}},


            {{'W', '-', 'S',  'W'},
                    {'E', 'W', '-',  'W'}},


            {{'S', 'W',  'W'},
                    {'E', '-',  'W'}},


            {{'-', 'E',  'W'},
                    {'W', 'S',  'W'}},


            {{'-', 'E', 'W',  'W'},
                    {'W', 'W', 'S',  'W'},
                    {'W', 'W', 'W',  'W'}},


            {{'-', '-', 'W', '-',  '-'},
                    {'W', 'W', '-', 'W',  '-'},
                    {'W', 'W', 'W', 'W',  '-'},
                    {'-', '-', 'W', 'W',  'W'},
                    {'-', 'S', '-', 'W',  'E'}},


            {{'W', 'W', 'W', 'W',  '-'},
                    {'W', 'W', 'W', 'W',  'W'},
                    {'W', 'W', 'W', 'W',  'W'},
                    {'W', 'E', 'S', '-',  'W'},
                    {'-', '-', '-', 'W',  '-'},
                    {'W', 'W', 'W', 'W',  'W'},
                    {'W', 'W', 'W', 'W',  '-'}},


            {{'-', '-', '-', '-', '-', '-',  '-'},
                    {'-', 'S', '-', '-', '-', '-',  '-'},
                    {'-', '-', '-', '-', '-', '-',  '-'},
                    {'-', '-', '-', 'E', '-', '-',  '-'},
                    {'-', '-', '-', '-', '-', '-',  '-'}},


            {{'-', 'W', 'W',  'W'},
                    {'W', 'W', 'W',  '-'},
                    {'W', '-', 'E',  'W'},
                    {'W', '-', 'W',  'W'},
                    {'-', 'W', '-',  'W'},
                    {'W', '-', 'S',  'W'},
                    {'W', '-', 'W',  '-'}},


            {{'W', 'W', 'W',  '-'},
                    {'W', 'W', 'W',  'W'},
                    {'S', 'W', 'W',  '-'},
                    {'W', '-', 'W',  'E'}},


            {{'W', '-', 'E', '-',  '-'},
                    {'-', '-', '-', '-',  '-'},
                    {'-', '-', '-', 'W',  'S'}},


            {{'S',  '-'},
                    {'-',  '-'},
                    {'E',  '-'},
                    {'-',  '-'},
                    {'W',  '-'}},


            {{'-', 'W', '-', '-', '-',  '-'},
                    {'-', '-', '-', '-', 'W',  'E'},
                    {'-', '-', '-', 'S', '-',  '-'}},


            {{'-', 'W', '-',  'W'},
                    {'W', 'W', 'E',  '-'},
                    {'W', '-', 'W',  '-'},
                    {'W', 'S', '-',  'W'},
                    {'-', 'W', '-',  '-'},
                    {'-', 'W', '-',  'W'},
                    {'W', '-', 'W',  'W'}},


            {{'-', 'W', 'W', 'W', 'W',  'W'},
                    {'W', 'W', '-', 'W', 'W',  '-'},
                    {'W', 'W', '-', '-', 'W',  'W'},
                    {'-', 'W', 'W', 'W', 'W',  'S'},
                    {'-', 'E', 'W', 'W', '-',  'W'},
                    {'W', 'W', 'W', 'W', 'W',  'W'},
                    {'W', 'W', 'W', 'W', '-',  'W'}},


            {{'-',  'W'},
                    {'-',  'W'},
                    {'S',  'W'},
                    {'-',  '-'},
                    {'-',  'W'},
                    {'W',  'E'}},


            {{'-', '-', '-', '-', '-',  '-'},
                    {'-', 'S', 'W', '-', 'W',  'W'},
                    {'W', '-', '-', '-', '-',  'W'},
                    {'W', '-', 'E', '-', 'W',  'W'},
                    {'W', 'W', '-', 'W', '-',  '-'},
                    {'-', 'W', '-', '-', '-',  '-'}},


            {{'E',  '-'},
                    {'-',  'S'},
                    {'-',  '-'},
                    {'W',  '-'},
                    {'W',  'W'}},


            {{'W', 'W', 'E', 'W',  'W'},
                    {'W', '-', 'S', 'W',  '-'},
                    {'W', 'W', 'W', '-',  'W'},
                    {'-', '-', '-', 'W',  'W'}},


            {{'W',  'E'},
                    {'-',  'S'},
                    {'W',  '-'}},


            {{'E',  'W'},
                    {'-',  'W'},
                    {'W',  'S'},
                    {'W',  'W'},
                    {'W',  'W'}},


            {{'-', '-', '-',  'W'},
                    {'-', '-', '-',  'W'},
                    {'W', 'W', '-',  'W'},
                    {'-', '-', '-',  '-'},
                    {'W', 'W', 'S',  'W'},
                    {'-', 'E', 'W',  '-'}},


            {{'-', 'E', '-', '-',  'W'},
                    {'-', 'W', 'W', '-',  '-'},
                    {'-', 'W', '-', 'W',  'W'},
                    {'-', '-', 'S', '-',  '-'}},


            {{'W',  'W'},
                    {'E',  'W'},
                    {'W',  'W'},
                    {'W',  '-'},
                    {'-',  '-'},
                    {'-',  'S'}}

    };
    boolean[] results = {false, true, true, true, true, true, true, false, true, true, false, false, true, true, false, false, true, true, true, false, false, false, true, true, true, true, false, false, true, false};

}
