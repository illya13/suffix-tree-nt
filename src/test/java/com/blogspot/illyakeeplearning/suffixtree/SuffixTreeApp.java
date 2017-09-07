package com.blogspot.illyakeeplearning.suffixtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SuffixTreeApp {
    private static String getTextFromURL(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            // Create a URL for the desired page
            URL urlUrl = new URL(url);

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(urlUrl.openStream()));

            String str;
            int i = 0;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                sb.append(str);
                sb.append('\n');
                i++;
                //if (i==10)
                //    break;
            }
            in.close();
            return sb.toString();
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String [] args) {
        String advsh12 = getTextFromURL("http://www.gutenberg.org/dirs/etext99/advsh12.txt");
        System.out.printf("Text size: %1$d\n", advsh12.length());
/*
        long time = System.currentTimeMillis();
        SuffixTree advsh12St = new SuffixTree(advsh12);
        System.out.printf("Time to build SuffixTree: %1$d\n", System.currentTimeMillis()- time) ;

        time = System.currentTimeMillis();
        String substring = "But Holmes shook his head gravely.";
        int index = advsh12.indexOf(substring);
        System.out.printf("Time to find [%3$s] by String.indexOf: %1$d, index: %2$d\n",
                System.currentTimeMillis()- time, index, substring);

        time = System.currentTimeMillis();
        index = advsh12St.indexOf(substring);
        System.out.printf("Time to find [%3$s] by SuffixTree.indexOf: %1$d, index: %2$d\n",
                System.currentTimeMillis()- time, index, substring);
*/


        String bskrv11a = getTextFromURL("http://www.gutenberg.org/dirs/etext02/bskrv11a.txt");
        System.out.printf("Text size: %1$d\n", bskrv11a.length());
/*
        time = System.currentTimeMillis();
        SuffixTree bskrv11aSt = new SuffixTree(bskrv11a);
        System.out.printf("Time to build SuffixTree: %1$d\n", System.currentTimeMillis()- time) ;

        time = System.currentTimeMillis();
        substring = "No doubt. There only remains one difficulty.";
        index = bskrv11a.indexOf(substring);
        System.out.printf("Time to find [%3$s] by String.indexOf: %1$d, index: %2$d\n",
                System.currentTimeMillis()- time, index, substring);

        time = System.currentTimeMillis();
        index = bskrv11aSt.indexOf(substring);
        System.out.printf("Time to find [%3$s] by SuffixTree.indexOf: %1$d, index: %2$d\n",
                System.currentTimeMillis()- time, index, substring);
*/
        long time = System.currentTimeMillis();
        GeneralizedSuffixTree gst = new GeneralizedSuffixTree(advsh12, bskrv11a);
        System.out.printf("Time to build GeneralizedSuffixTree: %1$d\n", System.currentTimeMillis()- time);

        time = System.currentTimeMillis();
        String lcs = gst.getLcsAsString();
        System.out.printf("Time to find LCS: %1$d, lcs:%2$s\n", System.currentTimeMillis()- time, lcs);
    }
}
