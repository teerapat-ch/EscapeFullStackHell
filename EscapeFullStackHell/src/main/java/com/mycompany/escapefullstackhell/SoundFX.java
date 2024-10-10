package com.mycompany.escapefullstackhell;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundFX {

    private Clip clip;

    SoundFX(String soundFilePath, float volume) {
        try {
            InputStream soundFileStream = getClass().getResourceAsStream(soundFilePath);
            if (soundFileStream == null) {
                throw new IOException("Unable to find sound file: " + soundFilePath);
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(soundFileStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setVolume(volume);
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }

    public void play() {
        if (clip.isRunning()) {
            return;
        }

        clip.setFramePosition(0);
        clip.start();
    }
}