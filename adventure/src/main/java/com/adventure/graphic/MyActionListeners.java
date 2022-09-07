package com.adventure.graphic;

import java.awt.event.*;

/**
 * Labels handler.
 */
public abstract class MyActionListeners implements ActionListener {
    InitialFrame myJFrame;

    public InitialFrame getMyJFrame() {
        return myJFrame;
    }

    public MyActionListeners(InitialFrame jf) {
        myJFrame = jf;
    }
}