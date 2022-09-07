package com.adventure.savingGame;

import java.util.Date;
import java.util.Timer;

/**
 * Saves data on file every interval of time.
 */
public class FileSaverTask implements Runnable {
    private Savageable obj;
    final static int PERIOD = 120000; // Two minutes

    public FileSaverTask(Savageable objRec) {
        this.obj = objRec;
    }

    @Override
    public void run() {
        new Timer().schedule(new MyTimerTask(obj), new Date(), PERIOD);
    }
}
