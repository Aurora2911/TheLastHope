package com.adventure.items;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.adventure.database.DbUtility;
import com.adventure.database.H2Utility;
import com.adventure.error.Fatal;
import com.adventure.gameEnvironment.WorldState;
import com.adventure.graphic.InitialFrame;
import com.adventure.net.MyClient;
import com.adventure.rooms.ROOM_TYPE;
import java.awt.event.*;

public class Usb extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.USB;
    final static public String ImagePath = "./img/items/usb.png";
    JTextArea myJta;

    public String getDescription() {
        return "Chiavetta Usb: Una chiavetta misteriosa che i russi hanno lasciato cadere durante la fuga. A cosa potrebbe servire?";
    }

    @Override
    public Boolean here(ROOM_TYPE rt) {
        return rt.equals(ROOM_TYPE.SAFE);
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }

    void showChallenge(InitialFrame frame) {
        JDialog jd = new JDialog(frame, "CrackMe");
        JPanel jpn = new JPanel();
        JLabel jlb = new JLabel();
        JButton enter = new JButton("Invia");
        this.myJta = new JTextArea("");

        jlb.setSize(1400, 900);
        jlb.setIcon(new ImageIcon(new ImageIcon("Hackme.png").getImage().getScaledInstance(jlb.getWidth(),
                jlb.getHeight(), Image.SCALE_SMOOTH)));

        this.myJta.setEditable(true);
        this.myJta.setToolTipText("Inserisci la chiave segreta!");
        this.myJta.addCaretListener(null);
        jpn.setLayout(new BorderLayout());

        enter.addActionListener((e) -> {
            try {
                Boolean b = (Boolean) new MyClient(this.myJta.getText()).serverHandler();
                if (b.equals(true)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Password corretta!",
                            "Ben fatto!",
                            JOptionPane.INFORMATION_MESSAGE);

                    DbUtility database = new H2Utility();
                    ResultSet result = null;
                    String query = null;
                    String text = null;
                    Properties myProperties = new Properties();

                    query = "SELECT Text FROM Cutscene WHERE descr = 'SpecialOutro'";

                    try {
                        myProperties.setProperty("user", "user");
                        myProperties.setProperty("password", "1234");
                        database.openDbConnection("jdbc:h2:./MAP/db/prova", myProperties);
                        result = database.readFromDb(query);
                        text = database.getText(result);
                        frame.setEnabled(true);
                        frame.outro(text);
                        jd.dispose();

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else
                    JOptionPane.showMessageDialog(
                            null,
                            "Password errata!",
                            "Errore!",
                            JOptionPane.ERROR_MESSAGE);

            } catch (Exception e1) {
                Fatal.errorHandle(e1.getMessage());
            }
        });

        this.myJta.setPreferredSize(new Dimension(jpn.getWidth(), 70));
        this.myJta.setFont(new Font("Dialog", Font.BOLD, 33));

        DefaultCaret caret = (DefaultCaret) this.myJta.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.myJta.setAutoscrolls(true);
        this.myJta.setRows(1);
        this.myJta.setColumns(1);
        myJta.setLineWrap(true);
        myJta.setWrapStyleWord(true);

        jpn.add(enter, BorderLayout.NORTH);
        jpn.add(jlb, BorderLayout.CENTER);
        jpn.add(this.myJta, BorderLayout.SOUTH);

        jd.add(jpn, BorderLayout.CENTER);
        jd.setSize(new Dimension(400, 200));
        frame.setEnabled(false);
        jd.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.setEnabled(true);
            }
        });
        jd.setResizable(false);
        jd.pack();
        jd.setVisible(true);
    }

    @Override
    public void use(InitialFrame frameRec) {
        if (frameRec.getCurrentGame().getState().equals(WorldState.HACK)) {
            JOptionPane.showMessageDialog(null,
                    "Sto provando a connettermi al server.\nIl nostro ingegnere inverso e'riuscito a decompilare i criteri di sicurezza del server remoto russo."
                            +
                            "\nSei tu l'esperto di crittografia!\nCattura il flag!",
                    "Finalmente!",
                    JOptionPane.INFORMATION_MESSAGE);
            this.showChallenge(frameRec);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Oops. Non riesco a capire come utilizzare l'unita' di memoria. Non ne vedo alcuna utilità!",
                    "Non so cosa fare!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}