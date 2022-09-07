package com.adventure.items;

import java.util.HashMap;

public class Coffee extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.COFFEE;
    final static public String ImagePath = "./img/items/Coffee.png";

    public Coffee() {
        this.addCombinableItems(new HashMap<ITEM_TYPE, Item>() {
            {
                put(Pills.it, new SleepingCoffee());
            }
        });
    }

    public String getDescription() {
        return "Caffe': Ottenuto alla macchinetta.";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    @Override
    public String getPath() {
        return ImagePath;
    }
}
