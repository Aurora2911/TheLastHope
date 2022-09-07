package com.adventure.graphic;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import com.adventure.gameSound.SoundThread;
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.adventure.character.CHARACTER_TYPE;
import com.adventure.database.DbInitAdventure;
import com.adventure.database.DbUtility;
import com.adventure.database.H2Utility;
import com.adventure.error.Fatal;
import com.adventure.gameEnvironment.AdventureWorld;
import com.adventure.gameEnvironment.WorldState;
import com.adventure.items.Item;
import com.adventure.rooms.Room;
import com.adventure.savingGame.WrapperFileSerializable;

/**
 * Represents the graphic part of the game.
 */
public class InitialFrame extends JFrame implements ComponentListener {
    private Map<String, JButton> myButtons;
    private Map<String, JComboBox<Object>> myComboBox;
    private DbInitAdventure database;

    private JPanel background;
    private JLabel initialLabel;

    private AdventureWorld currentGame;

    private Thread commanderScream;
    private MyKeyListener InitialCutsceneKeyListener;

    public final static int CUTSCENE_LONG = 45000;
    public final static int WAIT_FOR_COMMANDER_SCREAM = 5000;

    public final static int PLAY_BUTTON_X = 520;
    public final static int PLAY_BUTTON_Y = 470;
    public final static int PLAY_BUTTON_WIDTH = 300;
    public final static int PLAY_BUTTON_HEIGHT = 70;

    public final static int LOAD_GAME_BUTTON_Y_OFFSET = 100;
    public final static int EXIT_BUTTON_Y_OFFSET = LOAD_GAME_BUTTON_Y_OFFSET + 100;
    public final static int INITIAL_LABEL_Y_OFFSET = -650;
    public final static String playButtonText = "Play";

    public final static int LOAD_GAME_BUTTON_X = PLAY_BUTTON_X;
    public final static int LOAD_GAME_BUTTON_Y = PLAY_BUTTON_Y + LOAD_GAME_BUTTON_Y_OFFSET;
    public final static int LOAD_GAME_BUTTON_WIDTH = PLAY_BUTTON_WIDTH;
    public final static int LOAD_GAME_BUTTON_HEIGHT = PLAY_BUTTON_HEIGHT;

    public final static String loadSavedButtonText = "Carica partita";

    public final static int EXIT_BUTTON_X = PLAY_BUTTON_X;
    public final static int EXIT_BUTTON_Y = LOAD_GAME_BUTTON_Y + EXIT_BUTTON_Y_OFFSET;
    public final static int EXIT_BUTTON_WIDTH = PLAY_BUTTON_WIDTH;
    public final static int EXIT_BUTTON_HEIGHT = PLAY_BUTTON_HEIGHT;

    public final static int LABEL_TEXT_X = PLAY_BUTTON_X;
    public final static int LABEL_TEXT_Y = -650;

    public final static String initialLabelText = "<html> THE LAST HOPE <br><html>";

    public final static String exitButtonText = "Exit";
    public final static String pathBackground = "./img/InitialBackgroundImage.jpg";
    public final static String pathBlackBackground = "./img/SfondoNero.jpg";
    public static final String INITIAL_SCENE_QUERY = "SELECT Text FROM Cutscene WHERE Descr = 'Intro'";

    public final static int WIDTH_WINDOW = 1300;
    public final static int HEIGHT_WINDOW = 1000;

    public final static String ROOM_COMBO_BOX_TITLE = "Oggetti nella stanza:";
    public final static String BAG_COMBO_BOX_TITLE = "Oggetti nello zaino:";

