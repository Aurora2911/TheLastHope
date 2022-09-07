package com.adventure.items;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class SleepingCoffee extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.SLEEPING_COFFEE;
    final static public String ImagePath = "./img/items/CoffeeSpecial.png";

    public String getDescription() {
        return "Caffe' con sonnifero: Addormenta chi lo beve.";
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
