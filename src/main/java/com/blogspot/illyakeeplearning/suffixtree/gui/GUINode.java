/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

class GUINode extends GUIObject implements MouseMotionListener {
    public static final int WIDTH = 13;
    public static final int HEIGHT = 13;
    public static final int INSET = 1;

    private static final Ellipse2D ellipse = new Ellipse2D.Double(0, 0, WIDTH, HEIGHT);

    public GUINode(int x, int y) {
        Rectangle rect = getBounds();
        setBounds(rect.x, rect.y, WIDTH + 2 * INSET, HEIGHT + 2 * INSET);
        setLocation(x, y);

        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Object antialias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Color oldColor = g2.getColor();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(ellipse);
        g2.setColor(Color.BLACK);
        g2.draw(ellipse);

        g2.setColor(oldColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias);
    }

    public void mouseDragged(MouseEvent e) {
        Rectangle rect = getBounds();
        setLocation(rect.x + e.getX() - WIDTH / 2, rect.y + e.getY() - HEIGHT / 2);

        updateObservers();
    }

    public void mouseMoved(MouseEvent e) {
    }
}
