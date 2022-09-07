package com.adventure.items;

import java.util.HashMap;

public class Matches extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.MATCHES;
    final static public String ImagePath = "./img/items/Matches.png";

    public Matches() {
        this.addCombinableItems(new HashMap<ITEM_TYPE, Item>() {
            {
                put(GasolineCan.it, new Fire());
            }
        });
    }

    public String getDescription() {
        return "Fiammiferi: combinabili con la tanica di benzina. \n Attenzione: raccogliere tutti gli oggetti prima di dar fuoco alla stanza!";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
