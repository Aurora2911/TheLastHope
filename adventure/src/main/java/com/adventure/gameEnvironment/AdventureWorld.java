package com.adventure.gameEnvironment;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.character.Character;
import com.adventure.graphic.InitialFrame;
import com.adventure.items.GasolineCan;
import com.adventure.items.Hammer;
import com.adventure.items.Item;
import com.adventure.items.Matches;
import com.adventure.items.NoteFour;
import com.adventure.items.NoteThree;
import com.adventure.items.Pliers;
import com.adventure.rooms.ROOM_TYPE;
import com.adventure.rooms.Room;
import com.adventure.savingGame.FileSaverTask;
import com.adventure.savingGame.Savageable;
import com.adventure.savingGame.WrapperFileSerializable;

/**
 * Engine class of the game.
 */
public class AdventureWorld implements Serializable, Savageable {
    private MapGame ukrMap;
    private MapGame ruMap;
    private Map<CHARACTER_TYPE, Character> characters;
    private Map<ROOM_TYPE, Room> rooms;

    public static final String PATHFILE_LOG = "./Saving.log";

    private Room warehouse,
            accomodation1,
            accomodation2,
            accomodation3,
            accomodation4,
            surveillance,
            ufficials,
            operative;

    private WorldState state;

    public AdventureWorld() {
        this.init();
    }

    private void init() {
        try {
            this.characters = new HashMap<>();
            this.rooms = new HashMap<>();
            this.ukrMapInit();
            this.ruMapInit();
            this.initCharacters();
            this.initFile();
            this.setState(WorldState.INITIAL);
        } catch (Exception e) {
        }
    }

    public synchronized void setState(WorldState newState) {
        this.state = newState;
    }

    public synchronized WorldState getState() {
        return this.state;
    }

    private void ukrMapInit() {
        this.rooms.put(ROOM_TYPE.GARAGE, new Room(ROOM_TYPE.GARAGE, "./img/imgUkr/Garage.jpg"));
        this.rooms.put(ROOM_TYPE.GROUND_FLOOR, new Room(ROOM_TYPE.GROUND_FLOOR, "./img/imgUkr/GroundFloor.jpg"));
        this.rooms.put(ROOM_TYPE.FIRST_FLOOR, new Room(ROOM_TYPE.FIRST_FLOOR, "./img/imgUkr/FirstFloor.jpg"));
        this.rooms.put(ROOM_TYPE.BASEMENT, new Room(ROOM_TYPE.BASEMENT, "./img/imgUkr/Basement.jpg"));
        this.rooms.put(ROOM_TYPE.SAFE, new Room(ROOM_TYPE.SAFE, "./img/imgUkr/Safe.png"));
        this.rooms.put(ROOM_TYPE.ENTRANCE, new Room(ROOM_TYPE.ENTRANCE, InitialFrame.pathBackground));

        this.rooms.get(ROOM_TYPE.GROUND_FLOOR).setNearRooms(this.rooms.get(ROOM_TYPE.FIRST_FLOOR),
                this.rooms.get(ROOM_TYPE.BASEMENT), this.rooms.get(ROOM_TYPE.GARAGE),
                this.rooms.get(ROOM_TYPE.ENTRANCE));
        this.rooms.get(ROOM_TYPE.FIRST_FLOOR).setNearRooms(null, this.rooms.get(ROOM_TYPE.GROUND_FLOOR), null, null);
        this.rooms.get(ROOM_TYPE.GARAGE).setNearRooms(null, null, null, this.rooms.get(ROOM_TYPE.GROUND_FLOOR));
        this.rooms.get(ROOM_TYPE.BASEMENT).setNearRooms(this.rooms.get(ROOM_TYPE.GROUND_FLOOR), null,
                this.rooms.get(ROOM_TYPE.SAFE), null);
        this.rooms.get(ROOM_TYPE.SAFE).setNearRooms(null, null, null, this.rooms.get(ROOM_TYPE.BASEMENT));
        this.rooms.get(ROOM_TYPE.ENTRANCE).setNearRooms(null, null, this.rooms.get(ROOM_TYPE.GROUND_FLOOR), null);

        this.rooms.get(ROOM_TYPE.GARAGE).addItem(new GasolineCan());
        this.rooms.get(ROOM_TYPE.GARAGE).addItem(new Pliers());
        this.rooms.get(ROOM_TYPE.FIRST_FLOOR).addItem(new Matches());
        this.rooms.get(ROOM_TYPE.BASEMENT).addItem(new Hammer());
        this.rooms.get(ROOM_TYPE.BASEMENT).addItem(new NoteFour());
        this.rooms.get(ROOM_TYPE.FIRST_FLOOR).addItem(new NoteThree());

        this.rooms.get(ROOM_TYPE.SAFE).lock();
        this.rooms.get(ROOM_TYPE.BASEMENT).lock();
        this.rooms.get(ROOM_TYPE.ENTRANCE).lock();
        this.rooms.get(ROOM_TYPE.ENTRANCE).setEscapable(true);

        ukrMap = new MapGame(MAP_TYPE.UKR, this.rooms.values().stream().collect(Collectors.toSet()),
                "./img/imgUkr/Mappa.jpg");
    }

