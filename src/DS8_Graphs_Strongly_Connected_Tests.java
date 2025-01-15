
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DS8_Graphs_Strongly_Connected_Tests {
    String[][] edges1 =
            {
                    {"ab","ac","ad","bc","bd","db"},
                    {"AD","BC","CE","DA","EB","EC","FE"},
                    {"AD","AC","BE","BF","CD","CS","DG","DB","EF","FB","GC","SA","SB"}
            };

    String[] vertices1 =
            {
                    "abcd",
                    "ABCDEF",
                    "ABCDEFGS"
            };

    String[][] strongly1 =
            {
                    {"bd"},
                    {"AD","EBC"},
                    {"BEF","ADGCS"}
            };

    String[][] edges2 = {
            {"HC", "BF", "EC", "HE", "CF", "BC", "EB", "AF", "DF", "FG", "BG", "AD"},
            {"GH", "GA", "BC", "BH", "DC", "AC", "GD", "DB", "FG", "FE"},
            {"AG", "FA", "HB", "AD", "CH", "BE", "FG", "DE", "BF", "CA"},
            {"AC", "DA"},
            {"DI", "FB", "BA", "GE", "AF", "HG", "CD", "BG", "GD", "IC", "AC", "AD", "EH", "AG"},
            {"CA", "EG", "FE", "CH", "IA", "HB", "BC", "DI", "DF", "GF", "AD", "AE", "IG"},
            {"IH", "EG", "AD", "CA", "EF", "GI", "DC", "CF", "AF", "BE", "DH", "FB", "HG"},
            {"JI", "DF", "JE", "HJ", "IA", "FJ", "EH", "FC", "FH", "BD", "CJ", "AG", "GI", "CD"},
            {"IH", "EI", "FI", "AF", "DE", "FC", "FG", "BJ", "CI", "HC", "GA", "EB", "BD", "GI"},
            {"FG", "CE", "HE", "DH", "GD", "FB", "EI", "HG", "HI", "FD", "IC", "AG", "AH"},
            {"HG", "CB", "HD", "BE", "FB", "BG", "FG", "FH", "AD", "DF", "IB", "GI"},
            {"FE", "HF", "EH", "EG", "AD", "DI", "CE", "GH", "FB", "BD", "AF", "IB"},
            {"EB", "FE", "FC", "BJ", "AH", "JD", "GD", "HD", "IG", "CB", "DA", "BF", "HJ", "FD"},
            {"HD", "CA", "CH", "GB", "IA", "EB", "HG", "BA", "ID", "FH", "GE", "GC", "AE"},
            {"GB", "CF", "FG", "BC", "EA", "CG"},
            {"AC", "FB", "BJ", "IB", "JE", "EC", "JF", "EA", "BA", "JD", "IG", "IA"},
            {"BC", "DB", "FE", "BA", "FD", "BF"},
            {"HI", "GD", "FC", "BG", "GF", "CE", "IG", "BC", "DE", "DI", "BH"},
            {"AD", "AC", "EA", "BC", "BE", "CE", "AF", "DE"},
            {"AG", "BE", "AC", "CB", "EC"},
            {"FC", "DF", "HG", "CB", "FG", "HD", "EF", "ED", "EG", "FH", "GA"},
            {"GA", "IE", "EA", "JH", "FG", "BF", "EH", "FI", "HI", "GE", "DE", "CJ", "HA"},
            {"EF", "EA", "ED", "FC", "CA", "BF", "FG", "CB", "EG", "DF"},
            {"AB", "FB", "DG", "BE", "AE", "ED", "BC", "CE", "DC"},
            {"CE", "DC", "AB", "EA", "AD", "BC"},
            {"DC", "FD", "FE", "EB", "AF", "CB", "AE", "BF"},
            {"EC", "CD", "ED", "GI", "DA", "AI", "HD", "EG", "FD", "GH", "AE"},
            {"DB", "DA", "HI", "HD", "GB", "EI", "GE", "HG", "BE", "GC", "AE", "BC", "BH"},
            {"BA", "GA", "FG", "DG", "AF", "BH", "CD"},
            {"FE", "ED", "CF", "CA", "AD", "DC", "FA", "EC", "BA"}
    };


    String[] vertices2 =
            {"HCBFEADG", "GHABCDFE", "AGFHBDCE", "ACD", "DIFBAGEHC", "CAEGFHIBD", "IHEGADCFB", "JIDFEHACBG", "IHEFADCGBJ", "FGCEHDBIA", "HGCBDEFAI", "FEHGADICB", "EBFCJAHDGI", "HDCAGBIEF", "GBCFEA", "ACFBJIEDG", "BCDFEA", "HIGDFCBE", "ADCEBF", "AGBEC", "FCDHGBEA", "GAIEJHFBDC", "EFADCBG", "ABFDGEC", "CEDAB", "DCFEBA", "ECDGIAHF", "DBAHIGEC", "BAGFDHC", "FEDCAB"};

    String[][] strongly2 = {
            null,
            null,
            null,
            null,
            {"DIC", "FBA", "GEH"},
            {"CHB", "AID", "EGF"},
            {"IHG", "EFB", "ADC"},
            {"JEH", "IAG", "DFC"},
            {"IHC", "EDB", "FAG"},
            {"GHD", "CEI"},
            {"HDF", "GBI"},
            {"FEHG", "DIB"},
            {"EBFC", "JAHD"},
            {"HCG", "ABE"},
            {"GBCF"},
            {"FBJ"},
            {"BDF"},
            {"IGD"},
            {"ADCE"},
            {"BEC"},
            {"FDH"},
            {"IEH"},
            {"FCB"},
            {"DEC"},
            {"CEDAB"},
            {"DCFEB"},
            {"ECDGAH"},
            {"DBHG"},
            {"AGF"},
            {"FEDCA"}
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
            Method strong = graphs.getMethod("stronglyConnectedRegions", String[].class,String.class);

            for (int x = 0; x < edges1.length; x++) {
                String[] stronglyStudent = (String[]) strong.invoke(graphs,edges1[x], vertices1[x]);

                if(strongly1[x]!=null)
                {
                    Assert.assertNotNull("Graph.stronglyConnectedRegions(" + Arrays.toString(edges1[x])
                            + "," + vertices1[x] + ") produced null when there were strongly connected regions", stronglyStudent);

                    Assert.assertEquals("Graph.stronglyConnectedRegions(" + Arrays.toString(edges1[x])
                            + "," + vertices1[x] + ") produced a solution of the wrong size.", strongly1[x].length, stronglyStudent.length);

                    for(String s: strongly1[x])
                    {
                        boolean r=answerContains(s, stronglyStudent);
                        Assert.assertTrue("Graph.stronglyConnectedRegions("+Arrays.toString(edges1[x])
                                +"," +vertices1[x]
                                +") failed to have this region: "+s,r);
                    }                }
                else {
                    Assert.assertNull("Graph.stronglyConnectedRegions(" + Arrays.toString(edges1[x])
                            + "," + vertices1[x] + ") produced found strongly connected regions when there should be none", stronglyStudent);
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
            Method strong = graphs.getMethod("stronglyConnectedRegions", String[].class,String.class);

            for (int x = 0; x < edges2.length; x++) {
                String[] stronglyStudent = (String[]) strong.invoke(graphs,edges2[x], vertices2[x]);

                if(strongly2[x]!=null)
                {
                    Assert.assertNotNull("Graph.stronglyConnectedRegions(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced null when there were strongly connected regions", stronglyStudent);

                    Assert.assertEquals("Graph.stronglyConnectedRegions(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced a solution of the wrong size.", strongly2[x].length, stronglyStudent.length);

                    for(String s: strongly2[x])
                    {
                        boolean r=answerContains(s, stronglyStudent);
                        Assert.assertTrue("Graph.stronglyConnectedRegions("+Arrays.toString(edges2[x])
                                +"," +vertices2[x]
                                +") failed to have this region: "+s,r);
                    }                }
                else {
                    Assert.assertNull("Graph.stronglyConnectedRegions(" + Arrays.toString(edges2[x])
                            + "," + vertices2[x] + ") produced found strongly connected regions when there should be none", stronglyStudent);
                }

            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public static boolean answerContains(String value, String[] list)
    {
        for(String s:list)
        {
            int count=0;
            if(s.length() ==value.length())
            {
                for(char c: value.toCharArray())
                {
                    if(s.contains(""+c))
                        count++;

                }
                if(count==value.length())
                    return true;
            }
        }
        return false;
    }
}
