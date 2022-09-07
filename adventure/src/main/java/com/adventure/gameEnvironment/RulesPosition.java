package com.adventure.gameEnvironment;

import com.adventure.rooms.Room;

/**
 * Checks if a room belongs to specific map.
 */
public class RulesPosition {
    static public boolean IsValidPosition(MapGame mapRec, Room typeRec) {
        return mapRec.getRooms().contains(typeRec);
    }
}