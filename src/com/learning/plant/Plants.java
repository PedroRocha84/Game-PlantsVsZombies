package com.learning.plant;

import com.codeforall.online.simplegraphics.pictures.Picture;

public class Plants {

    private String type;
    private int health;
    private int maxHealth;
    private int damage;
    private String picturePath;
    private Picture picture;
    private int pictureWidth;
    private int pictureHeight;
    private int plantsPosX;
    private int plantsPosY;


    public Plants(PlantsBuilder builder) {
        this.type = builder.getType();
        this.health = builder.getHealth();
        this.maxHealth = builder.getMaxHealth();
        this.damage = builder.getDamage();
        this.picturePath = builder.getPicturePath();
        this.plantsPosX = builder.getPositionX();
        this.plantsPosY = builder.getPositionY();

    }

    // Getters
    public int getHealth() {
        return health;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getPlantsPosX(){
        return plantsPosX;
    }

    public int getPlantsPosY(){
        return this.plantsPosY;
    }

    // Setters
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {this.health = health;}

    public void setPlantsPosX(int plantsPosX) { this.plantsPosX = plantsPosX;}

    public void setPlantsPosY(int plantsPosY) {this.plantsPosY = plantsPosY;}

    public void setPosition(int plantsPosX, int plantsPosY) {
        this.plantsPosX = plantsPosX;
        this.plantsPosY = plantsPosY;

        this.picture = new Picture(plantsPosX, plantsPosY, this.picturePath);
        picture.draw();

    }

    public String getType() {
        return type;
    }


    public void getInjured(int damage){
        this.health -= damage;
    }

    public void delete(){
        this.picture.delete();
    }

    @Override
    public String toString() {
        return "Plants{" +
                "type='" + type + '\'' +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", damage=" + damage +
                ", picturePath='" + picturePath + '\'' +
                ", positionX=" + plantsPosX +
                ", positionY=" + plantsPosY +
                '}';
    }

}
