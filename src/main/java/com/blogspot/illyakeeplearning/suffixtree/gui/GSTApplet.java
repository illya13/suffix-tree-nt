/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import com.blogspot.illyakeeplearning.suffixtree.GeneralizedSuffixTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GSTApplet extends JApplet {
    private static final Dimension HGAP5 = new Dimension(5, 1);

    private STDrawPanel drawPanel;
    private STControlPanel controlPanel;

    private GeneralizedSuffixTree gst;

    @Override
    public void init() {
        String laf = UIManager.getCrossPlatformLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Warning: UnsupportedLookAndFeel: " + laf);
        } catch (Exception exc) {
            System.err.println("Error loading " + laf + ": " + exc);
        }

        controlPanel = new STControlPanel();
        drawPanel = new STDrawPanel();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, controlPanel);
        getContentPane().add(BorderLayout.CENTER, drawPanel);
    }


    private class STControlPanel extends JPanel implements ActionListener {
        private JButton buildButton;
        private JLabel textLabel1;
        private JTextField textField1;
        private JLabel textLabel2;
        private JTextField textField2;

        public STControlPanel() {
            buildButton = new JButton("Build GST");
            buildButton.addActionListener(this);

            textLabel1 = new JLabel("String 1: ");
            textField1 = new JTextField();

            textLabel2 = new JLabel("String 2: ");
            textField2 = new JTextField();

            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            add(Box.createRigidArea(HGAP5));
            add(textLabel1, BorderLayout.WEST);
            add(Box.createRigidArea(HGAP5));
            add(textField1, BorderLayout.WEST);
            add(Box.createRigidArea(HGAP5));
            add(Box.createRigidArea(HGAP5));

            add(textLabel2, BorderLayout.WEST);
            add(Box.createRigidArea(HGAP5));
            add(textField2, BorderLayout.WEST);
            add(Box.createRigidArea(HGAP5));
            add(Box.createRigidArea(HGAP5));

            add(buildButton, BorderLayout.EAST);
        }

        public void actionPerformed(ActionEvent e) {
            String arg = e.getActionCommand();
            if (arg.equals("Build GST")) {
                gst = new GeneralizedSuffixTree(textField1.getText(), textField2.getText(), '#', '$');
                drawPanel.setSuffixTree(gst.getSuffixTree());
            }
        }
    }
}