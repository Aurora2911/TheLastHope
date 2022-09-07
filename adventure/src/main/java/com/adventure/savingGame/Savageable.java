package com.adventure.savingGame;

/**
 * Every class that implements Savagable interface has
 * a mechanism to save its status somewhere.
 */
@FunctionalInterface
public interface Savageable {
    public void save();
}
