package com.adventure.items;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class RedCard extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.RED_CARD;
    final static public String ImagePath = "./img/items/RedCard.png";

    public String getDescription() {
        return "Tessera rossa: consente di accedere alla sala operativa.";
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
