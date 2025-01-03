package com.learning.play;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

    private Clip clip;
    private AudioInputStream audioInputStream;

    public SoundPlayer(String filePath) throws UnsupportedAudioFileException,  IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play()
    {
        clip.start();
        String status = "play";
    }

    // Method to stop the audio
    public void stop(){
        clip.stop();
        clip.close();
    }



}