package com.adventure.gameEnvironment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import com.adventure.rooms.Room;

enum MAP_TYPE {
    UKR, RU
}

/**
 * Represents map game.
 */
public class MapGame implements Serializable {
    private MAP_TYPE mapType;
    final private Set<Room> roomList;
    private String drawPath;

    public MapGame(MAP_TYPE typeRec, Set<Room> roomListRec, String drawPathRec) {
        this.roomList = new HashSet<Room>();
        this.setType(typeRec);
        this.roomList.addAll(roomListRec);
        this.setDrawPath(drawPathRec);
    }

    public MAP_TYPE getType() {
        return this.mapType;
    }

    public void setType(MAP_TYPE typeRec) {
        this.mapType = typeRec;
    }

    public Set<Room> getRooms() {
        return this.roomList;
    }

    public String getDrawPath() {
        return this.drawPath;
    }

    public void setDrawPath(String drawPathRec) {
        this.drawPath = drawPathRec;
    }

    public boolean searchRoom(Predicate<Room> pred) {
        for (Room x : this.roomList)
            if (pred.test(x))
                return true;
        return false;
    }
}