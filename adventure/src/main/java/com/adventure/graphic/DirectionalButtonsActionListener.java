package com.adventure.graphic;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.adventure.character.CHARACTER_TYPE;
import com.adventure.database.DbUtility;
import com.adventure.database.H2Utility;
import com.adventure.gameEnvironment.Position;
import com.adventure.rooms.Room;

/**
 * Reacts to the movement among rooms.
 */
public class DirectionalButtonsActionListener extends MyActionListeners {

    private Room which;

    DirectionalButtonsActionListener(InitialFrame frame, Room room) {
        super(frame);
        this.which = room;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.which == null)
            JOptionPane.showConfirmDialog(
                    this.getMyJFrame(),
                    "Oops. Non e' presente una stanza in questa direzione!",
                    "Dove vuoi andare?",
                    JOptionPane.DEFAULT_OPTION);
        else if (this.which.isLocked() == true)
            JOptionPane.showConfirmDialog(
                    this.getMyJFrame(),
                    "Oops. Sembra che questa stanza sia bloccata!",
                    "Non puoi passare!",
                    JOptionPane.DEFAULT_OPTION);
        else if (this.which
                .isEscapable() == true) {

            Properties myProperties = new Properties();
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

                this.getMyJFrame().outro(text);

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else {
            this.getMyJFrame().getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST)
                    .setPosition(new Position(
                            this.getMyJFrame().getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).getPosition()
                                    .getMap(),
                            this.which));
            this.getMyJFrame().dispatcher();
        }
    }
}
