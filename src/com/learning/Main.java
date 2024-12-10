package com.learning;

import com.learning.play.Menu;
import com.learning.play.PlantSelectionScreen;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        new PlantSelectionScreen();

        try {
            Menu menu = new Menu();
            menu.init();
            menu.show();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
