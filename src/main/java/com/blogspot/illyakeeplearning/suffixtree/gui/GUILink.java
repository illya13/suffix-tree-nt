/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import java.awt.*;

class GUILink extends GUIEdge {
    public static final int ARC_PATH_PROXIMITY_RADIUS = 4;
    private static final Color LINE_COLOUR = new Color(173, 216, 230);

    private int dy;

    public GUILink(GUINode startNode, GUINode endNode, int dy) {
        super(startNode, endNode);
        this.dy = dy;

        update();
    }

    protected void update() {
        color = LINE_COLOUR;

        GUINode startNode = getStartNode();
        GUINode endNode = getEndNode();

        double angle = getAngle(startNode.getLocation(), endNode.getLocation());

        path.reset();
        Point p1 = getArcStartPoint(startNode, angle);
        path.moveTo((float) p1.getX(), (float) p1.getY());

        Point p3 = getArcEndPoint(endNode, angle + Math.PI);
        Point p2 = new Point((int) (p1.getX() + (p3.getX() - p1.getX()) / 2 + dy),
                (int) (p1.getY() + (p3.getY() - p1.getY()) / 2 + dy));

        path.quadTo((float) p2.getX(), (float) p2.getY(),
                (float) p3.getX(), (float) p3.getY());

        Rectangle rect = path.getBounds();
        rect.grow(ARC_PATH_PROXIMITY_RADIUS, ARC_PATH_PROXIMITY_RADIUS);
        setBounds(rect);

        arrow.setLocation(p3.getX(), p3.getY(), getAngle(p2, p3));
    }
}
