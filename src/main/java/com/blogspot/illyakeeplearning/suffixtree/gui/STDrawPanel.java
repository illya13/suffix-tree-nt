package com.blogspot.illyakeeplearning.suffixtree.gui;

import com.blogspot.illyakeeplearning.suffixtree.Edge;
import com.blogspot.illyakeeplearning.suffixtree.Node;
import com.blogspot.illyakeeplearning.suffixtree.SuffixTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;

class STDrawPanel extends JPanel implements ComponentListener {
    private SuffixTree suffixTree;

    public STDrawPanel() {
        setLayout(null);
        setDoubleBuffered(true);
        addComponentListener(this);
        setBackground(Color.WHITE);

        suffixTree = null;
    }

    public void setSuffixTree(SuffixTree suffixTree) {
        this.suffixTree = suffixTree;
        updateTree();
    }

    private void updateTree() {
        removeAll();
        if (suffixTree != null) {
            Map<Node, GUINode> map = new HashMap<Node, GUINode>();

            Rectangle rect = getBounds();
            int dy = (rect.height - 40) / getTreeHeight();
            addNode(suffixTree.getRootNode(), rect.width / 2, 15, rect.width / 2, dy, map);
            addSuffixLink(suffixTree.getRootNode(), map, dy / 4);
        }
        repaint();
    }

    private int getTreeHeight() {
        return getTreeHeight(suffixTree.getRootNode(), 0);
    }

    private int getTreeHeight(Node node, int current) {
        int max = current;
        for (Edge edge : node.getEdges()) {
            int curr = getTreeHeight(edge.getEndNode(), current + 1);
            if (curr > max)
                max = curr;
        }
        return max;
    }

    private GUINode addNode(Node node, int x, int y, int dx, int dy, Map<Node, GUINode> map) {
        GUINode guiNode = new GUINode(x, y);
        map.put(node, guiNode);
        add(guiNode);

        int n = node.getEdges().size();
        if (n == 0) return guiNode;

        int newDx = (n == 1) ? dx : (dx - 3 * GUINode.HEIGHT) / (n - 1);
        int i = 0;
        for (Edge edge : node.getEdges()) {
            int newX = x - (newDx * (n - 1)) / 2 + i * newDx;
            int newY = y + dy;

            GUINode guiEndNode = addNode(edge.getEndNode(), newX, newY, newDx, dy, map);

            GUIEdge guiEdge = new GUIEdge(guiNode, guiEndNode);
            add(guiEdge);

            GUILabel label = new GUILabel(guiEdge,
                    suffixTree.getText().substring(edge.getBeginIndex(), edge.getEndIndex() + 1));
            add(label);

            i++;
        }
        return guiNode;
    }

    private void addSuffixLink(Node node, Map<Node, GUINode> map, int dy) {
        Node suffixNode = node.getSuffixNode();
        if (suffixNode != null) {
            GUILink guiLink = new GUILink(map.get(node), map.get(suffixNode), dy);
            add(guiLink);
        }
        for (Edge edge : node.getEdges()) {
            addSuffixLink(edge.getEndNode(), map, dy);
        }
    }

    public void componentResized(ComponentEvent e) {
        updateTree();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }
}
