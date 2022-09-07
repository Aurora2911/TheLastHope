package com.adventure.gameSound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implements the sound.
 */
public class Sound {

	private Clip clip;
	private AudioInputStream audioInputStream;

	public Sound(String filePath)
			throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
	}

	public void volume(float decibel) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(decibel);
	}

	public void setLoop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play() {
		clip.start();
	}
}
