package com.learning.zombie;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.MenuControl;
import com.learning.behaviour.Zombies;

public class Zombie {

    private String type;
    private String picturePath;
    private String zombieType;

    private double speed;

    private int health;
    private int maxHealth;
    private int damage;
    private int zombiePositionX;
    private int zombiePositionY;

    private Picture newZombiePicture;

    public Zombie(ZombieBuilder builder) {
        this.type = builder.getType();
        this.health = builder.getHealth();
        this.maxHealth = builder.getMaxHealth();
        this.speed = builder.getSpeed();
        this.damage = builder.getDamage();
        this.picturePath = builder.getPicturePath();
    }

    // Getters

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public int getZombiePositionX() {
        return zombiePositionX;
    }

    public int getZombiePositionY() {return zombiePositionY;}


    // Setters

    public void setType(String type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
        }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.health -= damage;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setInjury(int damage) {
        this.health -= damage;
    }

    public void addNewZombie(int rowNumber){
        double numRandom = Math.random();
        if (numRandom <= 0.8) {
            zombieType = Zombies.ZOMBIES_TYPE_1.getType();
        } else {
            zombieType = Zombies.ZOMBIES_TYPE_2.getType();
        }

        this.zombiePositionX = MenuControl.get(MenuControl.GRID_GRIDSTART_X)
                + MenuControl.ZOMBIES_POSITION_X.getValue();
        this.zombiePositionY = MenuControl.get(MenuControl.GRID_GRIDSTART_Y)
                + (MenuControl.ZOMBIES_POSITION_Y.getValue() * rowNumber);
        System.out.println("Zombie placed at : " + zombiePositionX + ", " + zombiePositionY);
        newZombiePicture = new Picture(zombiePositionX, zombiePositionY, this.picturePath);
        newZombiePicture.draw();
    }

    public void delete(){
        this.newZombiePicture.delete();
    }

    public void stopMove(){
        this.speed = 0;;
    }

    public void move(){
        newZombiePicture.translate(-2 * speed, 0);
        zombiePositionX = newZombiePicture.getX();
    }

    @Override
    public String toString() {
        return "Zombie{" +
                "type='" + type + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", speed=" + speed +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", damage=" + damage +
                ", zombieXposition=" + zombiePositionX +
                ", ZombieRow=" + zombiePositionY +
                ", newZombiePicture=" + newZombiePicture +
                '}';
    }
}
