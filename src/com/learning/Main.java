package com.learning;

import com.learning.play.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Menu menu = new Menu();
        menu.init();
        try {
            menu.show();
        }catch (IOException e){
            System.err.println(e);
        }finally {

        }



    }

}
