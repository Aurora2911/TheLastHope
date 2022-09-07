package com.adventure.graphic;

import java.awt.Dimension;

import javax.swing.plaf.basic.BasicArrowButton;

/**
 * Allows movement between rooms.
 */
public class DirectionalButton {
    private BasicArrowButton up, down, right, left;

    public BasicArrowButton getUp() {
        return up;
    }

    public BasicArrowButton makeUp() {
        setUp(makeButton(BasicArrowButton.NORTH));
        return getUp();
    }

    public void setUp(BasicArrowButton up) {
        this.up = up;
    }

    public BasicArrowButton getDown() {
        return down;
    }

    public BasicArrowButton makeDown() {
        setDown(makeButton(BasicArrowButton.SOUTH));
        return getDown();
    }

    public void setDown(BasicArrowButton down) {
        this.down = down;
    }

    public BasicArrowButton getRight() {
        return right;
    }

    public BasicArrowButton makeRight() {
        setRight(makeButton(BasicArrowButton.EAST));
        return getRight();
    }

    public void setRight(BasicArrowButton right) {
        this.right = right;
    }

    public BasicArrowButton getLeft() {
        return left;
    }

    public BasicArrowButton makeLeft() {
        setLeft(makeButton(BasicArrowButton.WEST));
        return getLeft();
    }

    public void setLeft(BasicArrowButton left) {
        this.left = left;
    }

    public BasicArrowButton makeButton(int versus) {
        return new BasicArrowButton(versus) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(30, 30);
            }
        };
    }
}