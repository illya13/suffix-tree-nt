/**
 * More details
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree.gui;

import com.blogspot.illyakeeplearning.suffixtree.GeneralizedSuffixTree;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class DiffApplet extends JApplet {
    private static final Dimension HGAP5 = new Dimension(5, 1);
    private static final String BOLD_ITALIC = "BoldItalic";

    private STControlPanel controlPanel;
    private JTextPane textPane;

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

        textPane = new JTextPane();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, controlPanel);
        getContentPane().add(BorderLayout.CENTER, textPane);
    }


    private class STControlPanel extends JPanel implements ActionListener {
        private JButton buildButton;
        private JLabel textLabel1;
        private JTextField textField1;
        private JLabel textLabel2;
        private JTextField textField2;

        public STControlPanel() {
            buildButton = new JButton("Identical");
            buildButton.addActionListener(this);

            textLabel1 = new JLabel("String 1: ");
            textField1 = new JTextField();

            textLabel2 = new JLabel("String 2: ");
            textField2 = new JTextField();

            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
            panel1.add(Box.createRigidArea(HGAP5));
            panel1.add(textLabel1, BorderLayout.WEST);
            panel1.add(Box.createRigidArea(HGAP5));
            panel1.add(textField1, BorderLayout.WEST);

            JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
            panel2.add(Box.createRigidArea(HGAP5));
            panel2.add(textLabel2, BorderLayout.WEST);
            panel2.add(Box.createRigidArea(HGAP5));
            panel2.add(textField2, BorderLayout.WEST);

            setLayout(new BorderLayout());

            add(panel1, BorderLayout.NORTH);
            add(panel2, BorderLayout.CENTER);
            add(buildButton, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            String arg = e.getActionCommand();
            if (arg.equals("Identical"))
                try {
                    StyledDocument document = getStyledDocument();
                    Style defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
                    Style boldStyle = document.getStyle(BOLD_ITALIC);

                    GeneralizedSuffixTree gst = new GeneralizedSuffixTree(textField1.getText(), textField2.getText());
                    String generalized = gst.getGeneralizedString();
                    List<GeneralizedSuffixTree.CommonSubstr> list = gst.diff();

                    Collections.sort(list, new FirstStringComparator());

                    int start = 0;
                    for (GeneralizedSuffixTree.CommonSubstr cs : list) {
                        if (cs.getBeginIndexes()[0] > start) {
                            document.insertString(document.getLength(),
                                    generalized.substring(start, cs.getBeginIndexes()[0]),
                                    defaultStyle);
                        }
                        document.insertString(document.getLength(),
                                generalized.substring(cs.getBeginIndexes()[0], cs.getEndIndexes()[0]),
                                boldStyle);
                        start = cs.getEndIndexes()[0];
                    }
                    if (start <= gst.getDefaultEndIndexes()[0]) {
                        document.insertString(document.getLength(),
                                generalized.substring(start, gst.getDefaultEndIndexes()[0]),
                                defaultStyle);
                    }

                    document.insertString(document.getLength(), "\n\n", defaultStyle);

                    start = gst.getDefaultBeginIndexes()[1];
                    for (GeneralizedSuffixTree.CommonSubstr cs : list) {
                        if (cs.getBeginIndexes()[1] > start) {
                            document.insertString(document.getLength(),
                                    generalized.substring(start, cs.getBeginIndexes()[1]),
                                    defaultStyle);
                        }
                        document.insertString(document.getLength(),
                                generalized.substring(cs.getBeginIndexes()[1], cs.getEndIndexes()[1]),
                                boldStyle);
                        start = cs.getEndIndexes()[1];
                    }
                    if (start <= gst.getDefaultEndIndexes()[1]) {
                        document.insertString(document.getLength(),
                                generalized.substring(start, gst.getDefaultEndIndexes()[1]),
                                defaultStyle);
                    }

                    textPane.setDocument(document);
                } catch (BadLocationException e1) {
                    // ignore
                }
        }

        private StyledDocument getStyledDocument() {
            StyledDocument document = new DefaultStyledDocument();

            document.addStyle(BOLD_ITALIC, null);
            Style style = document.getStyle(BOLD_ITALIC);
            StyleConstants.setBold(style, true);
            //StyleConstants.setItalic(style, true);
            StyleConstants.setForeground(style, Color.BLUE);

            return document;
        }
    }

    private class FirstStringComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            GeneralizedSuffixTree.CommonSubstr cs1 = (GeneralizedSuffixTree.CommonSubstr)o1;
            GeneralizedSuffixTree.CommonSubstr cs2 = (GeneralizedSuffixTree.CommonSubstr)o2;
            return ((Integer)cs1.getBeginIndexes()[0]).compareTo(cs1.getBeginIndexes()[0]);
        }
    }
}