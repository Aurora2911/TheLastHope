package com.adventure.items;

public class NoteThree extends Item {
    final static public ITEM_TYPE it = ITEM_TYPE.NOTE_THREE;
    final static public String ImagePath = "./img/items/NoteThree.png";

    public String getDescription() {
        return "Nota3: --3-";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }
}
