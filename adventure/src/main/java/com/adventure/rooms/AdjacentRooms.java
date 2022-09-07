package com.adventure.rooms;

import java.io.Serializable;

/**
 * Represents room's adjacent rooms.
 */
public class AdjacentRooms implements Serializable {
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public AdjacentRooms() {
        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
    }

    public AdjacentRooms(Room northRec, Room southRec, Room eastRec, Room westRec) {
        this.north = northRec;
        this.south = southRec;
        this.east = eastRec;
        this.west = westRec;
    }

    public Room getNorth() {
        return this.north;
    }

    public Room getSouth() {
        return this.south;
    }

    public Room getEast() {
        return this.east;
    }

    public Room getWest() {
        return this.west;
    }

    public void setNorth(Room northRec) {
        this.north = northRec;
    }

    public void setSouth(Room southRec) {
        this.south = southRec;
    }

    public void setEast(Room eastRec) {
        this.east = eastRec;
    }

    public void setWest(Room westRec) {
        this.west = westRec;
    }
}
