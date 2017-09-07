/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import com.blogspot.illyakeeplearning.suffixtree.SuffixTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class STApplet extends JApplet {
    private static final Dimension HGAP5 = new Dimension(5, 1);

    private STDrawPanel drawPanel;
    private STControlPanel controlPanel;

    private SuffixTree suffixTree;

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
        private JLabel textLabel;
        private JTextField textField;

        public STControlPanel() {
            buildButton = new JButton("Build Suffix Tree");
            buildButton.addActionListener(this);

            textLabel = new JLabel("Enter String: ");
            textField = new JTextField();

            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(Box.createRigidArea(HGAP5));
            add(textLabel, BorderLayout.WEST);
            add(Box.createRigidArea(HGAP5));
            add(textField, BorderLayout.CENTER);
            add(Box.createRigidArea(HGAP5));
            add(buildButton, BorderLayout.EAST);
        }

        public void actionPerformed(ActionEvent e) {
            String arg = e.getActionCommand();
            if (arg.equals("Build Suffix Tree")) {
                suffixTree = new SuffixTree(textField.getText());
                drawPanel.setSuffixTree(suffixTree);
            }
        }
    }
}
