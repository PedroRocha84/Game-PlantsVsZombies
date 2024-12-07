package com.learning.pea;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.MenuControl;

import java.io.FileNotFoundException;
import java.util.List;

public class Pea {
    private List<Pea> allPeas;
    private Picture newPeaPicture;
    private int xPos;
    private int yPos;
    private int speed = 10;
    public Pea() {

    }

    public void showPea(int xPos, int yPos) throws FileNotFoundException {
       this.xPos = xPos;
       this.yPos = yPos;

    }

    public void addNewPea(int centerX, int centerY) throws FileNotFoundException,
            InterruptedException {

        int distFromPlantX = centerX + MenuControl.PEAS_POSITION_ADJUSTMENT_X.getValue();
        int distFromPlantY = centerY + MenuControl.PEAS_POSITION_ADJUSTMENT_Y.getValue();

        newPeaPicture = new Picture(distFromPlantX, distFromPlantY, "resources/images/pea.png");
        newPeaPicture.draw();

    }

    public void movePea()  {
        newPeaPicture.translate(1  * speed,0);
    }

    public int getPositionX() {
        return newPeaPicture.getX();
    }

    public Picture getPicture() {
        return newPeaPicture;
    }
}
