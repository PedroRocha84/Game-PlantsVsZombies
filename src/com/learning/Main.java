package com.learning;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.play.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Menu menu = new Menu();
        menu.init();

        try {
            menu.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
