package com.learning;

import com.learning.play.Menu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.init();
            menu.show();
        } catch (IOException e) {
            System.err.println("File doesn't exist or can't be accessed");
        } catch (InterruptedException e) {
            System.err.println("The thread has been interrupted.");
        } catch (LineUnavailableException e) {
            System.err.println("Audio channel already in use, or lost!");
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Audio file is not supported!");
        } catch (Exception e) {
            System.err.println();
        }
    }
}
