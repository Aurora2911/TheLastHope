package com.adventure.graphic;

import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.items.Item;
import com.adventure.items.UsableItem;

/**
 * Reacts to the Combo box event/choices for the items in bag.
 */
public class ItemsInBagActionListener extends MyActionListeners {
        public MyChoiceFrame cf;

        public ItemsInBagActionListener(InitialFrame jf) {
                super(jf);
                cf = null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (-1 != this.myJFrame.getMyComboBox().get(InitialFrame.BAG_COMBO_BOX_TITLE).getSelectedIndex()) {
                        @SuppressWarnings("unchecked")
                        AbstractMap.SimpleEntry<Item, Integer> am = (SimpleEntry<Item, Integer>) this.myJFrame.getMyComboBox().get(InitialFrame.BAG_COMBO_BOX_TITLE).getSelectedItem();

                        this.myJFrame.setEnabled(false);

                        ItemsInBagActionListener.this.cf = new MyChoiceFrame(this.myJFrame, am.getKey()) {

                                final String release = "Rilascia";
                                final String describe = "Descrizione";
                                final String use = "Usa";
                                final String combine = "Combina";

                                Map<String, JButton> buttons = new HashMap<>();

                                public JButton releaseBtn() {
                                        buttons.put(release, new JButton(release));

                                        buttons.get(release).addActionListener(new MyActionListenerOnObject<Item>((InitialFrame) this.getMyJFrame(), getMyObj(),this) {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                        if (this.myJFrame.getCurrentGame().getBagByCharacter(CHARACTER_TYPE.PROTAGONIST).containsKey((getMyObj()))) {
                                                                this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).releaseItem(getMyObj());
                                                                this.myJFrame.getCurrentGame().getCurrentRoom().addItem(getMyObj());
                                                        }

                                                        this.myJFrame.dispatcher();
                                                        this.myJFrame.setEnabled(true);
                                                        this.getMyChoiceFrame().dispose();
                                                }
                                        });

                                        return this.buttons.get(release);
                                }

                                public JButton combineBtn() {
                                        buttons.put(combine, new JButton(combine));
                                        buttons.get(combine).addActionListener(new MyActionListenerOnObject<Item>((InitialFrame) this.getMyJFrame(), getMyObj(),this) {

                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                        new ChoiceFrame(ItemsInBagActionListener.this.cf) {

                                                                @Override
                                                                public void draw() {
                                                                        this.setLayout(new GridLayout());
                                                                        

                                                                        ItemsInBagActionListener.this.cf.getMyObj().getCombinableItems().entrySet().stream().forEach((r) -> {
                                                                                JButton jb = new JButton(r.getKey().toString());

                                                                                jb.addActionListener(new CombineActionListener(new AbstractMap.SimpleEntry<>(r)) {
                                                                                        @Override
                                                                                        public void actionPerformed(ActionEvent e) {

                                                                                                if (ItemsInBagActionListener.this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).searchItem((v) -> v.getKey().getType() == r.getKey()) == false)
                                                                                                        JOptionPane.showMessageDialog(null, "Non possiedi un oggetto di tipo " + r.getKey(), "Error", JOptionPane.ERROR_MESSAGE);
                                                                                                else {
                                                                                                        synchronized (ItemsInBagActionListener.this.myJFrame.getCurrentGame().getBagByCharacter(CHARACTER_TYPE.PROTAGONIST)) {
                                                                                                                ItemsInBagActionListener.this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).pickupItem(getMyObj().combine(r.getKey()));

                                                                                                                ItemsInBagActionListener.this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).releaseItem((i) -> i.getType() == r.getKey());

                                                                                                                ItemsInBagActionListener.this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).releaseItem((x) -> x.equals(getMyObj()));

                                                                                                                JOptionPane.showMessageDialog(null,"Oggetti combinati correttamente!","Error",JOptionPane.INFORMATION_MESSAGE);

                                                                                                                ItemsInBagActionListener.this.myJFrame.dispatcher();
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                });

                                                                                this.add(jb);
                                                                        });
                                                                }
                                                        }.create();
                                                }
                                        });
                                        
                                        return this.buttons.get(combine);
                                }

                                JButton describeBtn() {
                                        buttons.put(describe, new JButton(describe));
                                        buttons.get(describe).addActionListener(new MyActionListenerOnObjectDescribe((InitialFrame) this.getMyJFrame(), getMyObj(),this));
                                        return buttons.get(describe);
                                }

                                @Override
                                public void draw() {

                                        JPanel jpn1 = new JPanel();
                                        JPanel jpn2 = new JPanel();

                                        this.setLayout(new BorderLayout());

                                        jpn1.setLayout(new BorderLayout());
                                        jpn1.add(this.releaseBtn(), BorderLayout.NORTH);
                                        this.add(jpn1, BorderLayout.CENTER);

                                        jpn2.setLayout(new BorderLayout());
                                        jpn2.add(describeBtn(), BorderLayout.NORTH);
                                        this.add(jpn2, BorderLayout.NORTH);

                                        jpn2.add(this.combineBtn(), BorderLayout.CENTER);

                                        if (getMyObj() instanceof UsableItem) {
                                                buttons.put(use, new JButton(use));
                                                jpn2.add(buttons.get(use), BorderLayout.SOUTH);

                                                buttons.get(use).addActionListener(new MyActionListenerOnObject<Item>((InitialFrame) this.getMyJFrame(), getMyObj(), this) {

                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                                Item i = getMyObj();

                                                                myJFrame.setEnabled(true);
                                                                cf.dispose();

                                                                if (this.myJFrame.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).searchItem((y) -> y.getKey().equals(i))) {
                                                                        UsableItem ui = (UsableItem) getMyObj();
                                                                        if (ui.here((this.myJFrame.getCurrentGame().getCurrentRoom().getType()))) {
                                                                                ui.use(this.myJFrame);

                                                                        } else {
                                                                                JOptionPane.showMessageDialog(null,"Non puoi utilizzare quest'oggetto qua!","No!",JOptionPane.ERROR_MESSAGE);
                                                                        }

                                                                        this.myJFrame.dispatcher();
                                                                } else {
                                                                        JOptionPane.showMessageDialog(null,"Non possiedi quest'oggetto!","Ooops!",JOptionPane.ERROR_MESSAGE);
                                                                }

                                                        }
                                                });
                                        }
                                }
                        };

                        cf.create();
                }
        }
}