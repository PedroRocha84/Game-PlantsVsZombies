package com.learning.plant;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.play.Game;

import java.io.IOException;

public class Plants {

    private String type;
    private int health;
    private int maxHealth;
    private int damage;
    private String picturePath;
    private Picture picture;
    private int pictureWidth;
    private int pictureHeight;
    private int PlantsPosX;
    private int PlantsPosY;


    public Plants(PlantsBuilder builder) {
        this.type = builder.getType();
        this.health = builder.getHealth();
        this.maxHealth = builder.getMaxHealth();
        this.damage = builder.getDamage();
        this.picturePath = builder.getPicturePath();
    }

    public String getType() {
        return type;
    }

    /* ######## Added for placement ###### */
    public void show(int col, int row) throws IOException {
        //System.out.println("The picture is at position " + this.PlantsPosX + ", " + this.PlantsPosY);
        this.PlantsPosX = col;
        this.PlantsPosY = row;
        picture = new Picture(col, row, this.picturePath);
        picture.draw();

    }

}
