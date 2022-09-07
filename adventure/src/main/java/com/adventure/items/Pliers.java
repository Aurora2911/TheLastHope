package com.adventure.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.adventure.database.DbUtility;
import com.adventure.database.H2Utility;
import com.adventure.gameEnvironment.WorldState;
import com.adventure.graphic.InitialFrame;
import com.adventure.graphic.MyActionListeners;
import com.adventure.rooms.ROOM_TYPE;

public class Pliers extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.PLIERS;
    final static public String ImagePath = "./img/items/Pliers.png";

    public String getDescription() {
        return "Pinze: Consentono di tagliare o spezzare qualcosa.";
    }

    @Override
    public Boolean here(ROOM_TYPE rt) {
        return rt.equals(ROOM_TYPE.BASEMENT);
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }

    private void decision(InitialFrame frameRec) {
        JDialog jd1 = new JDialog(frameRec, "MyWindow");
        String topic = null;
        ResultSet rs = null;
        DbUtility db = new H2Utility();
        Properties myProperties = new Properties();
        JPanel jpanel = new JPanel();
        JScrollPane scroll = null;
        JTextArea jta = null;
        JButton escape = new JButton("Scappa");
        JButton stay = new JButton("Rimani");

        try {
            myProperties.setProperty("user", "user");
            myProperties.setProperty("password", "1234");
            db.openDbConnection("jdbc:h2:./MAP/db/prova", myProperties);
            rs = db.readFromDb("SELECT Text FROM ComanderDialogs WHERE CharacterMoment = 'C[1]'");
            topic = db.getText(rs);

            jta = new JTextArea(topic);
            jta.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 20));
            jta.setLineWrap(true);
            jta.setWrapStyleWord(true);
            jta.setEditable(false);
            jta.setBackground(Color.WHITE);

            scroll = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setMinimumSize(new Dimension(400, 200));
            scroll.setMaximumSize(new Dimension(400, 200));
            scroll.setPreferredSize(new Dimension(400, 200));

            escape.addActionListener(new MyActionListeners(frameRec) {

                @Override
                public void actionPerformed(ActionEvent e) {

                    DbUtility database = new H2Utility();
                    ResultSet result = null;
                    String query = null;
                    String text = null;

                    query = "SELECT Text FROM Cutscene WHERE descr = 'Outro'";

                    try {
                        myProperties.setProperty("user", "user");
                        myProperties.setProperty("password", "1234");
                        database.openDbConnection("jdbc:h2:./MAP/db/prova", myProperties);

                        result = database.readFromDb(query);
                        text = database.getText(result);
                        frameRec.setEnabled(true);
                        frameRec.outro(text);
                        jd1.dispose();

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            stay.addActionListener(new MyActionListeners(frameRec) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameRec.setEnabled(true);
                    jd1.dispose();
                }
            });

            jd1.setResizable(false);
            jd1.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            jd1.pack();
            jd1.setSize(400, 290);
            jd1.setLocation(300, 200);
            jpanel.setLayout(new BorderLayout());
            jpanel.add(escape, BorderLayout.SOUTH);
            jpanel.add(stay, BorderLayout.CENTER);
            jpanel.add(scroll, BorderLayout.NORTH);

            jta.setVisible(true);
            jd1.add(jpanel, BorderLayout.NORTH);
            jd1.setVisible(true);
            frameRec.setEnabled(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void use(InitialFrame frameRec) {
        if (frameRec.getCurrentGame().getState().equals(WorldState.INITIAL)) {
            JOptionPane.showMessageDialog(null,
                    "Hai liberato il comandante!",
                    "Finalmente!",
                    JOptionPane.INFORMATION_MESSAGE);

            this.decision(frameRec);

            frameRec.getCurrentGame().getRoom(ROOM_TYPE.ENTRANCE).unlock();
            frameRec.getCurrentGame().getRoom(ROOM_TYPE.BASEMENT).setDrawPath("./img/imgUkr/Basement2.jpg");
            frameRec.getCurrentGame().setState(WorldState.COMANDER_LIBERATION);

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Oops. Non riesco a capire come utilizzare le pinze. Non ne vedo alcuna utilità!",
                    "Non so cosa fare!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}