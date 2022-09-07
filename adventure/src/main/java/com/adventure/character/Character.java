package com.adventure.character;

import java.util.Map;
import java.util.function.Predicate;
import java.io.Serializable;

import com.adventure.gameEnvironment.Position;
import com.adventure.items.*;

/**
 * It represents game characters.
 */
public class Character implements Serializable {
    private String name;
    private Position pos;
    private CHARACTER_TYPE characterType; // type of person
    private MapContainer<Item> bag; // List of items

    public Character(String nameRec, Position posRec, CHARACTER_TYPE characterTypeRec) {
        this.init(nameRec, posRec, characterTypeRec);
        this.bag = new MapContainer<Item>();
    }

    public Character(String nameRec, Position posRec, CHARACTER_TYPE characterTypeRec, Map<Item, Integer> bagRec) {
        this.init(nameRec, posRec, characterTypeRec);
        this.bag = new MapContainer<Item>(bagRec);
    }

    private void init(String nameRec, Position posRec, CHARACTER_TYPE characterTypeRec) {
        this.setName(nameRec);
        this.setPosition(posRec);
        this.setType(characterTypeRec);
    }

    public void setName(String nameRec) {
        this.name = nameRec;
    }

    public String getName() {
        return this.name;
    }

    public void setType(CHARACTER_TYPE typeRec) {
        this.characterType = typeRec;
    }

    public CHARACTER_TYPE getType() {
        return this.characterType;
    }

    public void setPosition(Position posRec) {
        this.pos = posRec;
    }

    public Position getPosition() {
        return this.pos;
    }

    public Map<Item, Integer> getBag() {
        return this.bag;
    }

    public void pickupItem(Item item) {
        this.bag.add(item);
    }

    public synchronized void releaseItem(Item item) {
        this.bag.remove(item);
    }

    public synchronized void releaseItem(Predicate<Item> pred) {
        for (Map.Entry<Item, Integer> m : this.bag.entrySet()) {
            if (pred.test(m.getKey()) == true)
                this.bag.remove(m.getKey());
        }
    }

    public boolean searchItem(Predicate<Map.Entry<Item, Integer>> pred) {
        for (Map.Entry<Item, Integer> x : this.bag.entrySet())
            if (pred.test(x))
                return true;
        return false;
    }

    public synchronized Item combine(Item itemToCombine, Item combinableItem) {
        if (this.searchItem((x) -> x.getKey().equals(itemToCombine)) &&
                itemToCombine.isCombinable((x) -> x.equals(combinableItem.getType())))
            return itemToCombine.getEntry(combinableItem).getValue();

        else
            return null;
    }

    @Override
    public String toString() {
        return "Character [name=" + name + ", bag=" + bag + ", characterType=" + characterType
                + "pos=" + pos + "]";
    }
}