package com.learning;

import com.learning.play.Menu;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        try {
            Menu menu = new Menu();
            menu.init();
            menu.show();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