    private void ruMapInit() {
        Set<Room> ruRooms = new HashSet<>();

        this.warehouse = new Room(ROOM_TYPE.WAREHOUSE, null);
        this.accomodation1 = new Room(ROOM_TYPE.ACCOMODATION1, null);
        this.accomodation2 = new Room(ROOM_TYPE.ACCOMODATION2, null);
        this.accomodation3 = new Room(ROOM_TYPE.ACCOMODATION3, null);
        this.accomodation4 = new Room(ROOM_TYPE.ACCOMODATION4, null);
        this.surveillance = new Room(ROOM_TYPE.SURVEILLANCE, null);
        this.ufficials = new Room(ROOM_TYPE.UFFICIALS, null);
        this.operative = new Room(ROOM_TYPE.OPERATIVE, null);

        this.warehouse.setNearRooms(null, null, accomodation1, null);
        this.accomodation1.setNearRooms(null, null, accomodation1, warehouse);
        this.accomodation2.setNearRooms(operative, surveillance, null, accomodation1);
        this.accomodation3.setNearRooms(null, null, accomodation4, surveillance);
        this.accomodation4.setNearRooms(null, null, ufficials, accomodation3);
        this.surveillance.setNearRooms(accomodation2, null, accomodation3, null);
        this.ufficials.setNearRooms(null, null, null, accomodation4);
        this.operative.setNearRooms(null, accomodation2, null, null);

        ruRooms.add(this.warehouse);
        ruRooms.add(this.accomodation1);
        ruRooms.add(this.accomodation2);
        ruRooms.add(this.accomodation3);
        ruRooms.add(this.accomodation4);
        ruRooms.add(this.surveillance);
        ruRooms.add(this.ufficials);
        ruRooms.add(this.operative);
    }

    public void initCharacters() throws IllegalArgumentException {
        characters.put(
                CHARACTER_TYPE.PROTAGONIST,
                new Character(
                        "Jack",
                        new Position(
                                ukrMap,
                                this.rooms.get(ROOM_TYPE.GROUND_FLOOR)),
                        CHARACTER_TYPE.PROTAGONIST));
    }

    public void initFile() throws IOException {
        new File(PATHFILE_LOG).createNewFile();
    }

    @Override
    public synchronized void save() {
        try {
            WrapperFileSerializable.write(PATHFILE_LOG, this, false);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(
                    null,
                    "File " + AdventureWorld.PATHFILE_LOG + " non trovato. Non e' stato possibile salvare i dati!",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveOnFileTask() {
        // Don't wait it!
        new FileSaverTask(this).run();
    }

    public MapGame getRuMap() {
        return this.ruMap;
    }

    public MapGame getUkrMap() {
        return this.ukrMap;
    }

    public Character getCharacter(CHARACTER_TYPE characterTypeRec) {
        return this.characters.get(characterTypeRec);
    }

    public Room getRoom(ROOM_TYPE roomTypeRec) {
        return this.rooms.get(roomTypeRec);
    }

    /**
     * 
     * @return Current room of protagonist
     */
    public Room getCurrentRoom() {
        return this.characters.get(CHARACTER_TYPE.PROTAGONIST).getPosition().getRoom();
    }

    /**
     * 
     * @return Current map of protagonist
     */
    public MapGame getCurrentMap() {
        return this.characters.get(CHARACTER_TYPE.PROTAGONIST).getPosition().getMap();
    }

    public Map<Item, Integer> getBagByCharacter(CHARACTER_TYPE characterType) {
        return this.characters.get(characterType).getBag();
    }
}