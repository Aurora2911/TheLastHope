package com.adventure.gameEnvironment;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

import com.adventure.rooms.Room;

class Pair {
    static <F, S> Map.Entry<F, S> of(F first, S second) {
        return new AbstractMap.SimpleEntry<F, S>(first, second);
    }
}

/**
 * Keeps trace of the position of an item or a character.
 */
public class Position implements Serializable {
    private MapGame map;
    private Room room;

    public Position(MapGame mapRec, Room roomRec) throws IllegalArgumentException {
        this.set(mapRec, roomRec);
    }

    public Map.Entry<MapGame, Room> get() {
        return Pair.of(map, room);
    }

    public void set(MapGame mapRec, Room roomRec) throws IllegalArgumentException {
        this.map = mapRec;
        this.room = roomRec;
        if (RulesPosition.IsValidPosition(mapRec, roomRec) == false)
            throw new IllegalArgumentException();
    }

    public void setMap(MapGame mapRec) throws IllegalArgumentException {
        this.map = mapRec;
        if (RulesPosition.IsValidPosition(this.getMap(), this.getRoom()) == false)
            throw new IllegalArgumentException();
    }

    public MapGame getMap() {
        return this.map;
    }

    public void setRoom(Room roomRec) {
        this.room = roomRec;
        if (RulesPosition.IsValidPosition(this.getMap(), this.getRoom()) == false)
            throw new IllegalArgumentException();
    }

    public Room getRoom() {
        return this.room;
    }
}