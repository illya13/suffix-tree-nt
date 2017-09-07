/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import java.awt.*;
import java.awt.geom.GeneralPath;

class GUIEdge extends GUIObject implements GUIObserver {
    public static final int ARC_PATH_PROXIMITY_RADIUS = 4;

    protected Color color;

    private GUINode startNode;
    private GUINode endNode;

    protected GeneralPath path;
    protected ArrowHead arrow;

    public GUIEdge(GUINode startNode, GUINode endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        startNode.addObserver(this);
        endNode.addObserver(this);

        arrow = new ArrowHead();
        path = new GeneralPath();

        update();
    }

    protected void update() {
        color = Color.LIGHT_GRAY;
        double angle = getAngle(startNode.getLocation(), endNode.getLocation());

        path.reset();
        Point p1 = getArcStartPoint(startNode, angle);
        path.moveTo((float) p1.getX(), (float) p1.getY());

        Point p2 = getArcEndPoint(endNode, angle + Math.PI);
        path.lineTo((float) p2.getX(), (float) p2.getY());

        Rectangle rect = path.getBounds();
        rect.grow(ARC_PATH_PROXIMITY_RADIUS, ARC_PATH_PROXIMITY_RADIUS);
        setBounds(rect);

        arrow.setLocation(p2.getX(), p2.getY(), getAngle(p1, p2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Object antialias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Color oldColor = g2.getColor();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        Rectangle rect = getBounds();
        g2.translate(-rect.x, -rect.y);
        g2.draw(path);
        g2.fill(arrow);
        g2.translate(rect.x, rect.y);

        g2.setColor(oldColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias);
    }

    protected Point getArcEndPoint(GUINode guiNode, double angle) {
        Rectangle rect = guiNode.getBounds();
        double x = Math.cos(angle) * (GUINode.WIDTH / 2);
        double y = Math.sin(angle) * (GUINode.HEIGHT / 2);
        return new Point((int) (rect.x + (rect.width - GUINode.INSET) / 2 + x),
                (int) (rect.y + (rect.height - GUINode.INSET) / 2 + y));
    }

    protected Point getArcStartPoint(GUINode guiNode, double angle) {
        Rectangle rect = guiNode.getBounds();
        double x = Math.cos(angle) * (GUINode.WIDTH / 2);
        double y = Math.sin(angle) * (GUINode.HEIGHT / 2);
        return new Point((int) (rect.x + rect.width / 2 + x),
                (int) (rect.y + rect.height / 2 + y));
    }

    protected double getAngle(Point p1, Point p2) {
        if (p1.equals(p2)) {
            return 0;
        }

        if (p2.getX() - p1.getX() >= 0) {
            return Math.atan((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
        } else {
            return Math.atan((p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) + Math.PI;
        }
    }

    public GUINode getStartNode() {
        return startNode;
    }

    public GUINode getEndNode() {
        return endNode;
    }

    public void notifyMoved() {
        update();
        repaint();

        updateObservers();
    }
}