    public InitialFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("Textual Adventure Vol 1.0");
        currentGame = null;
        myButtons = new HashMap<String, JButton>();
        myComboBox = new HashMap<String, JComboBox<Object>>();
    }

    public void commanderScreamInit() {
        this.commanderScream = new Thread() {
            @Override
            public void run() {
                try {
                    // Wait for cutscene
                    Thread.sleep(CUTSCENE_LONG);
                    // Ok, you didn't press enter
                    InitialFrame.this.removeKeyListener(InitialFrame.this.InitialCutsceneKeyListener);
                    Thread.sleep(WAIT_FOR_COMMANDER_SCREAM);

                    if (InitialFrame.this.getCurrentGame().getState().equals(WorldState.INITIAL)) {
                        Thread t = new SoundThread("Comander.wav");
                        t.start();
                        InitialFrame.this.reflectionCommander();
                        t.join();
                    }
                } catch (InterruptedException e) {
                    // Thread interrupted
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    e1.printStackTrace();
                }
            }
        };

        this.commanderScream.start();
    }

    public void create() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);

        this.addComponentListener(this);

        this.initButtonPlay();
        this.initButtonLoadGame();
        this.initButtonExit();
        this.initInitialLabel();

        this.addInitialBackground();

        this.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        this.myButtons.get(playButtonText).addComponentListener(this);
    }

    public void componentResized(ComponentEvent e) {
        if (this.background != null) {
            this.myButtons.get(playButtonText).setBounds(
                    ((this.background.getWidth() - (this.myButtons.get(playButtonText).getWidth() / 2)) / 2),
                    ((this.background.getHeight() - (this.myButtons.get(playButtonText).getHeight() / 2)) / 2),
                    PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

            this.myButtons.get(loadSavedButtonText).setBounds(
                    ((this.background.getWidth() - (this.myButtons.get(loadSavedButtonText).getWidth() / 2)) / 2),
                    ((this.background.getHeight() - (this.myButtons.get(loadSavedButtonText).getHeight() / 2)) / 2)
                            + LOAD_GAME_BUTTON_Y_OFFSET,
                    LOAD_GAME_BUTTON_WIDTH, LOAD_GAME_BUTTON_HEIGHT);

            this.myButtons.get(exitButtonText).setBounds(
                    ((this.background.getWidth() - (this.myButtons.get(exitButtonText).getWidth() / 2)) / 2),
                    ((this.background.getHeight() - (this.myButtons.get(exitButtonText).getHeight() / 2)) / 2)
                            + EXIT_BUTTON_Y_OFFSET,
                    EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

            this.initialLabel.setBounds(((this.background.getWidth() - (this.initialLabel.getWidth() / 2)) / 2),
                    ((this.background.getHeight() - (this.initialLabel.getHeight() / 2)) / 2) + INITIAL_LABEL_Y_OFFSET,
                    500, 900);
        }
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    private void initButtonPlay() {
        this.myButtons.put(playButtonText, new JButton(playButtonText));
        this.myButtons.get(playButtonText).setBounds(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH,
                PLAY_BUTTON_HEIGHT);
        this.myButtons.get(playButtonText).setBackground(Color.BLUE);
        this.myButtons.get(playButtonText).setFont(new Font("SERIF", Font.BOLD, 30));
        this.myButtons.get(playButtonText).setToolTipText("Inizia una nuova partita");
        this.myButtons.get(playButtonText).setMargin(new Insets(0, 0, 0, 0));

        this.myButtons.get(playButtonText).addActionListener(new MyActionListeners(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (0 == JOptionPane.showConfirmDialog(this.myJFrame,
                        "Quest'azione cancellerà i vecchi dati. Continuare?",
                        "Warning",
                        JOptionPane.YES_NO_OPTION)) {
                    try {
                        InitialFrame.this.currentGame = new AdventureWorld();
                        InitialFrame.this.dispatcher();
                        this.getMyJFrame().initialCutscene(this.getMyJFrame().loadDataFromDb(INITIAL_SCENE_QUERY));
                        InitialFrame.this.currentGame.saveOnFileTask();
                        InitialFrame.this.scaryme();
                        InitialFrame.this.addKeyListener(new MyKeyListener(InitialFrame.this));
                        InitialFrame.this.commanderScreamInit();
                    } catch (SQLException | InterruptedException | UnsupportedAudioFileException
                            | LineUnavailableException | IOException e1) {
                        Fatal.log(e1.getMessage());
                    }
                    Timer tm = new Timer(0,
                            (x) -> {
                                InitialFrame.this.dispatcher();
                            });

                    tm.setRepeats(false);
                    tm.setInitialDelay(CUTSCENE_LONG);
                    tm.start();
                }
            }
        });
    }

    private void initialCutscene(String text) throws InterruptedException {
        JLabel jlb = new JLabel("<html>" + text + "<html><br> Premi Invio per iniziare...");
        JLabel img = new JLabel(new ImageIcon(pathBlackBackground), JLabel.CENTER);

        this.background.removeAll();

        jlb.setBounds(SwingConstants.CENTER, SwingConstants.CENTER, getWidth(), getHeight());
        jlb.setForeground(Color.WHITE);
        jlb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        this.background.add(jlb, BorderLayout.CENTER);
        this.background.add(img, BorderLayout.CENTER);

        this.add(background);

        this.background.repaint();
        this.background.revalidate();

        this.InitialCutsceneKeyListener = (new MyKeyListener(this) {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        this.obj.dispatcher();
                        this.obj.removeKeyListener(this);
                        if (commanderScream.isAlive())
                            commanderScream.interrupt();

                        Timer tm = new Timer(0,
                                (x) -> {
                                    try {
                                        if (InitialFrame.this.getCurrentGame().getState().equals(WorldState.INITIAL)) {

                                            new SoundThread("Comander.wav") {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(WAIT_FOR_COMMANDER_SCREAM);
                                                        Thread t = new Thread() {
                                                            @Override
                                                            public void run() {
                                                                InitialFrame.this.reflectionCommander();
                                                            }
                                                        };
                                                        t.start();
                                                        super.run();
                                                        t.join();
                                                    } catch (InterruptedException e) {
                                                        Fatal.errorHandle(e.getMessage());
                                                    }
                                                }
                                            }.start();
                                        }
                                    } catch (UnsupportedAudioFileException | IOException
                                            | LineUnavailableException e1) {
                                        Fatal.errorHandle(e1.getMessage());
                                    }
                                });
                        tm.setRepeats(false);
                        tm.start();
                    }
                } catch (Exception exc) {
                    Fatal.errorHandle(exc.getMessage());
                }
            }
        });

        this.addKeyListener(this.InitialCutsceneKeyListener);
        this.revalidate();
        this.setFocusable(true);
        this.requestFocus();
    }

    public void outro(String textRec) {
        JLabel jlb = new JLabel("<html><div style='text-align: center;'>" + textRec + "</div><html><br>");
        JLabel img = new JLabel(new ImageIcon(pathBlackBackground), JLabel.CENTER);

        this.background.removeAll();

        jlb.setBounds(SwingConstants.CENTER, SwingConstants.CENTER, getWidth(), getHeight());
        jlb.setForeground(Color.WHITE);
        jlb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));

        jlb.setHorizontalAlignment(SwingConstants.CENTER);
        jlb.setVerticalAlignment(SwingConstants.CENTER);

        this.background.add(jlb);
        this.background.add(img, BorderLayout.CENTER);

        this.add(background);

        this.background.repaint();
        this.background.revalidate();

        Timer tm = new Timer(0, (x) -> {
            System.exit(0);
        });

        tm.setRepeats(false);
        tm.setInitialDelay(15000);
        tm.start();

        this.addKeyListener(new MyKeyListener(this) {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.exit(0);
                }
            }
        });
    }

    private void initButtonLoadGame() {
        this.myButtons.put(loadSavedButtonText, new JButton(loadSavedButtonText));
        this.myButtons.get(loadSavedButtonText).setBounds(LOAD_GAME_BUTTON_X, LOAD_GAME_BUTTON_Y,
                LOAD_GAME_BUTTON_WIDTH, LOAD_GAME_BUTTON_HEIGHT);
        this.myButtons.get(loadSavedButtonText).setBackground(Color.YELLOW);
        this.myButtons.get(loadSavedButtonText).setFont(new Font("SERIF", Font.BOLD, 30));
        this.myButtons.get(loadSavedButtonText).setToolTipText("Carica partita");
        this.myButtons.get(loadSavedButtonText).setMargin(new Insets(0, 0, 0, 0));

        this.myButtons.get(loadSavedButtonText).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InitialFrame.this.currentGame = new AdventureWorld();
                    InitialFrame.this.currentGame = WrapperFileSerializable.read(AdventureWorld.PATHFILE_LOG);
                    InitialFrame.this.currentGame.saveOnFileTask();
                    JOptionPane.showMessageDialog(null,
                            "Il caricamento e' stato eseguito correttamente!",
                            "Partita caricata!",
                            JOptionPane.INFORMATION_MESSAGE);
                    InitialFrame.this.dispatcher();
                    InitialFrame.this.scaryme();
                    InitialFrame.this.addKeyListener(new MyKeyListener(InitialFrame.this));
                } catch (EOFException | UnsupportedAudioFileException | LineUnavailableException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Non e' stato possibile caricare il file!\n"
                                    + "Una possibile causa potrebbe esssere l'inesistenza del file di salvataggio! "
                                    + e1.getMessage(),
                            "Nessuna partita caricata!",
                            JOptionPane.INFORMATION_MESSAGE);
                    InitialFrame.this.dispatcher();
                    System.out.println(InitialFrame.this.currentGame.getCurrentRoom());
                } catch (ClassNotFoundException | IOException e1) {

                    Fatal.log(e1.getMessage());
                }
            }
        });
    }

    public void scaryme() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new SoundThread("JumpScare.wav") {
            @Override
            public void run() {
                if (InitialFrame.this.getCurrentGame().getState().equals(WorldState.INITIAL) == true) {
                    while (InitialFrame.this.getCurrentGame().getState()
                            .equals(WorldState.COMANDER_LIBERATION) == false)
                        ;
                    super.run();
                }
            }
        }.start();
    }

    private void initButtonExit() {
        this.myButtons.put(exitButtonText, new JButton(exitButtonText));
        this.myButtons.get(exitButtonText).setBounds(EXIT_BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH,
                EXIT_BUTTON_HEIGHT);
        this.myButtons.get(exitButtonText).setBackground(Color.WHITE);
        this.myButtons.get(exitButtonText).setFont(new Font("SERIF", Font.BOLD, 30));
        this.myButtons.get(exitButtonText).setToolTipText("Chiudi programma");
        this.myButtons.get(exitButtonText).setMargin(new Insets(0, 0, 0, 0));

        this.myButtons.get(exitButtonText).addActionListener(new MyActionListeners(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (0 == JOptionPane.showConfirmDialog(null, "Sei sicuro?", "Warning",
                        JOptionPane.YES_NO_OPTION))
                    System.exit(0);
            }
        });
    }

    private void addInitialBackground() {
        this.background = new JPanel();

        this.background.setLayout(new BorderLayout());
        this.background.add(this.myButtons.get(exitButtonText));
        this.background.add(this.myButtons.get(loadSavedButtonText));
        this.background.add(this.myButtons.get(playButtonText));
        this.background.add(initialLabel, BorderLayout.NORTH);

        this.background.add(new JLabel(new ImageIcon(pathBackground)), BorderLayout.CENTER);

        this.add(background);
    }

    public void reflectionCommander() {
        DbUtility database = new H2Utility();
        Properties myProperties = new Properties();
        ResultSet result = null;
        String query = null;
        String text = null;

        query = "SELECT Text FROM MainCharacterReflections WHERE CharacterMoment = 'REF[1]'";

        try {
            myProperties.setProperty("user", "user");
            myProperties.setProperty("password", "1234");
            database.openDbConnection("jdbc:h2:./MAP/db/prova", myProperties);

            result = database.readFromDb(query);
            text = database.getText(result);

        } catch (SQLException e1) {
            Fatal.log(e1.getMessage());
        }

        JOptionPane.showMessageDialog(null,
                text,
                "Attenzione!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String loadDataFromDb(String queryRec) throws SQLException {
        ResultSet result = null;
        String text = new String();

        this.database = new DbInitAdventure();

        database.initDb();
        result = database.readFromDb(queryRec);

        while (result.next()) {
            text = (result.getString(1));
        }

        return text;
    }

    private void initInitialLabel() {

        this.initialLabel = new JLabel(initialLabelText, SwingConstants.CENTER);
        this.initialLabel.setFont(new Font("Dialog", Font.PLAIN, 100));
        this.initialLabel.setForeground(Color.BLACK);
    }

    public void dispatcher() {

        DirectionalButton directionalButton = null;

        this.background.removeAll();
        directionalButton = this.drawRoom();
        this.addDirectionalButtonHandler(directionalButton);

        this.revalidate();
        this.setFocusable(true);
        this.requestFocus();
    }

    private void addDirectionalButtonHandler(DirectionalButton buttons) {

        buttons.getUp().addActionListener(
                new DirectionalButtonsActionListener(this,
                        this.currentGame.getCurrentRoom().getNearRooms().getNorth()));

        buttons.getDown().addActionListener(
                new DirectionalButtonsActionListener(this,
                        this.currentGame.getCurrentRoom().getNearRooms().getSouth()));

        buttons.getLeft().addActionListener(
                new DirectionalButtonsActionListener(this, this.currentGame.getCurrentRoom().getNearRooms().getWest()));

        buttons.getRight().addActionListener(
                new DirectionalButtonsActionListener(this, this.currentGame.getCurrentRoom().getNearRooms().getEast()));
    }

    DirectionalButton drawRoom() {
        DirectionalButton directionalButton = new DirectionalButton();
        Room currentRoom = this.currentGame.getCurrentRoom();
        JPanel panelInterUser = new JPanel();
        JPanel panelDesc = new JPanel();
        JPanel panelItems = new JPanel();

        panelInterUser.setBackground(Color.BLACK);
        panelInterUser.setLayout(new BorderLayout());
        panelInterUser.add(new JLabel(new ImageIcon(currentRoom.getDrawPath())));

        this.background.setLayout(new BorderLayout());

        panelInterUser.add(directionalButton.makeUp(), BorderLayout.NORTH);
        panelInterUser.add(directionalButton.makeDown(), BorderLayout.SOUTH);
        panelInterUser.add(directionalButton.makeRight(), BorderLayout.EAST);
        panelInterUser.add(directionalButton.makeLeft(), BorderLayout.WEST);

        directionalButton.getUp().setBackground(Color.BLACK);
        directionalButton.getDown().setBackground(Color.BLACK);
        directionalButton.getLeft().setBackground(Color.BLACK);
        directionalButton.getRight().setBackground(Color.BLACK);

        panelDesc.setLayout(new BorderLayout());
        panelDesc.add(this.createScrollableTextArea(), BorderLayout.NORTH);

        panelItems.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelItems.add(this.createComboBox(
                this.currentGame
                        .getCurrentRoom()
                        .getItems()
                        .entrySet()
                        .stream()
                        .map(
                                (k) -> {
                                    return new AbstractMap.SimpleEntry<Item, Integer>(k.getKey(), k.getValue());
                                })
                        .collect(Collectors.toSet()).toArray(),
                ROOM_COMBO_BOX_TITLE, new ItemsInRoomActionListener(this)), BorderLayout.SOUTH);

        panelItems.add(this.createComboBox(
                this.currentGame
                        .getBagByCharacter(CHARACTER_TYPE.PROTAGONIST)
                        .entrySet()
                        .stream()
                        .map(
                                (k) -> {
                                    return new AbstractMap.SimpleEntry<Item, Integer>(k.getKey(), k.getValue());
                                })
                        .collect(Collectors.toSet()).toArray(),
                BAG_COMBO_BOX_TITLE, new ItemsInBagActionListener(this)), BorderLayout.SOUTH);

        this.background.add(panelInterUser, BorderLayout.CENTER);
        this.background.add(panelDesc, BorderLayout.SOUTH);
        this.background.add(panelItems, BorderLayout.NORTH);

        return directionalButton;
    }

    private JScrollPane createScrollableTextArea() {
        JTextArea jta = new JTextArea(this.currentGame.getCurrentRoom().getDescription());
        JScrollPane scroll = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jta.setPreferredSize(new Dimension(30, 60));
        jta.setEditable(false);
        jta.setBackground(Color.white);
        jta.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 20));
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        return scroll;
    }

    private JComboBox<Object> createComboBox(Object[] items, String title, ActionListener eventHandle) {
        this.myComboBox.put(title, new JComboBox<Object>(items));

        this.myComboBox.get(title).setEditable(false);
        this.myComboBox.get(title).setToolTipText(title);
        this.myComboBox.get(title).setSelectedIndex(-1);
        this.myComboBox.get(title).setRenderer(new MyListCellRenderer<Object>(title));

        this.myComboBox.get(title).addActionListener(eventHandle);
        this.myComboBox.get(title).setPreferredSize(new Dimension(170, 40));

        return this.myComboBox.get(title);
    }

    public AdventureWorld getCurrentGame() {
        return currentGame;
    }

    public Map<String, JComboBox<Object>> getMyComboBox() {
        return myComboBox;
    }
}