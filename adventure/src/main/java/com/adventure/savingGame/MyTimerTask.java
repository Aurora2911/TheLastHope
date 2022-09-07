package com.adventure.savingGame;

import java.util.TimerTask;
import com.adventure.error.Fatal;

/**
 * Saves data in a regular way.
 */
public class MyTimerTask extends TimerTask {
    private Savageable objectToWrite;

    MyTimerTask(Savageable objectRec) {
        this.objectToWrite = objectRec;
    }

    @Override
    public void run() {
        try {
            this.objectToWrite.save();
        } catch (Exception e) {
            Fatal.errorHandle(e.getMessage());
        }
    }
}
