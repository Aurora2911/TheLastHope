package com.adventure.rooms;

import java.util.Map;
import java.util.function.Predicate;
import java.io.Serializable;
import java.util.Objects;

import com.adventure.items.Item;
import com.adventure.items.MapContainer;

/**
 * Represents a room.
 */
public class Room implements Serializable {
    private ROOM_TYPE rt;
    private MapContainer<Item> items;
    private AdjacentRooms nearRooms;
    private String drawPath;
    private Boolean bLocked;
    private Boolean escapable;

    public Room(ROOM_TYPE typeRec, String drawPathRec) {
        this.init(typeRec, null, drawPathRec);
        this.items = new MapContainer<>();
    }

    public Room(ROOM_TYPE typeRec, AdjacentRooms adRoomsRec, String drawPathRec) {
        this.init(typeRec, adRoomsRec, drawPathRec);
        this.items = new MapContainer<>();
    }

    public Room(ROOM_TYPE typeRec, MapContainer<Item> itemsRec, AdjacentRooms adRoomsRec, String drawPathRec) {
        this.init(typeRec, adRoomsRec, drawPathRec);
        this.items = new MapContainer<>(itemsRec);
    }

    public Room(ROOM_TYPE typeRec, MapContainer<Item> itemsRec, String drawPathRec) {
        this.init(typeRec, null, drawPathRec);
        this.items = new MapContainer<Item>(itemsRec);
    }

    private void init(ROOM_TYPE typeRec, AdjacentRooms adRoomsRec, String drawPathRec) {
        this.setDrawPath(drawPathRec);
        this.setType(typeRec);
        this.nearRooms = adRoomsRec;
        this.unlock();
        this.setEscapable(false);
    }

    public void addItem(Item itemRec) {
        this.items.add(itemRec);
    }

    public Integer removeItem(Item itemRec) {
        return this.items.remove(itemRec);
    }

    public ROOM_TYPE getType() {
        return this.rt;
    }

    public void setType(ROOM_TYPE typeRec) {
        this.rt = typeRec;
    }

    public boolean searchItem(Predicate<Map.Entry<Item, Integer>> pred) {
        for (Map.Entry<Item, Integer> x : this.items.entrySet())
            if (pred.test(x))
                return true;
        return false;
    }

    public MapContainer<Item> getItems() {
        return this.items;
    }

    public AdjacentRooms getNearRooms() {
        return this.nearRooms;
    }

    public void setNearRooms(AdjacentRooms nearRoomsRec) {
        this.nearRooms = nearRoomsRec;
    }

    public void setNearRooms(Room north, Room south, Room east, Room west) {
        this.setNearRooms(new AdjacentRooms(north, south, east, west));
    }

    public String getDrawPath() {
        return this.drawPath;
    }

    public void setDrawPath(String drawPathRec) {
        this.drawPath = drawPathRec;
    }

    public final void lock() {
        this.bLocked = true;
    }

    public final void unlock() {
        this.bLocked = false;
    }

    public final Boolean isLocked() {
        return this.bLocked;
    }

    public String getDescription() {
        return ROOM_TYPE.getDescription(rt);
    }

    public Boolean isEscapable() {
        return this.escapable;
    }

    public void setEscapable(Boolean newValue) {
        this.escapable = newValue;
    }

    @Override
    final public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.rt);
        return hash;
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Room other = (Room) obj;
        return this.rt == other.rt;
    }

    @Override
    public String toString() {
        return "Room [rt=" + rt + "]";
    }
}