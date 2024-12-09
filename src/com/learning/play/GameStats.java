package com.learning.play;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Text;
import com.learning.behaviour.MenuControl;

public class GameStats {

    private int gamePlayGroundWidth = 1000;
    private int gamePlayGroundHeath = 429;

    public GameStats() {

    }

    public void showMainText(int level){

        Text text = new Text(0, (gamePlayGroundHeath  + 10), "Level: " + level);
        text.translate(10, 0);
        text.setColor(Color.BLACK);
        text.draw();
    }

    public void gameOver(){
        Text text = new Text(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_X_MAX) / 2 , MenuControl.get(MenuControl.GRID_CANVAS_SIZE_Y_MAX) / 2, "GAME OVER");
        text.setColor(Color.BLACK);
        text.grow(200, 200);
        text.draw();
    }

}
