package com.adventure;

import com.adventure.error.Fatal;
import com.adventure.gameSound.SoundThread;
import com.adventure.graphic.InitialFrame;

/**
 * Main Class of the game.
 */
public class App {
    public static void main(String args[]) throws Exception {
        try {
            Thread music = new SoundThread("Overwatch - Evan King.wav") {
                @Override
                public void run() {
                    this.getAudioPlayer().volume(-20.0f);
                    this.getAudioPlayer().setLoop();
                    super.run();
                }
            };

            music.start();
            new InitialFrame().create();
            music.join();
        } catch (Exception e) {
            Fatal.log(e.getMessage());
        }
    }
}