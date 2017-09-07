/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree;

import junit.framework.TestCase;

public class SuffixTreeTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBook() {
        SuffixTree st = new SuffixTree("book");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("book"));
        assertTrue(st.contains("oo"));
        assertTrue(st.contains("ok"));
        assertFalse(st.contains("okk"));
        assertFalse(st.contains("bookk"));

        assertEquals(0, st.indexOf("book"));
        assertEquals(1, st.indexOf("oo"));
        assertEquals(2, st.indexOf("ok"));
    }

    public void testBookke() {
        SuffixTree st = new SuffixTree("bookke");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("bookk"));

        assertEquals(0, st.indexOf("book"));
        assertEquals(1, st.indexOf("oo"));
        assertEquals(2, st.indexOf("ok"));
    }

    public void testCacao() {
        SuffixTree st = new SuffixTree("cacao");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("aca"));

        assertEquals(3, st.indexOf("ao"));
        assertEquals(0, st.indexOf("ca"));
        assertEquals(2, st.indexOf("cao"));
    }

    public void testGoogol() {
        SuffixTree st = new SuffixTree("googol");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("oo"));

        assertEquals(0, st.indexOf("go"));
        assertEquals(3, st.indexOf("gol"));
        assertEquals(1, st.indexOf("oo"));
    }

    public void testAbababc() {
        SuffixTree st = new SuffixTree("abababc");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("aba"));

        assertEquals(0, st.indexOf("aba"));
        assertEquals(4, st.indexOf("abc"));
    }

    public void testUnicode() {
        char SOH = '\u0001';
        char STX = '\u0002';

        SuffixTree st = new SuffixTree(SOH+"cacao"+STX);
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("aca"));

        assertEquals(4, st.indexOf("ao"));
        assertEquals(1, st.indexOf("ca"));
        assertEquals(3, st.indexOf("cao"));
    }

    public void testGeneralized() {
        SuffixTree st = new SuffixTree("abab#baba$ba%");
        TestHelper.dumpEdges(st);

        assertTrue(st.contains("aba"));

        assertEquals(0, st.indexOf("aba"));
        assertEquals(-1, st.indexOf("abc"));
    }
}
