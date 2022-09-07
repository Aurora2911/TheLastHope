package com.adventure.graphic;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Adds a title to the Combo Boxes.
 */
public class MyListCellRenderer<T> extends JLabel implements ListCellRenderer<T> {
    private String myTitle;

    MyListCellRenderer(String title) {
        this.myTitle = title;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends T> list,
            T value, int index,
            boolean isSelected,
            boolean cellHasFocus) {
        if (index == -1 && value == null)
            this.setText(this.myTitle);
        else
            setText(value.toString());

        return this;
    }

}
