package SolutionsAndTests.CS_DS.u8.ChallengeSet2;

import SolutionsAndTests.CS_DS.u8.ChallengeSet4.DS8_Path_Solution;
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
import static org.junit.Assert.assertNull;

public class DS8_BFS_Unweighted_Tests {
    String[][] testGraphs = {
            {"AB", "CH", "GB", "FA"},
            {"FA", "CF", "DC", "BD"},
            {"BD", "CB", "DA", "AE", "EC", "FE", "AC", "FA", "CD", "CF"},
            {"DH", "AE", "DA", "HA", "FD", "HE"},
            {"DH", "AG", "BF", "AF", "CA", "HB"},
            {"FC", "AB", "BC", "EA", "CD", "EB", "FE", "DF"},
            {"BA", "DA", "BD", "CA", "CD", "ED", "CB"},
            {"AD", "CD", "CB"},
            {"AB", "AD", "DC", "BE", "ED", "AE", "BC"},
            {"DA", "DB", "BC", "CD", "BA"},
            {"DB", "CB", "BA", "DA", "CD", "AC"},
            {"DF", "FA", "EB", "AE", "EC", "FC", "AD", "CB", "AB", "CA"},
            {"CD", "EB", "CA", "BA", "BC", "ED"},
            {"DB", "GF", "AC", "CB", "AB", "FB", "FC", "GC"},
            {"FA", "AB", "GF", "CB"},
            {"CD", "BC", "CA"},
            {"BD", "AC", "ED", "EC", "CD"},
            {"DE", "DC", "AE", "FA", "FB", "AB", "FC"},
            {"BG", "GF", "GA", "FB", "BE", "CA", "EA", "GD", "BA", "FE", "FD", "EC"},
            {"DC", "BD", "AB", "AC", "BC"},
            {"FC", "CB", "BF"},
            {"CE", "EB", "GA", "CB", "EA", "HA"},
            {"EG", "DA", "BG", "CB", "BA", "CG", "DG", "FB", "GA", "CA"},
            {"AF", "DC", "ED", "GE", "AE", "AD", "GB", "EC", "AG", "CG"},
            {"BA", "DA", "CD", "BD", "AF"},
            {"AE", "AC", "CD", "DB", "CE", "ED"},
            {"AB", "CA", "DC"},
            {"FD", "CB", "AF", "BD", "EC", "FE"},
            {"ED", "BA", "AD", "DC"},
            {"FC", "EF", "DC", "CH", "BA", "EA", "DA", "DH", "AG", "DF", "BD", "BH"}
    };
    String[] testVertices =
            {"ABCDEFGH", "ABCDEF", "ABCDEF", "ABCDEFGH", "ABCDEFGH", "ABCDEF", "ABCDE", "ABCD", "ABCDE", "ABCD", "ABCD", "ABCDEF", "ABCDE", "ABCDEFG", "ABCDEFG", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG", "ABCD", "ABCDEF", "ABCDEFGH", "ABCDEFG", "ABCDEFG", "ABCDEF", "ABCDE", "ABCD", "ABCDEF", "ABCDE", "ABCDEFGH"};

    String[][] testSolutions = {
            {"ABAB", "ACnull", "ADnull", "AEnull", "AFAF", "AGABG", "AHnull", "BABA", "BCnull", "BDnull", "BEnull", "BFBAF", "BGBG", "BHnull", "CAnull", "CBnull", "CDnull", "CEnull", "CFnull", "CGnull", "CHCH", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "DHnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "EHnull", "FAFA", "FBFAB", "FCnull", "FDnull", "FEnull", "FGFABG", "FHnull", "GAGBA", "GBGB", "GCnull", "GDnull", "GEnull", "GFGBAF", "GHnull", "HAnull", "HBnull", "HCHC", "HDnull", "HEnull", "HFnull", "HGnull"},
            {"ABAFCDB", "ACAFC", "ADAFCD", "AEnull", "AFAF", "BABDCFA", "BCBDC", "BDBD", "BEnull", "BFBDCF", "CACFA", "CBCDB", "CDCD", "CEnull", "CFCF", "DADCFA", "DBDB", "DCDC", "DEnull", "DFDCF", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "FAFA", "FBFCDB", "FCFC", "FDFCD", "FEnull"},
            {"ABADB", "ACAC", "ADAD", "AEAE", "AFAF", "BABDA", "BCBC", "BDBD", "BEBCE", "BFBCF", "CACA", "CBCB", "CDCD", "CECE", "CFCF", "DADA", "DBDB", "DCDC", "DEDAE", "DFDAF", "EAEA", "EBECB", "ECEC", "EDEAD", "EFEF", "FAFA", "FBFCB", "FCFC", "FDFAD", "FEFE"},
            {"ABnull", "ACnull", "ADAD", "AEAE", "AFADF", "AGnull", "AHAH", "BAnull", "BCnull", "BDnull", "BEnull", "BFnull", "BGnull", "BHnull", "CAnull", "CBnull", "CDnull", "CEnull", "CFnull", "CGnull", "CHnull", "DADA", "DBnull", "DCnull", "DEDHE", "DFDF", "DGnull", "DHDH", "EAEA", "EBnull", "ECnull", "EDEAD", "EFEADF", "EGnull", "EHEH", "FAFDA", "FBnull", "FCnull", "FDFD", "FEFDHE", "FGnull", "FHFDH", "GAnull", "GBnull", "GCnull", "GDnull", "GEnull", "GFnull", "GHnull", "HAHA", "HBnull", "HCnull", "HDHD", "HEHE", "HFHDF", "HGnull"},
            {"ABAFB", "ACAC", "ADAFBHD", "AEnull", "AFAF", "AGAG", "AHAFBH", "BABFA", "BCBFAC", "BDBHD", "BEnull", "BFBF", "BGBFAG", "BHBH", "CACA", "CBCAFB", "CDCAFBHD", "CEnull", "CFCAF", "CGCAG", "CHCAFBH", "DADHBFA", "DBDHB", "DCDHBFAC", "DEnull", "DFDHBF", "DGDHBFAG", "DHDH", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "EHnull", "FAFA", "FBFB", "FCFAC", "FDFBHD", "FEnull", "FGFAG", "FHFBH", "GAGA", "GBGAFB", "GCGAC", "GDGAFBHD", "GEnull", "GFGAF", "GHGAFBH", "HAHBFA", "HBHB", "HCHBFAC", "HDHD", "HEnull", "HFHBF", "HGHBFAG"},
            {"ABAB", "ACABC", "ADABCD", "AEAE", "AFAEF", "BABA", "BCBC", "BDBCD", "BEBE", "BFBCF", "CACBA", "CBCB", "CDCD", "CECFE", "CFCF", "DADCBA", "DBDCB", "DCDC", "DEDFE", "DFDF", "EAEA", "EBEB", "ECEBC", "EDEFD", "EFEF", "FAFEA", "FBFCB", "FCFC", "FDFD", "FEFE"},
            {"ABAB", "ACAC", "ADAD", "AEADE", "BABA", "BCBC", "BDBD", "BEBDE", "CACA", "CBCB", "CDCD", "CECDE", "DADA", "DBDB", "DCDC", "DEDE", "EAEDA", "EBEDB", "ECEDC", "EDED"},
            {"ABADCB", "ACADC", "ADAD", "BABCDA", "BCBC", "BDBCD", "CACDA", "CBCB", "CDCD", "DADA", "DBDCB", "DCDC"},
            {"ABAB", "ACABC", "ADAD", "AEAE", "BABA", "BCBC", "BDBAD", "BEBE", "CACDA", "CBCB", "CDCD", "CECDE", "DADA", "DBDAB", "DCDC", "DEDE", "EAEA", "EBEB", "ECEBC", "EDED"},
            {"ABAB", "ACADC", "ADAD", "BABA", "BCBC", "BDBD", "CACBA", "CBCB", "CDCD", "DADA", "DBDB", "DCDC"},
            {"ABAB", "ACAC", "ADAD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCD", "DADA", "DBDB", "DCDC"},
            {"ABAB", "ACAC", "ADAD", "AEAE", "AFAF", "BABA", "BCBC", "BDBAD", "BEBE", "BFBCF", "CACA", "CBCB", "CDCFD", "CECE", "CFCF", "DADA", "DBDAB", "DCDFC", "DEDAE", "DFDF", "EAEA", "EBEB", "ECEC", "EDEAD", "EFEAF", "FAFA", "FBFAB", "FCFC", "FDFD", "FEFAE"},
            {"ABAB", "ACAC", "ADACD", "AEABE", "BABA", "BCBC", "BDBED", "BEBE", "CACA", "CBCB", "CDCD", "CECDE", "DADCA", "DBDCB", "DCDC", "DEDE", "EAEBA", "EBEB", "ECEBC", "EDED"},
            {"ABAB", "ACAC", "ADABD", "AEnull", "AFACF", "AGACG", "BABA", "BCBC", "BDBD", "BEnull", "BFBF", "BGBCG", "CACA", "CBCB", "CDCBD", "CEnull", "CFCF", "CGCG", "DADBA", "DBDB", "DCDBC", "DEnull", "DFDBF", "DGDBCG", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "FAFBA", "FBFB", "FCFC", "FDFBD", "FEnull", "FGFG", "GAGCA", "GBGFB", "GCGC", "GDGFBD", "GEnull", "GFGF"},
            {"ABAB", "ACABC", "ADnull", "AEnull", "AFAF", "AGAFG", "BABA", "BCBC", "BDnull", "BEnull", "BFBAF", "BGBAFG", "CACBA", "CBCB", "CDnull", "CEnull", "CFCBAF", "CGCBAFG", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "FAFA", "FBFAB", "FCFABC", "FDnull", "FEnull", "FGFG", "GAGFA", "GBGFAB", "GCGFABC", "GDnull", "GEnull", "GFGF"},
            {"ABACB", "ACAC", "ADACD", "BABCA", "BCBC", "BDBCD", "CACA", "CBCB", "CDCD", "DADCA", "DBDCB", "DCDC"},
            {"ABACDB", "ACAC", "ADACD", "AEACE", "BABDCA", "BCBDC", "BDBD", "BEBDE", "CACA", "CBCDB", "CDCD", "CECE", "DADCA", "DBDB", "DCDC", "DEDE", "EAECA", "EBEDB", "ECEC", "EDED"},
            {"ABAB", "ACAFC", "ADAED", "AEAE", "AFAF", "BABA", "BCBFC", "BDBFCD", "BEBAE", "BFBF", "CACFA", "CBCFB", "CDCD", "CECDE", "CFCF", "DADEA", "DBDEAB", "DCDC", "DEDE", "DFDCF", "EAEA", "EBEAB", "ECEDC", "EDED", "EFEAF", "FAFA", "FBFB", "FCFC", "FDFCD", "FEFAE"},
            {"ABAB", "ACAC", "ADAGD", "AEAE", "AFAGF", "AGAG", "BABA", "BCBEC", "BDBGD", "BEBE", "BFBF", "BGBG", "CACA", "CBCAB", "CDCAGD", "CECE", "CFCEF", "CGCAG", "DADGA", "DBDGB", "DCDGAC", "DEDFE", "DFDF", "DGDG", "EAEA", "EBEB", "ECEC", "EDEFD", "EFEF", "EGEBG", "FAFGA", "FBFB", "FCFEC", "FDFD", "FEFE", "FGFG", "GAGA", "GBGB", "GCGAC", "GDGD", "GEGBE", "GFGF"},
            {"ABAB", "ACAC", "ADABD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCD", "DADCA", "DBDB", "DCDC"},
            {"ABnull", "ACnull", "ADnull", "AEnull", "AFnull", "BAnull", "BCBC", "BDnull", "BEnull", "BFBF", "CAnull", "CBCB", "CDnull", "CEnull", "CFCF", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "FAnull", "FBFB", "FCFC", "FDnull", "FEnull"},
            {"ABAEB", "ACAEC", "ADnull", "AEAE", "AFnull", "AGAG", "AHAH", "BABEA", "BCBC", "BDnull", "BEBE", "BFnull", "BGBEAG", "BHBEAH", "CACEA", "CBCB", "CDnull", "CECE", "CFnull", "CGCEAG", "CHCEAH", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "DHnull", "EAEA", "EBEB", "ECEC", "EDnull", "EFnull", "EGEAG", "EHEAH", "FAnull", "FBnull", "FCnull", "FDnull", "FEnull", "FGnull", "FHnull", "GAGA", "GBGAEB", "GCGAEC", "GDnull", "GEGAE", "GFnull", "GHGAH", "HAHA", "HBHAEB", "HCHAEC", "HDnull", "HEHAE", "HFnull", "HGHAG"},
            {"ABAB", "ACAC", "ADAD", "AEAGE", "AFABF", "AGAG", "BABA", "BCBC", "BDBGD", "BEBGE", "BFBF", "BGBG", "CACA", "CBCB", "CDCGD", "CECGE", "CFCBF", "CGCG", "DADA", "DBDAB", "DCDAC", "DEDGE", "DFDABF", "DGDG", "EAEGA", "EBEGB", "ECEGC", "EDEGD", "EFEGBF", "EGEG", "FAFBA", "FBFB", "FCFBC", "FDFBGD", "FEFBGE", "FGFBG", "GAGA", "GBGB", "GCGC", "GDGD", "GEGE", "GFGBF"},
            {"ABAGB", "ACAEC", "ADAD", "AEAE", "AFAF", "AGAG", "BABGA", "BCBGC", "BDBGED", "BEBGE", "BFBGAF", "BGBG", "CACDA", "CBCGB", "CDCD", "CECE", "CFCDAF", "CGCG", "DADA", "DBDCGB", "DCDC", "DEDE", "DFDAF", "DGDCG", "EAEA", "EBEGB", "ECEC", "EDED", "EFEAF", "EGEG", "FAFA", "FBFAGB", "FCFAEC", "FDFAD", "FEFAE", "FGFAG", "GAGA", "GBGB", "GCGC", "GDGED", "GEGE", "GFGAF"},
            {"ABAB", "ACADC", "ADAD", "AEnull", "AFAF", "BABA", "BCBDC", "BDBD", "BEnull", "BFBAF", "CACDA", "CBCDB", "CDCD", "CEnull", "CFCDAF", "DADA", "DBDB", "DCDC", "DEnull", "DFDAF", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "FAFA", "FBFAB", "FCFADC", "FDFAD", "FEnull"},
            {"ABAEDB", "ACAC", "ADAED", "AEAE", "BABDCA", "BCBDC", "BDBD", "BEBDE", "CACA", "CBCDB", "CDCD", "CECE", "DADCA", "DBDB", "DCDC", "DEDE", "EAEA", "EBEDB", "ECEC", "EDED"},
            {"ABAB", "ACAC", "ADACD", "BABA", "BCBAC", "BDBACD", "CACA", "CBCAB", "CDCD", "DADCA", "DBDCAB", "DCDC"},
            {"ABAFDB", "ACAFEC", "ADAFD", "AEAFE", "AFAF", "BABDFA", "BCBC", "BDBD", "BEBCE", "BFBDF", "CACEFA", "CBCB", "CDCBD", "CECE", "CFCEF", "DADFA", "DBDB", "DCDBC", "DEDFE", "DFDF", "EAEFA", "EBECB", "ECEC", "EDEFD", "EFEF", "FAFA", "FBFDB", "FCFEC", "FDFD", "FEFE"},
            {"ABAB", "ACADC", "ADAD", "AEADE", "BABA", "BCBADC", "BDBAD", "BEBADE", "CACDA", "CBCDAB", "CDCD", "CECDE", "DADA", "DBDAB", "DCDC", "DEDE", "EAEDA", "EBEDAB", "ECEDC", "EDED"},
            {"ABAB", "ACADC", "ADAD", "AEAE", "AFAEF", "AGAG", "AHABH", "BABA", "BCBDC", "BDBD", "BEBAE", "BFBDF", "BGBAG", "BHBH", "CACDA", "CBCDB", "CDCD", "CECFE", "CFCF", "CGCDAG", "CHCH", "DADA", "DBDB", "DCDC", "DEDAE", "DFDF", "DGDAG", "DHDH", "EAEA", "EBEAB", "ECEFC", "EDEFD", "EFEF", "EGEAG", "EHEFCH", "FAFEA", "FBFDB", "FCFC", "FDFD", "FEFE", "FGFEAG", "FHFCH", "GAGA", "GBGAB", "GCGADC", "GDGAD", "GEGAE", "GFGAEF", "GHGABH", "HAHDA", "HBHB", "HCHC", "HDHD", "HEHCFE", "HFHCF", "HGHDAG"}
    };

    String[][] staticGraphs = {
            {"AC", "AB", "BD", "BE", "EH", "EF", "FG", "FC", "GC"},
            {"AC", "AE", "BI", "BD", "BG", "BF", "BE", "CI", "DI", "DG", "HI"},
            {"AB", "AC", "BC", "DE", "DF", "EF"}
    };
    String[] staticVertices =
            {"ABCDEFGH", "ABCDEFGHI", "ABCDEF"};
    String[][] staticSolutions = {
            {"ABAB", "ACAC", "ADABD", "AEABE", "AFACF", "AGACG", "AHABEH", "BABA", "BCBAC", "BDBD", "BEBE", "BFBEF", "BGBACG", "BHBEH", "CACA", "CBCAB", "CDCABD", "CECFE", "CFCF", "CGCG", "CHCFEH", "DADBA", "DBDB", "DCDBAC", "DEDBE", "DFDBEF", "DGDBACG", "DHDBEH", "EAEBA", "EBEB", "ECEFC", "EDEBD", "EFEF", "EGEFG", "EHEH", "FAFCA", "FBFEB", "FCFC", "FDFEBD", "FEFE", "FGFG", "FHFEH", "GAGCA", "GBGFEB", "GCGC", "GDGFEBD", "GEGFE", "GFGF", "GHGFEH", "HAHEBA", "HBHEB", "HCHEFC", "HDHEBD", "HEHE", "HFHEF", "HGHEFG"},
            {"ABAEB", "ACAC", "ADACID", "AEAE", "AFAEBF", "AGAEBG", "AHACIH", "AIACI", "BABEA", "BCBIC", "BDBD", "BEBE", "BFBF", "BGBG", "BHBIH", "BIBI", "CACA", "CBCIB", "CDCID", "CECAE", "CFCIBF", "CGCIBG", "CHCIH", "CICI", "DADBEA", "DBDB", "DCDIC", "DEDBE", "DFDBF", "DGDG", "DHDIH", "DIDI", "EAEA", "EBEB", "ECEAC", "EDEBD", "EFEBF", "EGEBG", "EHEBIH", "EIEBI", "FAFBEA", "FBFB", "FCFBIC", "FDFBD", "FEFBE", "FGFBG", "FHFBIH", "FIFBI", "GAGBEA", "GBGB", "GCGBIC", "GDGD", "GEGBE", "GFGBF", "GHGBIH", "GIGBI", "HAHICA", "HBHIB", "HCHIC", "HDHID", "HEHIBE", "HFHIBF", "HGHIBG", "HIHI", "IAICA", "IBIB", "ICIC", "IDID", "IEIBE", "IFIBF", "IGIBG", "IHIH"},
            {"ABAB", "ACAC", "ADnull", "AEnull", "AFnull", "BABA", "BCBC", "BDnull", "BEnull", "BFnull", "CACA", "CBCB", "CDnull", "CEnull", "CFnull", "DAnull", "DBnull", "DCnull", "DEDE", "DFDF", "EAnull", "EBnull", "ECnull", "EDED", "EFEF", "FAnull", "FBnull", "FCnull", "FDFD", "FEFE"}
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
            Method unweighted = dfs.getMethod("breadthFirstSearch_Unweighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<staticGraphs.length; g++)
                for(int t=0; t<staticSolutions[g].length; t++) {
                    if(staticSolutions[g][t].charAt(2)=='n')
                        assertNull("breadthFirstSearch_Unweighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed to produce null.",
                                unweighted.invoke(dfs,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
                    else
                        checkSolution(
                                "breadthFirstSearch_Unweighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed ",
                                staticGraphs[g],
                                staticSolutions[g][t],
                                (String) unweighted.invoke(dfs,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
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
            Class<?> dfs = Class.forName(generateClassName("DS8_BFS"));
            Method unweighted = dfs.getMethod("breadthFirstSearch_Unweighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<testGraphs.length; g++)
                for(int t=0; t<testSolutions[g].length; t++) {
                    if(testSolutions[g][t].charAt(2)=='n')
                        assertNull("breadthFirstSearch_Unweighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed to produce null.",
                                unweighted.invoke(dfs,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                    else
                        checkSolution(
                                "breadthFirstSearch_Unweighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed ",
                                testGraphs[g],
                                testSolutions[g][t],
                                (String) unweighted.invoke(dfs,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public void checkSolution(String message,String[] edgesIn, String teacherSolution,String studentSolution)
    {
        ArrayList<String> edgesList = new ArrayList<>();
        for(String ed:edgesIn)
            edgesList.add(ed);

        Assert.assertNotNull(message + "to produce a path",studentSolution);

        Assert.assertEquals(message + "to produce a path of the correct length",teacherSolution.length()-2,studentSolution.length());

        Assert.assertEquals(message + "to produce a path that starts with "+teacherSolution.charAt(0),teacherSolution.charAt(0),studentSolution.charAt(0));
        Assert.assertEquals(message + "to produce a path that ends with "+teacherSolution.charAt(1),teacherSolution.charAt(1),studentSolution.charAt(studentSolution.length()-1));

        Assert.assertEquals(message + "to produce a path that ends with "+teacherSolution.charAt(1),teacherSolution.charAt(1),studentSolution.charAt(studentSolution.length()-1));

        for(int x =1; x<studentSolution.length(); x++)
            Assert.assertTrue(message + " due to containing an invalid edge.",
                    edgesList.contains(studentSolution.charAt(x-1)+""+studentSolution.charAt(x)) ||
                            edgesList.contains(studentSolution.charAt(x)+""+studentSolution.charAt(x-1)) );

    }


}
