/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree;

import java.util.Queue;
import java.util.LinkedList;

public class TestHelper {
    public static void dumpEdges(SuffixTree suffixTree) {
        System.out.println("\tEdge \tStart \tEnd \tSuf \tFirst \tLast \tString");
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(suffixTree.getRootNode());
        dumpEdges(suffixTree, queue);
    }

    private static void dumpEdges(SuffixTree suffixTree, Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (Edge edge : node.getEdges()) {
                Node suffixNode = edge.getEndNode().getSuffixNode();
                System.out.print("\t" + edge + " " +
                        "\t\t" + edge.getStartNode() + " " +
                        "\t\t" + edge.getEndNode() + " " +
                        "\t\t" + ((suffixNode == null) ? "-1" : suffixNode) + " " +
                        "\t\t" + edge.getBeginIndex() + " " +
                        "\t\t" + edge.getEndIndex() + " " +
                        "\t\t");

                System.out.println(suffixTree.getText().substring(edge.getBeginIndex(), edge.getEndIndex()+1));

                if (edge.getEndNode() != null)
                    queue.add(edge.getEndNode());
            }
        }
    }
}
