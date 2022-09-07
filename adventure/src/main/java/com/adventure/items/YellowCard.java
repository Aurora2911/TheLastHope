package com.adventure.items;

import java.util.ArrayList;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class YellowCard extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.YELLOW_CARD;
    final static public String ImagePath = "./img/items/YellowCard.png";

    ArrayList<ROOM_TYPE> validRoomsForUsage;

    public String getDescription() {
        return "Tessera gialla: Consente di accedere alla stanza dell'ufficiale.";
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
