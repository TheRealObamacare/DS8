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
import static org.junit.Assert.assertNull;

public class DS8_Dijkstras_Weighted_Tests
{
    String[][] testGraphs = {
            {"CA9", "BA8", "BD4", "AB9"},
            {"CE3", "CA3", "DG7", "DB4", "FC10", "BD4", "AF3", "CA11", "FE1", "GC7"},
            {"CB6", "BD7", "BD12", "DA1", "CD12", "AD8", "AB5", "DC12"},
            {"FD14", "HA10", "EG9", "HB5", "GC11", "FD4", "HF5", "GE10", "HG1", "DF6", "EA5", "HB11", "BF8", "DB9", "CG1", "AE7", "BG8", "HF13"},
            {"BD9", "DB12", "CA12", "BA13", "DC7", "DC9", "DA13", "AD12", "DC2", "DB14"},
            {"AB10", "AC5", "DC3", "ED2"},
            {"AD10", "DB14", "EB11", "BD6", "FC13", "DC7", "FB4", "CE8", "GD5", "AC1", "HA7", "AD6", "AB3", "CE14", "FA14", "BD12"},
            {"AC11", "DF13", "FA1", "CF12", "EA13", "DC7", "DB7", "FD5", "BF8", "DC13", "DB2", "AF2"},
            {"AC12", "BD11", "AB1", "DA11", "AD4", "CB6"},
            {"AC6", "CA14", "CA12", "AC9", "CB9", "CA10"},
            {"DE6", "EC9", "DE5", "AC2"},
            {"GB11", "FB10", "AC1", "GF3", "DE3", "HE3", "HB14", "HA7", "BH10", "GE13", "HC7", "EC2"},
            {"GE13", "CA8", "GA8", "GE8", "GA2", "BC12", "AF11", "DF8", "CF12", "DE11", "FA10", "AC7"},
            {"CB12", "BE9", "CA7", "DC13", "DB12", "EA9", "DE2", "AC5", "DB7", "CD11", "CA4", "CE5"},
            {"GC6", "CH2", "EF13", "CH2"},
            {"AD3", "BE14", "ED1", "AB7"},
            {"DC13", "AC12", "EC14", "CD9"},
            {"BC2", "AD5", "AC9", "CA4", "AC13", "DC12"},
            {"DB13", "DC11", "BE3", "CA11", "CB13", "BD2"},
            {"AB1", "AE1", "CA5", "AD6", "DE3", "AC10"},
            {"EC2", "BA9", "DC1", "CA8", "DC12", "DC11", "DA13", "CD10", "AC1", "BA11"},
            {"DB9", "EB10", "CB3", "BD9", "CF1", "BA10"},
            {"CE8", "CD10", "CA14", "BE7", "BA9", "CD12"},
            {"BE2", "AF8", "FB6", "CG1"},
            {"CA9", "EC13", "FD4", "AB13"},
            {"AB4", "CB5", "BE13", "EB13"},
            {"CD4", "AG9", "CD7", "BE5", "EB8", "GE1", "DF9", "DG12"},
            {"CB1", "CE13", "FA9", "EF8", "CE4", "EA7", "DE7", "ED2", "CA5", "DF11", "BA9", "CB5"},
            {"AE8", "EA5", "FE8", "FC5", "EA1", "AE1", "AC5", "BF14"},
            {"CD9", "AD14", "BA6", "AC12", "AD9", "DA11"}
    };
    String[] testVertices =
            {"ABCD", "ABCDEFG", "ABCD", "ABCDEFGH", "ABCD", "ABCDE", "ABCDEFGH", "ABCDEF", "ABCD", "ABCDE", "ABCDE", "ABCDEFGH", "ABCDEFG", "ABCDE", "ABCDEFGH", "ABCDEF", "ABCDE", "ABCD", "ABCDE", "ABCDE", "ABCDE", "ABCDEF", "ABCDE", "ABCDEFGH", "ABCDEF", "ABCDEF", "ABCDEFG", "ABCDEF", "ABCDEF", "ABCD"};

