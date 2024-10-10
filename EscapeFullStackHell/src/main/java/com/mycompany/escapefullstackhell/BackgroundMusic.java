package com.mycompany.escapefullstackhell;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundMusic {

    private Clip musicClip;

    BackgroundMusic(String soundFilePath, float volume) {
        try {
            InputStream soundFileStream = getClass().getResourceAsStream(soundFilePath);
            if (soundFileStream == null) {
                throw new IOException("Unable to find sound file: " + soundFilePath);
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(soundFileStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);

            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setVolume(volume);
    }

    public void setVolume(float volume) {
        if (musicClip != null) {
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }

    public void play() {
        if (musicClip.isRunning()) {
            return;
        }

        musicClip.setFramePosition(0);
        musicClip.start();
    }
}