package com.learning.play;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviours.GameData;

public class Game {

    private Game game;

    public void gameMenusInit(Picture picture) {
        picture.load("resources/images/playground.png");
        Picture topMenu = new Picture(GameData.get(GameData.GRID_GRIDSTART_X), 0, "resources/images/topMenu.png");
        topMenu.draw();
    }


}
