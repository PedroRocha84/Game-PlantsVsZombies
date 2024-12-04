package com.learning.play;

import com.codeforall.online.simplegraphics.graphics.Canvas;
import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviours.GameData;

import javax.swing.*;
import java.awt.*;


public class Menu implements MouseHandler {

    /**
     * The start button on the image, will be controlled by user Mouse Click and return the click position between
     * coordinates
     * */

    int buttonInitX = GameData.get(GameData.MENU_BUTTON_X);
    int buttonInitY = GameData.get(GameData.MENU_BUTTON_Y);
    int buttonFinalX = GameData.get(GameData.MENU_BUTTON_WIDTH);
    int buttonFinalY = GameData.get(GameData.MENU_BUTTON_HEIGHT);
    private boolean startButtonPressed;

    public Menu() {
        this.startButtonPressed = false;
    }

    /**
     * This method will run one at the game start to show the menu
     * */

    public void init(){
        Canvas.setMaxX(GameData.get(GameData.GRID_CANVAS_SIZE_X_MAX));
        Canvas.setMaxY(GameData.get(GameData.GRID_CANVAS_SIZE_Y_MAX));


    }

    public void show() throws InterruptedException {
        Picture backgroundImg = new Picture(10, 10, "resources/images/menu_background.png");
        backgroundImg.draw();

        // Set up mouse handling
        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

        while(!startButtonPressed) {
            Thread.sleep(50);
        }

        backgroundImg.load("resources/images/playground.png");
        Picture topMenu = new Picture(GameData.get(GameData.GRID_GRIDSTART_X), 0, "resources/images/topMenu.png");
        topMenu.draw();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        // Get mouse coordinates
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();

        if (x >= buttonInitX && x <= buttonInitX + buttonFinalX && y >= buttonFinalY && y <= buttonInitY + buttonFinalY) {
            startButtonPressed = true;
            System.out.println("Start button clicked!");
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
