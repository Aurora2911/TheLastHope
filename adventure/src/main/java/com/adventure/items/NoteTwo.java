package com.adventure.items;

public class NoteTwo extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.NOTE_TWO;
    final static public String ImagePath = "./img/items/NoteTwo.png";

    public String getDescription() {
        return "Nota2: -7--";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
