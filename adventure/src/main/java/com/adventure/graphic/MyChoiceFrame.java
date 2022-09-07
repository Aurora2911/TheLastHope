package com.adventure.graphic;

import javax.swing.JFrame;

import com.adventure.items.Item;

/**
 * Similar to ChoiceFrame.
 */
abstract class MyChoiceFrame extends ChoiceFrame {
    private Item myObj;

    public MyChoiceFrame(JFrame jf, Item obj) {
        super(jf);
        this.myObj = obj;
    }

    public Item getMyObj() {
        return myObj;
    }
}
