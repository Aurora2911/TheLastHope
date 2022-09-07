package com.adventure.items;

public class NoteOne extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.NOTE_ONE;
    final static public String ImagePath = "./img/items/NoteOne.png";

    public String getDescription() {
        return "Nota1: 1---";
    }
    
    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
