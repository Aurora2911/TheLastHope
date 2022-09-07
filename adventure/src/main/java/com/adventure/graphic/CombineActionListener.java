package com.adventure.graphic;

import com.adventure.items.ITEM_TYPE;
import com.adventure.items.Item;

import java.awt.event.ActionListener;
import java.util.AbstractMap;

/**
 * Reacts to a combine event.
 */
public abstract class CombineActionListener implements ActionListener {
    private AbstractMap.SimpleEntry<ITEM_TYPE, Item> myEntry;

    public AbstractMap.SimpleEntry<ITEM_TYPE, Item> getMyItem() {
        return myEntry;
    }

    CombineActionListener(AbstractMap.SimpleEntry<ITEM_TYPE, Item> otherItem) {
        this.myEntry = otherItem;
    }
}
