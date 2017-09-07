/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

class GUIObject extends JComponent {
    private List<GUIObserver> observers = new LinkedList<GUIObserver>();

    public void addObserver(GUIObserver observer) {
        observers.add(observer);
    }

    public void updateObservers() {
        for (GUIObserver observer : observers) {
            observer.notifyMoved();
        }
    }
}