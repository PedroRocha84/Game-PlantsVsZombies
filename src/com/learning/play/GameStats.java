package com.learning.play;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Text;
import com.learning.behaviour.MenuControl;

public class GameStats {

    private int gamePlayGroundWidth = 1000;
    private int gamePlayGroundHeath = 429;

    private int startPlayerEnergy;
    private int energyStar;
    private int level;

    public GameStats(int level, int startEnergy) {
        this.startPlayerEnergy = startEnergy;
        this.level = level;
    }

    public int getPlayerEnergy(){
        return this.startPlayerEnergy;
    }

    public void setPlayerEnergy(int energy) {
        this.startPlayerEnergy = energy;
    }


    public int getStarEnergy(){
        return this.energyStar;
    }

    public void setStarEnergy(int energy) {
        this.energyStar += energy;
    }

    public void removeStarEnergy(int energy) {
        this.energyStar -= energy;
    }

    public void increaseEnergy(int amount) {
        this.energyStar += amount;
    }

    public void setplayerInjury(int energy) {
        this.startPlayerEnergy -= energy;
    }

    public void showMainText(int level){

        Text text = new Text(0, (gamePlayGroundHeath  + 10), "Level: " + level);
        text.translate(10, 0);
        text.setColor(Color.BLACK);
        text.draw();
    }

    public void getEnergyLeft(){
        Text text = new Text(0, (gamePlayGroundHeath  + 10), "Remaining Energy : " + getPlayerEnergy());
        text.translate(10, 20);
        text.setColor(Color.BLACK);
        text.draw();
    }

    public void gameOver(){
        Text text = new Text(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_X_MAX) / 2 , MenuControl.get(MenuControl.GRID_CANVAS_SIZE_Y_MAX) / 2, "GAME OVER");
        text.setColor(Color.BLACK);
        text.grow(200, 200);
        text.draw();
    }

    public void youWin(){
        Text texto = new Text(MenuControl.get(MenuControl.GRID_CANVAS_SIZE_X_MAX) / 2 , MenuControl.get(MenuControl.GRID_CANVAS_SIZE_Y_MAX) / 2, "You Win!!");
        texto.setColor(Color.DARK_ORANGE);
        texto.grow(200, 200);
        texto.draw();
    }

}
