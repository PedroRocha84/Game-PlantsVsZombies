package com.learning.pea;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.MenuControl;

import java.io.FileNotFoundException;

public class Pea {

    private int xPos;
    private int yPos;
    private int speed = 3;
    private int damage = 20;

    private Picture newPeaPicture;

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

        this.xPos =centerX;
        this.yPos =centerY;

    }

    public void movePea()  {
        newPeaPicture.translate(1  * speed,0);
        this.xPos = newPeaPicture.getX();
    }

    public int getPositionX() {
        return newPeaPicture.getX();
    }

    public int getPositionY() {return newPeaPicture.getY();}

    public int getDamage() {return damage;}

    public void delete(){
        newPeaPicture.delete();
    }

    public Picture getPicture() {
        return newPeaPicture;
    }

    @Override
    public String toString() {
        return "Pea{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", speed=" + speed +
                ", damage=" + damage +
                ", newPeaPicture=" + newPeaPicture +
                '}';
    }
}
