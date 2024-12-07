package com.learning.play;

import com.codeforall.online.simplegraphics.graphics.Canvas;
import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.MenuControl;

import java.io.FileNotFoundException;


public class Menu implements MouseHandler {

    int buttonInitX = MenuControl.get(MenuControl.MENU_BUTTON_X);
    int buttonInitY = MenuControl.get(MenuControl.MENU_BUTTON_Y);
    int buttonFinalX = MenuControl.get(MenuControl.MENU_BUTTON_WIDTH);
    int buttonFinalY = MenuControl.get(MenuControl.MENU_BUTTON_HEIGHT);

    private boolean startButtonPressed;

    private Game game;

    public Menu() {
        this.startButtonPressed = false;
    }

    public void init(){
        Canvas.setMaxX(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_X_MAX));
        Canvas.setMaxY(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_Y_MAX));
    }

    public void show() throws FileNotFoundException, InterruptedException {

            Picture backgroundImg = new Picture(10, 10, "resources/images/menu_background.png");
            backgroundImg.draw();

            // Set up mouse handling
            Mouse mouse = new Mouse(this);
            mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

            while(!startButtonPressed) {
                Thread.sleep(50);
            }

            backgroundImg.delete();

            Game game = new Game(1);


    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        // Get mouse coordinates
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        System.out.println(x + " " + y);
        if (x >= buttonInitX
                && x <= (buttonInitX + buttonFinalX)
                && y >= buttonInitY
                && y <= (buttonInitY + buttonFinalY)) {
            startButtonPressed = true;
            System.out.println("Start button clicked!");
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
