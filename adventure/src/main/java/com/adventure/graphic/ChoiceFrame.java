package com.adventure.graphic;

import java.awt.Color;

import javax.swing.JFrame;
import java.awt.event.*;

/**
 * Creates a default window for choices.
 */
public abstract class ChoiceFrame extends JFrame {
    private JFrame myJFrame;

    ChoiceFrame(JFrame jf) {
        super("Fai la tua scelta");
        this.myJFrame = jf;
    }

    void create() {
        this.myJFrame.setEnabled(false);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLocation(300, 300);
        this.setSize(300, 300);

        this.addWindowListener(new MyWindowAdapter(this.myJFrame) {
            public void windowClosing(WindowEvent we) {
                this.myJFrame.setEnabled(true);
            }
        });

        this.draw();
    }

    public abstract void draw();

    public JFrame getMyJFrame() {
        return myJFrame;
    }
}