package com.learning.plant;

import com.codeforall.online.simplegraphics.pictures.Picture;

public class Plants {
    private String type;
    private String picturePath;
    private int health;
    private int maxHealth;
    private int damage;
    private int plantsPosX;
    private int plantsPosY;
    private Picture picture;

    public Plants(PlantsBuilder builder) {
        this.type = builder.getType();
        this.health = builder.getHealth();
        this.maxHealth = builder.getMaxHealth();
        this.damage = builder.getDamage();
        this.picturePath = builder.getPicturePath();
        this.plantsPosX = builder.getPositionX();
        this.plantsPosY = builder.getPositionY();
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

    // Getters
    public int getHealth() {
        return health;
    }
    public int getPlantsPosX(){
        return plantsPosX;
    }
    public int getPlantsPosY(){
        return this.plantsPosY;
    }
    public String getType() {return type;}
    public void getInjured(int damage){
        this.health -= damage;
    }
    public void setPosition(int plantsPosX, int plantsPosY) {
        this.plantsPosX = plantsPosX;
        this.plantsPosY = plantsPosY;
        this.picture = new Picture(plantsPosX, plantsPosY, this.picturePath);
        picture.draw();
    }
}
