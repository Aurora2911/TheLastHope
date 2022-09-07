package com.adventure.items;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Used as a container for objects
 * Map contains two values:
 * - An object of type T
 * - Number of these objects in container
 * Please note that this class extends ConcurrentHashMap in order to allow
 * concurrent access.
 */
public class MapContainer<K> extends ConcurrentHashMap<K, Integer> {
    public MapContainer() {
        super();
    }

    public MapContainer(Map<K, Integer> m) throws IllegalArgumentException {
        super(m);
        m.forEach((k, v) -> {
            try {
                if (v <= 0)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArgumentException");
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer remove(Object key) {
        if (this.containsKey(key))
            if (this.get(key) > 1)
                return this.put((K) key, this.get(key) - 1);
            else
                return super.remove(key);
        return null;
    }

    public Integer add(K key) {
        if (this.containsKey(key))
            return super.put(key, this.get(key) + 1);
        else
            return super.put(key, 1);
    }

    @Override
    public Integer put(K key, Integer value) throws IllegalArgumentException {
        if (value <= 0)
            throw new IllegalArgumentException();
        return super.put(key, value);
    }
}