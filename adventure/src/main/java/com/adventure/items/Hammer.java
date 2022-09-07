package com.adventure.items;

import javax.swing.JOptionPane;

import com.adventure.gameEnvironment.WorldState;
import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class Hammer extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.HAMMER;
    final static public String ImagePath = "./img/items/Hammer.png";

    public String getDescription() {
        return "Hammer: Consente di rompere qualcosa.";
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }

    @Override
    public Boolean here(ROOM_TYPE rt) {
        return rt.equals(ROOM_TYPE.BASEMENT);
    }

    @Override
    public void use(InitialFrame frameRec) {
        if (frameRec.getCurrentGame().getState().equals(WorldState.COMANDER_LIBERATION)) {
            JOptionPane.showMessageDialog(null,
                    "Una stanza piena di server. Questo era il rifugio russo.\nEra proprio quello che ci serviva."
                            +
                            "\nIn fondo, i migliori hackers al mondo sono russi! Battiamoli in casa loro!",
                    "Finalmente!",
                    JOptionPane.INFORMATION_MESSAGE);
            frameRec.getCurrentGame().getRoom(ROOM_TYPE.SAFE).unlock();
            frameRec.getCurrentGame().setState(WorldState.HACK);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Oops. Non riesco a capire come utilizzare il martello. Non ne vedo alcuna utilità!",
                    "Non so cosa fare!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}