    String[][] testSolutions = {
            {"AB9", "AC-1", "AD13", "BA8", "BC-1", "BD4", "CA9", "CB18", "CD22", "DA-1", "DB-1", "DC-1"},
            {"AB-1", "AC13", "AD-1", "AE4", "AF3", "AG-1", "BA21", "BC18", "BD4", "BE21", "BF24", "BG11", "CA3", "CB-1", "CD-1", "CE3", "CF6", "CG-1", "DA17", "DB4", "DC14", "DE17", "DF20", "DG7", "EA-1", "EB-1", "EC-1", "ED-1", "EF-1", "EG-1", "FA13", "FB-1", "FC10", "FD-1", "FE1", "FG-1", "GA10", "GB-1", "GC7", "GD-1", "GE10", "GF13"},
            {"AB5", "AC20", "AD8", "BA8", "BC19", "BD7", "CA13", "CB6", "CD12", "DA1", "DB6", "DC12"},
            {"AB-1", "AC27", "AD-1", "AE7", "AF-1", "AG16", "AH-1", "BA23", "BC19", "BD12", "BE18", "BF8", "BG8", "BH-1", "CA16", "CB-1", "CD-1", "CE11", "CF-1", "CG1", "CH-1", "DA32", "DB9", "DC28", "DE27", "DF6", "DG17", "DH-1", "EA5", "EB-1", "EC20", "ED-1", "EF-1", "EG9", "EH-1", "FA36", "FB13", "FC32", "FD4", "FE31", "FG21", "FH-1", "GA15", "GB-1", "GC11", "GD-1", "GE10", "GF-1", "GH-1", "HA10", "HB5", "HC12", "HD9", "HE11", "HF5", "HG1"},
            {"AB24", "AC14", "AD12", "BA13", "BC11", "BD9", "CA12", "CB36", "CD24", "DA13", "DB12", "DC2"},
            {"AB10", "AC5", "AD-1", "AE-1", "BA-1", "BC-1", "BD-1", "BE-1", "CA-1", "CB-1", "CD-1", "CE-1", "DA-1", "DB-1", "DC3", "DE-1", "EA-1", "EB-1", "EC5", "ED2"},
            {"AB3", "AC1", "AD6", "AE9", "AF-1", "AG-1", "AH-1", "BA-1", "BC13", "BD6", "BE21", "BF-1", "BG-1", "BH-1", "CA-1", "CB19", "CD25", "CE8", "CF-1", "CG-1", "CH-1", "DA-1", "DB14", "DC7", "DE15", "DF-1", "DG-1", "DH-1", "EA-1", "EB11", "EC24", "ED17", "EF-1", "EG-1", "EH-1", "FA14", "FB4", "FC13", "FD10", "FE21", "FG-1", "FH-1", "GA-1", "GB19", "GC12", "GD5", "GE20", "GF-1", "GH-1", "HA7", "HB10", "HC8", "HD13", "HE16", "HF-1", "HG-1"},
            {"AB9", "AC11", "AD7", "AE-1", "AF2", "BA9", "BC20", "BD13", "BE-1", "BF8", "CA13", "CB19", "CD17", "CE-1", "CF12", "DA11", "DB2", "DC7", "DE-1", "DF10", "EA13", "EB22", "EC24", "ED20", "EF15", "FA1", "FB7", "FC12", "FD5", "FE-1"},
            {"AB1", "AC12", "AD4", "BA22", "BC34", "BD11", "CA28", "CB6", "CD17", "DA11", "DB12", "DC23"},
            {"AB15", "AC6", "AD-1", "AE-1", "BA-1", "BC-1", "BD-1", "BE-1", "CA10", "CB9", "CD-1", "CE-1", "DA-1", "DB-1", "DC-1", "DE-1", "EA-1", "EB-1", "EC-1", "ED-1"},
            {"AB-1", "AC2", "AD-1", "AE-1", "BA-1", "BC-1", "BD-1", "BE-1", "CA-1", "CB-1", "CD-1", "CE-1", "DA-1", "DB-1", "DC14", "DE5", "EA-1", "EB-1", "EC9", "ED-1"},
            {"AB-1", "AC1", "AD-1", "AE-1", "AF-1", "AG-1", "AH-1", "BA17", "BC15", "BD-1", "BE13", "BF-1", "BG-1", "BH10", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "CG-1", "CH-1", "DA-1", "DB-1", "DC5", "DE3", "DF-1", "DG-1", "DH-1", "EA-1", "EB-1", "EC2", "ED-1", "EF-1", "EG-1", "EH-1", "FA27", "FB10", "FC25", "FD-1", "FE23", "FG-1", "FH20", "GA28", "GB11", "GC15", "GD-1", "GE13", "GF3", "GH21", "HA7", "HB14", "HC5", "HD-1", "HE3", "HF-1", "HG-1"},
            {"AB-1", "AC7", "AD-1", "AE-1", "AF11", "AG-1", "BA20", "BC12", "BD-1", "BE-1", "BF24", "BG-1", "CA8", "CB-1", "CD-1", "CE-1", "CF12", "CG-1", "DA18", "DB-1", "DC25", "DE11", "DF8", "DG-1", "EA-1", "EB-1", "EC-1", "ED-1", "EF-1", "EG-1", "FA10", "FB-1", "FC17", "FD-1", "FE-1", "FG-1", "GA2", "GB-1", "GC9", "GD-1", "GE8", "GF13"},
            {"AB17", "AC5", "AD16", "AE10", "BA18", "BC23", "BD34", "BE9", "CA4", "CB12", "CD11", "CE5", "DA11", "DB7", "DC13", "DE2", "EA9", "EB26", "EC14", "ED25"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "AG-1", "AH-1", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "BH-1", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "CG-1", "CH2", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "DG-1", "DH-1", "EA-1", "EB-1", "EC-1", "ED-1", "EF13", "EG-1", "EH-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "FH-1", "GA-1", "GB-1", "GC6", "GD-1", "GE-1", "GF-1", "GH8", "HA-1", "HB-1", "HC-1", "HD-1", "HE-1", "HF-1", "HG-1"},
            {"AB7", "AC-1", "AD3", "AE21", "AF-1", "BA-1", "BC-1", "BD15", "BE14", "BF-1", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "EA-1", "EB-1", "EC-1", "ED1", "EF-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB-1", "AC12", "AD21", "AE-1", "BA-1", "BC-1", "BD-1", "BE-1", "CA-1", "CB-1", "CD9", "CE-1", "DA-1", "DB-1", "DC13", "DE-1", "EA-1", "EB-1", "EC14", "ED23"},
            {"AB-1", "AC9", "AD5", "BA6", "BC2", "BD11", "CA4", "CB-1", "CD9", "DA16", "DB-1", "DC12"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "BA24", "BC13", "BD2", "BE3", "CA11", "CB13", "CD15", "CE16", "DA22", "DB13", "DC11", "DE16", "EA-1", "EB-1", "EC-1", "ED-1"},
            {"AB1", "AC10", "AD6", "AE1", "BA-1", "BC-1", "BD-1", "BE-1", "CA5", "CB6", "CD11", "CE6", "DA-1", "DB-1", "DC-1", "DE3", "EA-1", "EB-1", "EC-1", "ED-1"},
            {"AB-1", "AC1", "AD11", "AE-1", "BA9", "BC10", "BD20", "BE-1", "CA8", "CB-1", "CD10", "CE-1", "DA9", "DB-1", "DC1", "DE-1", "EA10", "EB-1", "EC2", "ED12"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "BA10", "BC-1", "BD9", "BE-1", "BF-1", "CA13", "CB3", "CD12", "CE-1", "CF1", "DA19", "DB9", "DC-1", "DE-1", "DF-1", "EA20", "EB10", "EC-1", "ED19", "EF-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "BA9", "BC-1", "BD-1", "BE7", "CA14", "CB-1", "CD10", "CE8", "DA-1", "DB-1", "DC-1", "DE-1", "EA-1", "EB-1", "EC-1", "ED-1"},
            {"AB14", "AC-1", "AD-1", "AE16", "AF8", "AG-1", "AH-1", "BA-1", "BC-1", "BD-1", "BE2", "BF-1", "BG-1", "BH-1", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "CG1", "CH-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "DG-1", "DH-1", "EA-1", "EB-1", "EC-1", "ED-1", "EF-1", "EG-1", "EH-1", "FA-1", "FB6", "FC-1", "FD-1", "FE8", "FG-1", "FH-1", "GA-1", "GB-1", "GC-1", "GD-1", "GE-1", "GF-1", "GH-1", "HA-1", "HB-1", "HC-1", "HD-1", "HE-1", "HF-1", "HG-1"},
            {"AB13", "AC-1", "AD-1", "AE-1", "AF-1", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "CA9", "CB22", "CD-1", "CE-1", "CF-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "EA22", "EB35", "EC13", "ED-1", "EF-1", "FA-1", "FB-1", "FC-1", "FD4", "FE-1"},
            {"AB4", "AC-1", "AD-1", "AE17", "AF-1", "BA-1", "BC-1", "BD-1", "BE13", "BF-1", "CA-1", "CB5", "CD-1", "CE18", "CF-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "EA-1", "EB13", "EC-1", "ED-1", "EF-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB18", "AC-1", "AD-1", "AE10", "AF-1", "AG9", "BA-1", "BC-1", "BD-1", "BE5", "BF-1", "BG-1", "CA-1", "CB25", "CD4", "CE17", "CF13", "CG16", "DA-1", "DB21", "DC-1", "DE13", "DF9", "DG12", "EA-1", "EB8", "EC-1", "ED-1", "EF-1", "EG-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "GA-1", "GB9", "GC-1", "GD-1", "GE1", "GF-1"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "BA9", "BC-1", "BD-1", "BE-1", "BF-1", "CA5", "CB1", "CD6", "CE4", "CF12", "DA14", "DB-1", "DC-1", "DE7", "DF11", "EA7", "EB-1", "EC-1", "ED2", "EF8", "FA9", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB-1", "AC5", "AD-1", "AE1", "AF-1", "BA23", "BC19", "BD-1", "BE22", "BF14", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "EA1", "EB-1", "EC6", "ED-1", "EF-1", "FA9", "FB-1", "FC5", "FD-1", "FE8"},
            {"AB-1", "AC12", "AD9", "BA6", "BC18", "BD15", "CA20", "CB-1", "CD9", "DA11", "DB-1", "DC23"}
    };

    String[][] staticGraphs = {
            {"ab3", "ae5", "bc8", "bd5", "be3", "dc2", "ed4"},
            {"ab1", "ac2", "ad1", "bc1", "bd2", "db2"},
            {"AD3", "BC6", "CE7", "DA1", "EB5", "EC4", "FE10"}
    };
    String[] staticVertices =
            {"abcde", "abcd", "ABCDEF"};
    String[][] staticSolutions = {
            {"ab3", "ac10", "ad8", "ae5", "ba-1", "bc7", "bd5", "be3", "ca-1", "cb-1", "cd-1", "ce-1", "da-1", "db-1", "dc2", "de-1", "ea-1", "eb-1", "ec6", "ed4"},
            {"ab1", "ac2", "ad1", "ba-1", "bc1", "bd2", "ca-1", "cb-1", "cd-1", "da-1", "db2", "dc3"},
            {"AB-1", "AC-1", "AD3", "AE-1", "AF-1", "BA-1", "BC6", "BD-1", "BE13", "BF-1", "CA-1", "CB12", "CD-1", "CE7", "CF-1", "DA1", "DB-1", "DC-1", "DE-1", "DF-1", "EA-1", "EB5", "EC4", "ED-1", "EF-1", "FA-1", "FB15", "FC14", "FD-1", "FE10"}
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
        allowedImports.add("java.awt.Arrays");
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
            Method weighted = dijkstras.getMethod("dijkstras_Weighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<staticGraphs.length; g++)
                for(int t=0; t<staticSolutions[g].length; t++) {

                        assertEquals("dijkstras_Weighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed to produce the correct result.",Integer.parseInt(staticSolutions[g][t].substring(2)),
                                weighted.invoke(dijkstras,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
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
            Method weighted = dijkstras.getMethod("dijkstras_Weighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<testGraphs.length; g++)
                for(int t=0; t<testSolutions[g].length; t++) {
                    assertEquals("dijkstras_Weighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed to produce the correct result.",Integer.parseInt(testSolutions[g][t].substring(2)),
                            weighted.invoke(dijkstras,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
