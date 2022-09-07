package com.adventure.gameSound;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Creates a thread that playbacks a sound.
 */
public class SoundThread extends Thread {
    private String path;
    private Sound audioPlayer;

    public SoundThread(String mySound)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.path = mySound;
        this.audioPlayer = new Sound(getPath());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Sound getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(Sound audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void run() {
        audioPlayer.play();
        while (true);
    }
}
