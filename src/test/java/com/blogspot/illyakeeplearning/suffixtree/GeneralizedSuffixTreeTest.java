/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree;

import junit.framework.TestCase;

import java.util.List;

public class GeneralizedSuffixTreeTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testAbabBaba() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abab", "baba", '#', '$');
        TestHelper.dumpEdges(gst.getSuffixTree());
        assertEquals("aba", gst.getLcsAsString());
    }

    public void testXabxaBabxba() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("xabxa", "babxba", '#', '$');
        TestHelper.dumpEdges(gst.getSuffixTree());
        assertEquals("abx", gst.getLcsAsString());
    }

    public void testAlive() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("superriorcalifoernialives", "sealiver", '#', '$');
        assertEquals("alive", gst.getLcsAsString());

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("superriorcalifoernialives".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree("aaasuperriorcalifoernialives", "aaasealiver", '#', '$');
        assertEquals("alive", gst.getLcsAsString());

        list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("aaasuperriorcalifoernialives".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree("aaasealiver", "aaasuperriorcalifoernialives", '#', '$');
        assertEquals("alive", gst.getLcsAsString());

        list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("aaasealiver".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();
    }

    public void testAbabBabaBa() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(new String[]{"abab", "baba", "ba"}, new char[]{'#', '$', '%'});
        TestHelper.dumpEdges(gst.getSuffixTree());
        assertEquals("ba", gst.getLcsAsString());
    }

    public void testAlive3() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(
                new String[]{"superiorcalifornialives", "sealivers", "szalivefrerds"}, 
                new char[]{'#', '$', '%'});
        assertEquals("alive", gst.getLcsAsString());

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("superiorcalifornialives".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();
    }
    
    public void testAbabBaba2() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abab", "baba", '#', '$');
        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(2, commonSubstr.getEndIndexes()[0]);
    }

    public void testAbababcAbbbabc() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abababc", "abbbabc", '#', '$');
        assertEquals("babc", gst.getLcsAsString());
        assertEquals(3, gst.getSuffixTree().indexOf("babc"));
        assertEquals(5, gst.getSuffixTree().indexOf("bc"));

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(3, commonSubstr.getBeginIndexes()[0]);
        assertEquals(6, commonSubstr.getEndIndexes()[0]);

        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), commonSubstr.getBeginIndexes());
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(1, commonSubstr.getEndIndexes()[0]);
    }

    public void testAbababcDdddddddabc() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abababc", "ddddddddabc", '#', '$');
        assertEquals("abc", gst.getLcsAsString());
    }

    public void testDiff() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abcabeabg", "abdabfabh", '#', '$');

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(1, commonSubstr.getEndIndexes()[0]);
        assertEquals(10, commonSubstr.getBeginIndexes()[1]);
        assertEquals(11, commonSubstr.getEndIndexes()[1]);

        commonSubstr = gst.getLcs(gst.incIndexes(commonSubstr.getEndIndexes()), gst.getDefaultEndIndexes());
        assertNotNull(commonSubstr);
        assertEquals(3, commonSubstr.getBeginIndexes()[0]);
        assertEquals(4, commonSubstr.getEndIndexes()[0]);
        assertEquals(13, commonSubstr.getBeginIndexes()[1]);
        assertEquals(14, commonSubstr.getEndIndexes()[1]);

        commonSubstr = gst.getLcs(gst.incIndexes(commonSubstr.getEndIndexes()), gst.getDefaultEndIndexes());
        assertNotNull(commonSubstr);
        assertEquals(6, commonSubstr.getBeginIndexes()[0]);
        assertEquals(7, commonSubstr.getEndIndexes()[0]);
        assertEquals(16, commonSubstr.getBeginIndexes()[1]);
        assertEquals(17, commonSubstr.getEndIndexes()[1]);

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list) {
            System.out.print("abcabeabg".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        }
        System.out.println();
    }

    public void testDiff2() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abababcdab", "abbbabceab", '#', '$');

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(3, commonSubstr.getBeginIndexes()[0]);
        assertEquals(6, commonSubstr.getEndIndexes()[0]);
        assertEquals(14, commonSubstr.getBeginIndexes()[1]);
        assertEquals(17, commonSubstr.getEndIndexes()[1]);

        int[] beginIndexes = gst.incIndexes(commonSubstr.getEndIndexes());
        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), commonSubstr.getBeginIndexes());
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(1, commonSubstr.getEndIndexes()[0]);
        assertEquals(11, commonSubstr.getBeginIndexes()[1]);
        assertEquals(12, commonSubstr.getEndIndexes()[1]);

        commonSubstr = gst.getLcs(beginIndexes, gst.getDefaultEndIndexes());
        assertNotNull(commonSubstr);
        assertEquals(8, commonSubstr.getBeginIndexes()[0]);
        assertEquals(9, commonSubstr.getEndIndexes()[0]);
        assertEquals(19, commonSubstr.getBeginIndexes()[1]);
        assertEquals(20, commonSubstr.getEndIndexes()[1]);

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list) {
            System.out.print("abababcdab".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        }
        System.out.println();
    }

    public void testDiff3() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("ddddddddabc", "abababc", '#', '$');
        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(1, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list) {
            System.out.print("ddddddddabc".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        }
        System.out.println();
    }

    public void testDiff4() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abababcdde", "ddddddabc", '#', '$');
        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(1, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list) {
            System.out.print("ddddddddabc".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        }
        System.out.println();
    }

    public void testLcs() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abababcdab", "abbbabceab", '#', '$');
        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();

        int beginIndexes[] = commonSubstr.getBeginIndexes();
        int endIndexes[] = commonSubstr.getEndIndexes();
        assertEquals(3, beginIndexes[0]);
        assertEquals(6, endIndexes[0]);
        assertEquals(14, beginIndexes[1]);
        assertEquals(17, endIndexes[1]);

        int leftEndIndexes[] = new int[]{3, 14};
        int rightBeginIndexes[] = new int[]{7, 18};

        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), leftEndIndexes);
        beginIndexes = commonSubstr.getBeginIndexes();
        endIndexes = commonSubstr.getEndIndexes();
        assertEquals(0, beginIndexes[0]);
        assertEquals(1, endIndexes[0]);
        assertEquals(11, beginIndexes[1]);
        assertEquals(12, endIndexes[1]);

        commonSubstr = gst.getLcs(rightBeginIndexes, gst.getDefaultEndIndexes());
        beginIndexes = commonSubstr.getBeginIndexes();
        endIndexes = commonSubstr.getEndIndexes();
        assertEquals(8, beginIndexes[0]);
        assertEquals(9, endIndexes[0]);
        assertEquals(19, beginIndexes[1]);
        assertEquals(20, endIndexes[1]);
    }

    public void testLcs2() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("ddddddddabc", "abababc", '#', '$');
        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();

        int beginIndexes[] = commonSubstr.getBeginIndexes();
        int endIndexes[] = commonSubstr.getEndIndexes();
        assertEquals(8, beginIndexes[0]);
        assertEquals(10, endIndexes[0]);
        assertEquals(16, beginIndexes[1]);
        assertEquals(18, endIndexes[1]);

        int leftEndIndexes[] = new int[]{8, 16};
        int rightBeginIndexes[] = new int[]{11, 19};

        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), leftEndIndexes);
        assertNull(commonSubstr);

        commonSubstr = gst.getLcs(rightBeginIndexes, gst.getDefaultEndIndexes());
        assertNull(commonSubstr);
    }

    public void testDefaultTerminators() {
        char[] terminators = GeneralizedSuffixTree.getDefaultTerminators(2);
        assertEquals(2, terminators.length);
        assertEquals('\ud800', terminators[0]);
        assertEquals('\ud801', terminators[1]);

        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(new String[]{"abab", "baba"});
        TestHelper.dumpEdges(gst.getSuffixTree());
        assertEquals("aba", gst.getLcsAsString());

        gst = new GeneralizedSuffixTree(new String[]{"abababcdab", "abbbabceab"});

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(3, commonSubstr.getBeginIndexes()[0]);
        assertEquals(6, commonSubstr.getEndIndexes()[0]);
        assertEquals(14, commonSubstr.getBeginIndexes()[1]);
        assertEquals(17, commonSubstr.getEndIndexes()[1]);

        int[] beginIndexes = gst.incIndexes(commonSubstr.getEndIndexes());
        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), commonSubstr.getBeginIndexes());
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(1, commonSubstr.getEndIndexes()[0]);
        assertEquals(11, commonSubstr.getBeginIndexes()[1]);
        assertEquals(12, commonSubstr.getEndIndexes()[1]);

        commonSubstr = gst.getLcs(beginIndexes, gst.getDefaultEndIndexes());
        assertNotNull(commonSubstr);
        assertEquals(8, commonSubstr.getBeginIndexes()[0]);
        assertEquals(9, commonSubstr.getEndIndexes()[0]);
        assertEquals(19, commonSubstr.getBeginIndexes()[1]);
        assertEquals(20, commonSubstr.getEndIndexes()[1]);

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list) {
            System.out.print("abababcdab".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        }
        System.out.println();
    }

    public void testFailure() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree("abba", "ccde", '#', '$');
        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNull(commonSubstr);

        gst = new GeneralizedSuffixTree("123456", "7890", '#', '$');
        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(0, list.size());
    }

    public void testMerge() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(
                "djfhsdajkzzsdfajkhasdjfghhshafjhsrtyuidfsj",
                "djfhsdajkfasdfajkhasdjfadjshafjhsadjhsadfsj");
        assertEquals("sdfajkhasdjf", gst.getLcsAsString());

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(4, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("djfhsdajkzzsdfajkhasdjfghhshafjhsrtyuidfsj".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree(
                "djfhsdajkfasdfajkhasdjfadjshafjhsadjhsadfsj",
                "djfhsdajkzzsdfajkhasdjfghhshafjhsrtyuidfsj");
        assertEquals("sdfajkhasdjf", gst.getLcsAsString());

        list = gst.diff();
        assertEquals(4, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("djfhsdajkfasdfajkhasdjfadjshafjhsadjhsadfsj".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree(
                "djfhsdajkzzsdfaaaaaaaaaaaaaafjhsrtyuidfsj",
                "djfhsdajkfasdfaaaaaaaaaaajshafjhsadjhsadfsj");
        assertEquals("sdfaaaaaaaaaaa", gst.getLcsAsString());

        list = gst.diff();
        assertEquals(4, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("djfhsdajkzzsdfaaaaaaaaaaaaaafjhsrtyuidfsj".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree(
                "djfhsdajkfasdfaaaaaaaaaaajshafjhsadjhsadfsj",
                "djfhsdajkzzsdfaaaaaaaaaaaaaafjhsrtyuidfsj");
        assertEquals("sdfaaaaaaaaaaa", gst.getLcsAsString());

        list = gst.diff();
        assertEquals(4, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("djfhsdajkfasdfaaaaaaaaaaajshafjhsadjhsadfsj".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();
    }

    public void testMerge2() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(
                "12345 67890",
                "12345 sdfsd 67890");

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(0, commonSubstr.getBeginIndexes()[0]);
        assertEquals(5, commonSubstr.getEndIndexes()[0]);

        assertEquals("12345 ", gst.getLcsAsString());

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(2, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("12345 67890".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();


        gst = new GeneralizedSuffixTree(
                "12345 sdfsd 67890",
                "12345 67890");

        list = gst.diff();
        assertEquals(2, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print("12345 sdfsd 67890".substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();
    }
    
    public void testMama() {
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(
                "mama myla ramu",
                "ramu myla mama",
                '#', '$');

        GeneralizedSuffixTree.CommonSubstr commonSubstr = gst.getLcs();
        assertNotNull(commonSubstr);
        assertEquals(4, commonSubstr.getBeginIndexes()[0]);
        assertEquals(9, commonSubstr.getEndIndexes()[0]);
        assertEquals(19, commonSubstr.getBeginIndexes()[1]);
        assertEquals(24, commonSubstr.getEndIndexes()[1]);

        commonSubstr = gst.getLcs(gst.getDefaultBeginIndexes(), commonSubstr.getBeginIndexes());
        assertNotNull(commonSubstr);
        assertEquals(1, commonSubstr.getBeginIndexes()[0]);
        assertEquals(2, commonSubstr.getEndIndexes()[0]);
        assertEquals(16, commonSubstr.getBeginIndexes()[1]);
        assertEquals(17, commonSubstr.getEndIndexes()[1]);

        assertEquals(" myla ", gst.getLcsAsString());

        List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();
        assertEquals(3, list.size());

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print(gst.getGeneralizedString().substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]) + " ");
        System.out.println();

        for (GeneralizedSuffixTree.CommonSubstr cs : list)
            System.out.print(gst.getGeneralizedString().substring(cs.getBeginIndexes()[1], cs.getEndIndexes()[1]) + " ");
        System.out.println();
    }
}
