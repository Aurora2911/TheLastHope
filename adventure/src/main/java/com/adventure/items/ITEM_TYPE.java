package com.adventure.items;

public enum ITEM_TYPE {
    USB, HAMMER, JAMMER, YELLOW_CARD, RED_CARD, GASOLINE_CAN, MATCHES, NOTE_ONE, NOTE_TWO, NOTE_THREE, NOTE_FOUR,
    PLIERS,
    COFFEE, PILL, SLEEPING_COFFEE, FIRE;

    static public boolean isLong(ITEM_TYPE i) {
        return (ITEM_TYPE.JAMMER == i || ITEM_TYPE.YELLOW_CARD == i || ITEM_TYPE.RED_CARD == i
                || ITEM_TYPE.NOTE_ONE == i || ITEM_TYPE.PLIERS == i
                || ITEM_TYPE.NOTE_TWO == i || ITEM_TYPE.NOTE_THREE == i || ITEM_TYPE.NOTE_FOUR == i);
    }
}