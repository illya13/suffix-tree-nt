/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import javax.swing.*;
import java.awt.*;

class GUILabel extends JLabel implements GUIObserver {
    private GUIEdge edge;

    public GUILabel(GUIEdge edge, String text) {
        super(text);
        this.edge = edge;
        edge.addObserver(this);

        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        updateSize();

        update();
    }

    private void update() {
        GUINode startNode = edge.getStartNode();
        GUINode endNode = edge.getEndNode();
        setLocation(startNode.getX() + (endNode.getX() - startNode.getX()) / 2 - 2,
                startNode.getY() + (endNode.getY() - startNode.getY()) / 2);
    }

    public void updateSize() {
        setSize((int) (getPreferredSize().width * 1.2),
                (int) (getPreferredSize().height * 1.2));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Object antialias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));

        super.paintComponent(g);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias);
    }

    public void notifyMoved() {
        update();
        repaint();
    }
}
