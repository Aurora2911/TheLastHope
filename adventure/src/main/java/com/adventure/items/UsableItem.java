package com.adventure.items;

import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

/**
 * Represents a usable item.
 */
public abstract class UsableItem extends Item {
    /**
     * It indicates where it is possible use this item
     */
    abstract public Boolean here(ROOM_TYPE rt);

    /**
     * It describes what it did after has been used
     */
    abstract public void use(InitialFrame myframe);
}