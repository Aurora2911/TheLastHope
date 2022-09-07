package com.adventure;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.character.Character;
import com.adventure.items.*;
import com.adventure.rooms.ROOM_TYPE;
import com.adventure.rooms.Room;

import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void functionalProgrammingRoomClass() {
        Room room = new Room(ROOM_TYPE.ACCOMODATION1, null);
        room.addItem(new Coffee());
        room.addItem(new Jammer());
        room.addItem(new Jammer());

        assertTrue(room.searchItem(
                (i) -> (i.getKey().getType() == ITEM_TYPE.JAMMER) && (i.getValue() == 2)));
    }

    @Test
    public void functionalProgrammingItemClass() {
        Item coffe = new Coffee();
        Item pills = new Pills();

        assertTrue(coffe.isCombinable((x) -> x == Pills.it));
        assertTrue(coffe.isCombinable((x) -> x == new Pills().getType()));
        assertTrue(coffe.isCombinable((x) -> x == pills.getType()));
        assertTrue(coffe.isCombinable((x) -> x == ITEM_TYPE.PILL));

        assertFalse(coffe.isCombinable((x) -> x != ITEM_TYPE.PILL));
    }

    @Test
    public void functionalProgramminCharacterClass() {
        Character c = new Character("Walter", null, CHARACTER_TYPE.PROTAGONIST);

        /**
         * I have a coffee.
         * I should be able to combine it
         */
        c.pickupItem(new Coffee());
        assertNotNull(c.combine(new Coffee(), new Pills()));

        /**
         * A coffee is not combinable with a jammer
         */
        assertNull(c.combine(new Coffee(), new Jammer()));

        /**
         * I don't have more a coffee.
         * I should not be able to combine it
         */
        c.releaseItem(new Coffee());
        assertNull(c.combine(new Coffee(), new Pills()));

        /**
         * Search an item that I don't have
         */
        assertFalse(c.searchItem((x) -> x.getKey().getType() == ITEM_TYPE.COFFEE));

        /**
         * Search an item that I have in number
         */
        c.pickupItem(new Coffee());
        c.pickupItem(new Coffee());
        assertTrue(c.searchItem((x) -> x.getKey().getType() == ITEM_TYPE.COFFEE && x.getValue() == 2));

        /**
         * Search an item that I don't have in number
         */
        assertFalse(c.searchItem((x) -> x.getKey().getType() == ITEM_TYPE.COFFEE && x.getValue() == 3));
    }
}