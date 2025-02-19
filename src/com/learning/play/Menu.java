package com.learning.play;

import com.codeforall.online.simplegraphics.graphics.Canvas;
import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.MenuControl;

import javax.sound.sampled.*;
import java.io.IOException;


public class Menu implements MouseHandler {

    private int buttonInitX = MenuControl.get(MenuControl.MENU_BUTTON_X);
    private int buttonInitY = MenuControl.get(MenuControl.MENU_BUTTON_Y);
    private int buttonFinalX = MenuControl.get(MenuControl.MENU_BUTTON_WIDTH);
    private int buttonFinalY = MenuControl.get(MenuControl.MENU_BUTTON_HEIGHT);

    private boolean startButtonPressed;
    private SoundPlayer soundPlayer;

    public Menu()  {
        this.startButtonPressed = false;
    }

    public void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Canvas.setMaxX(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_X_MAX));
        Canvas.setMaxY(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_Y_MAX));

        try {
            String filePath = "resources/sounds/introTheme.wav";
            soundPlayer  = new SoundPlayer(filePath);
            soundPlayer.play();

        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    public void show() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {

            Picture backgroundImg = new Picture(MenuControl.GAMESTATS_POS_X.getValue(),
                                                MenuControl.GAMESTATS_POS_Y.getValue(),
                                            "resources/images/menu_background.png");
            backgroundImg.draw();

            Mouse mouse = new Mouse(this);
            mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

            while(!startButtonPressed) {
                Thread.sleep(50);
            }

            soundPlayer.stop();

            backgroundImg.delete();

            Game game = new Game(1);
            game.startGame(1);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();

        if (x >= buttonInitX
                && x <= (buttonInitX + buttonFinalX)
                && y >= buttonInitY
                && y <= (buttonInitY + buttonFinalY)) {
            startButtonPressed = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
