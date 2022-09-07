package com.adventure.error;

import javax.swing.JOptionPane;

/**
 * Displays the fatal error message in case of error.
 */
public class Fatal {
    static public void log(String what) {
        JOptionPane.showMessageDialog(
                null,
                "Fatal error! Cause: " + what,
                "Fatal error!",
                JOptionPane.ERROR_MESSAGE);

        System.exit(1);
    }

    static public void errorHandle(String what) {
        JOptionPane.showMessageDialog(
                null,
                "Error! Cause: " + what,
                "Error!",
                JOptionPane.ERROR_MESSAGE);
    }
}
