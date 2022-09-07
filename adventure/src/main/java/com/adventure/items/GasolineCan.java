package com.adventure.items;

import java.util.HashMap;

public class GasolineCan extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.GASOLINE_CAN;
    final static public String ImagePath = "./img/items/GasolineCan.png";

    public GasolineCan() {
        this.addCombinableItems(new HashMap<ITEM_TYPE, Item>() {
            {
                put(Matches.it, new Fire());
            }
        });
    }

    public String getDescription() {
        return "Tanica di benzina: combinabile con i fiammiferi. \n Attenzione: raccogliere tutti gli oggetti prima di dar fuoco alla stanza!";
    }

    public void use() {
        System.out.println("I'm using a gasolineCan");
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
