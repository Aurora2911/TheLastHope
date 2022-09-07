package com.adventure.items;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.database.DbUtility;
import com.adventure.database.H2Utility;
import com.adventure.graphic.InitialFrame;
import com.adventure.rooms.ROOM_TYPE;

public class Fire extends UsableItem {
    final static public ITEM_TYPE it = ITEM_TYPE.FIRE;
    final static public String ImagePath = "./img/items/fire.gif";

    public String getDescription() {
        return "Fuoco: Scappa!";
    }

    @Override
    public Boolean here(ROOM_TYPE rt) {
        return rt.equals(ROOM_TYPE.FIRST_FLOOR);
    }

    public ITEM_TYPE getType() {
        return it;
    }

    public String getPath() {
        return ImagePath;
    }

    @Override
    public void use(InitialFrame frameRec) {

        DbUtility database = new H2Utility();
        ResultSet result = null;
        String query = null;
        String text = null;
        Properties myProperties = new Properties();

        query = "SELECT Text FROM MainCharacterReflections WHERE CharacterMoment = 'REF[0]'";

        try {
            myProperties.setProperty("user", "user");
            myProperties.setProperty("password", "1234");
            database.openDbConnection("jdbc:h2:./MAP/db/prova", myProperties);

            result = database.readFromDb(query);
            text = database.getText(result);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        JOptionPane.showMessageDialog(null,
                text,
                "Finalmente!",
                JOptionPane.INFORMATION_MESSAGE);

        frameRec.getCurrentGame().getRoom(ROOM_TYPE.BASEMENT).unlock();
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.FIRST_FLOOR).lock();
        frameRec.getCurrentGame().getCharacter(CHARACTER_TYPE.PROTAGONIST).releaseItem(this);
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.GARAGE).setDrawPath("./img/imgUkr/Garage2.jpg");
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.FIRST_FLOOR).setDrawPath("img/imgUkr/FirstFloor2.jpg");
        frameRec.dispatcher();
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.GARAGE).addItem(new Usb());
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.GARAGE).addItem(new NoteOne());
        frameRec.getCurrentGame().getRoom(ROOM_TYPE.GARAGE).addItem(new NoteTwo());
    }
}