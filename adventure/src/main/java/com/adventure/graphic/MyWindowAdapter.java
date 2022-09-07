package com.adventure.graphic;

import java.awt.event.*;

import javax.swing.JFrame;

/**
 * Handles the window events.
 */
public class MyWindowAdapter extends WindowAdapter {
    public JFrame myJFrame;

    MyWindowAdapter(JFrame jf) {
        this.myJFrame = jf;
    }
}