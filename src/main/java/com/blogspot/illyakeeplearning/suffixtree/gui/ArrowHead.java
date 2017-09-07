/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import java.awt.*;
import java.awt.geom.*;

class ArrowHead
        extends Object
        implements Shape, Cloneable {
    /**
     * Central width.
     */
    public static final int ARROWHEAD_CENTRAL_WIDTH = 6;

    /**
     * Total width.
     */
    public static final int ARROWHEAD_WIDTH = 9;

    /**
     * Total height.
     */
    public static final int ARROWHEAD_HEIGHT = 4;

    /**
     * General Path object.
     */
    private GeneralPath head = new GeneralPath();

    /**
     * Location Point object.
     */
    private Point2D.Double pointTo = new Point2D.Double();

    /**
     * Angle.
     */
    private double angle;

    /**
     * Constructs ArrowHead object.
     */
    public ArrowHead() {
        head.moveTo(0, 0);
        head.lineTo(-ARROWHEAD_WIDTH, ARROWHEAD_HEIGHT);
        head.lineTo(-(ARROWHEAD_CENTRAL_WIDTH), 0);
        head.lineTo(-ARROWHEAD_WIDTH, -ARROWHEAD_HEIGHT);
        head.closePath();
    }

    /**
     * Sets Arrow Head location and angle.
     *
     * @param xTo   double
     * @param yTo   double
     * @param angle double
     */
    public void setLocation(double xTo, double yTo, double angle) {
        pointTo.setLocation(xTo, yTo);
        this.angle = angle;
    }

    /**
     * @return Rectangle
     * @see java.awt.Shape#contains(double, double)
     */
    public Rectangle getBounds() {
        return head.getBounds();
    }

    /**
     * @return Rectangle2D
     * @see java.awt.Shape#getBounds2D
     */
    public Rectangle2D getBounds2D() {
        return head.getBounds2D();
    }

    /**
     * @param x double
     * @param y double
     * @return boolean
     * @see java.awt.Shape#contains(double, double)
     */
    public boolean contains(double x, double y) {
        return head.contains(x, y);
    }

    /**
     * @param p Point2D
     * @return boolean
     * @see java.awt.Shape#contains(java.awt.geom.Point2D)
     */
    public boolean contains(Point2D p) {
        return head.contains(p);
    }

    /**
     * @param x double
     * @param y double
     * @param w double
     * @param h double
     * @return boolean
     * @see java.awt.Shape#intersects(double, double, double, double)
     */
    public boolean intersects(double x, double y, double w, double h) {
        return head.intersects(x, y, w, h);
    }

    /**
     * @param r Rectangle2D
     * @return boolean
     * @see java.awt.Shape#intersects(java.awt.geom.Rectangle2D)
     */
    public boolean intersects(Rectangle2D r) {
        return head.intersects(r);
    }

    /**
     * @param x double
     * @param y double
     * @param w double
     * @param h double
     * @return boolean
     * @see java.awt.Shape#contains(double, double, double, double)
     */
    public boolean contains(double x, double y, double w, double h) {
        return head.contains(x, y, w, h);
    }

    /**
     * @param r Rectangle2D
     * @return boolean
     * @see java.awt.Shape#contains(java.awt.geom.Rectangle2D)
     */
    public boolean contains(Rectangle2D r) {
        return head.contains(r);
    }

    /**
     * @param at AffineTransform
     * @return PathIterator
     * @see java.awt.Shape#getPathIterator(java.awt.geom.AffineTransform)
     */
    public PathIterator getPathIterator(AffineTransform at) {
        AffineTransform transform = new AffineTransform();
        transform.translate(pointTo.getX(), pointTo.getY());
        transform.rotate(angle);
        return head.createTransformedShape(transform).getPathIterator(at);
    }

    /**
     * @param at       AffineTransform
     * @param flatness double
     * @return PathIterator
     * @see java.awt.Shape#getPathIterator(java.awt.geom.AffineTransform, double)
     */
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        AffineTransform transform = new AffineTransform();
        transform.translate(pointTo.getX(), pointTo.getY());
        transform.rotate(angle);
        return head.createTransformedShape(transform).getPathIterator(at, flatness);
    }
}
