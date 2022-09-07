package com.adventure.items;

import java.util.HashMap;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class Pills extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.PILL;
    final static public String ImagePath = "./img/items/Pills.png";

    public Pills() {
        this.addCombinableItems(new HashMap<ITEM_TYPE, Item>() {
            {
                put(ITEM_TYPE.COFFEE, new SleepingCoffee());
            }
        });
    }

    public String getDescription() {
        return "Sonnifero: combinabile con un liquido.";
    }

    @Override
    public Boolean here(ROOM_TYPE rt) {
        return true;
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }

    @Override
    public void use(InitialFrame frameRec) {
    }
}
