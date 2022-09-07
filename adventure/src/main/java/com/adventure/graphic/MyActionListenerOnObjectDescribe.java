package com.adventure.graphic;

import java.awt.event.ActionEvent;
import com.adventure.items.Item;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.*;

/**
 * Reacts to the item describe event/choice, showing it.
 */
public class MyActionListenerOnObjectDescribe extends MyActionListenerOnObject<Item> {
    public MyActionListenerOnObjectDescribe(InitialFrame jf, Item obj, ChoiceFrame cf) {
        super(jf, obj, cf);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocation(500, 250);

        JPanel jpnl = new JPanel();
        jpnl.setLayout(new BorderLayout());

        JTextArea jtxt = new JTextArea(getMyObj().getDescription());
        jtxt.setBounds(SwingConstants.NORTH, SwingConstants.NORTH, 100, 50);
        jtxt.setEditable(false);
        jtxt.setFont(new Font("Dialog", Font.BOLD, 18));

        JScrollPane scroll = new JScrollPane(jtxt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        jtxt.setEditable(false);

        jtxt.setBackground(Color.white);

        jtxt.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 20));

        jtxt.setLineWrap(true);

        jtxt.setWrapStyleWord(true);

        jpnl.add(scroll, BorderLayout.NORTH);
        frame.add(jpnl);

        frame.setVisible(true);

        JLabel jlbl2 = new JLabel(new ImageIcon(getMyObj().getPath()));
        jpnl.add(jlbl2, BorderLayout.CENTER);

    }
}