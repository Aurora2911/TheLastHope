package com.adventure.graphic;

import java.awt.event.*;
import javax.swing.JOptionPane;

import com.adventure.error.Fatal;

/**
 * Input events handler.
 */
public class MyKeyListener implements KeyListener {
    InitialFrame obj;

    MyKeyListener(InitialFrame i) {
        this.obj = i;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                this.obj.getCurrentGame().save();

                JOptionPane.showMessageDialog(
                        null,
                        "Partita salvata!",
                        "Salvataggio riuscito!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception exc) {
            Fatal.errorHandle(exc.getMessage());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}