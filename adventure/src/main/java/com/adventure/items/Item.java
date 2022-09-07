package com.adventure.items;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents an object.
 */
public abstract class Item implements Serializable {
    private Map<ITEM_TYPE, Item> combinable = new HashMap<>();

    abstract public String getDescription();

    abstract public ITEM_TYPE getType();

    abstract public String getPath();

    protected void addCombinableItems(Map<ITEM_TYPE, Item> itemsRec) {
        this.combinable.putAll(itemsRec);
    }

    public Map.Entry<ITEM_TYPE, Item> getEntry(Item itemCombRec) throws IllegalArgumentException {
        for (Map.Entry<ITEM_TYPE, Item> entryMap : combinable.entrySet()) {
            if (entryMap.getKey().equals(itemCombRec.getType()))
                return entryMap;
        }
        return null;
    }

    public boolean isCombinable(Predicate<ITEM_TYPE> pred) {
        return combinable.entrySet().stream().anyMatch((x) -> pred.test(x.getKey()));
    }

    public Map<ITEM_TYPE, Item> getCombinableItems() {
        return this.combinable;
    }

    @Override
    final public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.getType());
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
        final Item other = (Item) obj;
        return this.getType() == other.getType();
    }

    @Override
    final public String toString() {
        return this.getType().toString();
    }

    public Item combine(ITEM_TYPE otherItem) {
        return this.combinable.get(otherItem);
    }
}