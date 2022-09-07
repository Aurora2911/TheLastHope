package com.adventure.graphic;

import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.items.Item;

/**
 * Reacts to the Combo box event/choices for the items in the room.
 */
public class ItemsInRoomActionListener extends MyActionListeners {
    public ItemsInRoomActionListener(InitialFrame jf) {
        super(jf);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (-1 != this.myJFrame.getMyComboBox().get(InitialFrame.ROOM_COMBO_BOX_TITLE).getSelectedIndex()) {
            AbstractMap.SimpleEntry<Item, Integer> am = (SimpleEntry<Item, Integer>) this.myJFrame.getMyComboBox()
                    .get(InitialFrame.ROOM_COMBO_BOX_TITLE).getSelectedItem();

            this.myJFrame.setEnabled(false);

            new MyChoiceFrame(this.myJFrame, am.getKey()) {

                @Override
                public void draw() {
                    final String pickup = "Raccogli";
                    final String describe = "Descrizione";

                    Map<String, JButton> buttons = new HashMap<>();
                    JPanel jpn1 = new JPanel();
                    JPanel jpn2 = new JPanel();

                    this.setLayout(new BorderLayout());

                    jpn1.setLayout(new BorderLayout());
                    buttons.put(pickup, new JButton(pickup));
                    jpn1.add(buttons.get(pickup), BorderLayout.NORTH);
                    this.add(jpn1, BorderLayout.CENTER);

                    jpn2.setLayout(new BorderLayout());
                    buttons.put(describe, new JButton(describe));
                    jpn2.add(buttons.get(describe), BorderLayout.NORTH);
                    this.add(jpn2, BorderLayout.NORTH);

                    buttons.get(pickup).addActionListener(
                            new MyActionListenerOnObject<Item>((InitialFrame) this.getMyJFrame(), getMyObj(), this) {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (this.myJFrame.getCurrentGame().getCurrentRoom()
                                            .searchItem(((k) -> k.getKey().equals(getMyObj())))) {
                                        this.myJFrame.getCurrentGame().getCurrentRoom().removeItem(getMyObj());
                                        this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST)
                                                .pickupItem(getMyObj());
                                    }
                                    this.myJFrame.dispatcher();
                                    this.myJFrame.setEnabled(true);
                                    this.getMyChoiceFrame().dispose();
                                }
                            });

                    buttons.get(describe).addActionListener(
                            new MyActionListenerOnObjectDescribe((InitialFrame) this.getMyJFrame(), getMyObj(), this));
                }
            }.create();
        }
    }
}