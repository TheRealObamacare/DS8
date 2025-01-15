
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DS8_Graphs_Bridges_Tests {
    String[][] edges1 =
            {
                    {"AB","BC","BD","DC"},
                    {"AD","AC","BC","BD", "BE","EF","EG","EH","FG","FH"},
                    {"AB","AC","AE","BD","BE","DE","CF","CG"}
            };
    String[] vertices1 =
            {
                    "ABCD",
                    "ABCDEFGH",
                    "ABCDEFG"
            };

    String [][] bridges1 =
            {
                    {"AB"},
                    {"BE"},
                    {"AC","CF","CG"}
            };

    String[][] edges2 = {
            {"CF", "AG", "BD"},
            {"BC", "BD", "AD"},
            {"BA", "GC"},
            {"EA", "GD", "BC", "FD", "GH", "EH"},
            {"AC", "DA"},
            {"DC", "DA", "CB", "CA", "BD"},
            {"AD", "DC", "BC", "BD", "AB"},
            {"AB", "CD"},
            {"CA", "AB", "EF", "AD", "BE"},
            {"GF", "BG", "HC", "FH", "FA"},
            {"DC", "DB", "BA"},
            {"BG", "FA", "HC", "HB", "EG", "DA"},
            {"BC", "DA", "AB", "AC"},
            {"EB", "AB", "CA", "CH"},
            {"BA", "DB", "CA", "DA"},
            {"AD", "BA", "CB", "CA"},
            {"DE", "EC", "EA", "AC", "BA"},
            {"AD", "CB"},
            {"FG", "EG", "FD", "EC", "GD", "AD", "BC"},
            {"DB", "GC", "FD", "FC", "CE"},
            {"FB", "AF", "EC", "EA"},
            {"DG", "AE"},
            {"EC", "BE"},
            {"CA", "DC"},
            {"BD", "BE", "DA", "DC"},
            {"CA", "AF", "DA", "BE", "EC", "BA", "DF"},
            {"DE", "CE", "DC", "AB", "CB", "EB"},
            {"BD", "CD", "DA", "AC", "BC"},
            {"AB", "DB"},
            {"HD", "GC", "BG", "DG", "EC"}
    };


    String[] vertices2 =
            {"CFAGBD", "BCDA", "BAGC", "EAGDBCFH", "ACD", "DCAB", "ADCB", "ABCD", "CABEFD", "GFBHCA", "DCBA", "BGFAHCED", "BCDA", "EBACH", "BADC", "ADBC", "DECAB", "ADCB", "FGEDCAB", "DBGCFE", "FBAEC", "DGAE", "ECB", "CAD", "BDEAC", "CAFDBE", "DECAB", "BDCA", "ABD", "HDGCBE"};

    String[][] bridges2 = {
            {"CF", "AG", "BD"},
            {"BC", "BD", "AD"},
            {"BA", "GC"},
            {"EA", "GD", "BC", "FD", "GH", "EH"},
            {"AC", "DA"},
            null,
            null,
            {"AB", "CD"},
            {"CA", "AB", "EF", "AD", "BE"},
            {"GF", "BG", "HC", "FH", "FA"},
            {"DC", "DB", "BA"},
            {"BG", "FA", "HC", "HB", "EG", "DA"},
            {"DA"},
            {"EB", "AB", "CA", "CH"},
            {"CA"},
            {"AD"},
            {"DE", "BA"},
            {"AD", "CB"},
            {"EG", "EC", "AD", "BC"},
            {"DB", "GC", "FD", "FC", "CE"},
            {"FB", "AF", "EC", "EA"},
            {"DG", "AE"},
            {"EC", "BE"},
            {"CA", "DC"},
            {"BD", "BE", "DA", "DC"},
            null,
            {"AB"},
            null,
            {"AB", "DB"},
            {"HD", "GC", "BG", "DG", "EC"}
    };

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
        String className = "DS8_Graphs";
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

    @Test(timeout = 1000)
    public void test1() throws Exception{
        try
        {
            Class<?> graphs = Class.forName(generateClassName("DS8_Graphs"));
            Method bridges = graphs.getMethod("bridges", String[].class,String.class);

            for (int x = 0; x < edges1.length; x++) {
                ArrayList<String> bridgesStudent = (ArrayList<String>) bridges.invoke(graphs,edges1[x], vertices1[x]);

                if(bridges1[x]!=null)
                {
                    Assert.assertNotNull("Graph.bridges(" + Arrays.toString(edges1[x])
                            + "," + vertices1[x] + ") produced null when there were bridges", bridgesStudent);

                    Assert.assertEquals("Graph.bridges(" + Arrays.toString(edges1[x])
                        + "," + vertices1[x] + ") produced a solution of the wrong size.", bridges1[x].length, bridgesStudent.size());


                    for (String e : bridges1[x])
                        Assert.assertTrue("Graph.bridges(" + Arrays.toString(edges1[x])
                                + "," + vertices1[x] + ") did not include bridge - " + e, bridgesStudent.contains(e));
                }
                else {
                    Assert.assertNull("Graph.bridges(" + Arrays.toString(edges1[x])
                            + "," + vertices1[x] + ") produced found bridges when there should be none", bridgesStudent);
                }

            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 1000)
    public void test2() throws Exception{
        try
        {
            Class<?> graphs = Class.forName(generateClassName("DS8_Graphs"));
            Method bridges = graphs.getMethod("bridges", String[].class,String.class);

            for (int x = 0; x < edges2.length; x++) {
                ArrayList<String> bridgesStudent = (ArrayList<String>) bridges.invoke(graphs,edges2[x], vertices2[x]);
                System.out.println("aaa"+bridgesStudent);
                if(bridges2[x]!=null)
                {
                    Assert.assertNotNull("Graph.bridges(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced null when there were bridges", bridgesStudent);

                    Assert.assertEquals("Graph.bridges(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced a solution of the wrong size.", bridges2[x].length, bridgesStudent.size());


                    for (String e : bridges2[x])
                        Assert.assertTrue("Graph.bridges(" + Arrays.toString(edges2[x])
                                + "," + vertices2[x] + ") did not include bridge - " + e, bridgesStudent.contains(e));
                }
                else {
                    Assert.assertNull("Graph.bridges(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced found bridges when there should be none", bridgesStudent);
                }

            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

}
