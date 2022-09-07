package com.adventure.items;

public class NoteFour extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.NOTE_FOUR;
    final static public String ImagePath = "./img/items/NoteFour.png";

    public String getDescription() {
        return "Nota4: L'unione fa la forza!";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
