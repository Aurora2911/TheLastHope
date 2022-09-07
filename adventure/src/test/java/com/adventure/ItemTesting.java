package com.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.adventure.items.Fire;
import com.adventure.items.ITEM_TYPE;
import com.adventure.items.Item;
import com.adventure.items.Matches;

public class ItemTesting {
    @Test
    public void GetCombinablePair() {
        Item i = null;
        i = new Matches();

        assertEquals(new Fire(), i.combine(ITEM_TYPE.GASOLINE_CAN));
        assertNull(i.combine(ITEM_TYPE.COFFEE));
    }
}
