package com.learning.play;
import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Text;
import com.codeforall.online.simplegraphics.pictures.Picture;

public class GameStats {

    private int gamePlayGroundHeath = 429;

    public void showMainText(int level){

        Text text = new Text(0, (gamePlayGroundHeath  + 10), "Level: " + level);
        text.translate(10, 0);
        text.setColor(Color.BLACK);
        text.draw();
    }

    public void gameOver(){
        Picture over = new Picture(170, 25,"resources/images/gameOver1s.png");
        over.draw();
    }

}
