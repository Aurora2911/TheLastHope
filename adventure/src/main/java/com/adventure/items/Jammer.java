package com.adventure.items;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class Jammer extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.JAMMER;
    final static public String ImagePath = "./img/items/Jammer.png";

    public String getDescription() {
        return "Jammer: Consente di disattivare le telecamere nella stanza di sorveglianza.";
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
