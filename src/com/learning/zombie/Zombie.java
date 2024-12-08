package com.learning.zombie;

import com.codeforall.online.simplegraphics.pictures.Picture;

public class Zombie {

    private String type;
    private String picturePath;

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

    public void addNewZombie(int xPosition, int yPosition){
        this.zombiePositionX = xPosition;
        this.zombiePositionY = yPosition;
        System.out.println("Zombie placed at : " + xPosition + ", " + yPosition);
        newZombiePicture = new Picture(xPosition, yPosition, this.picturePath);
        newZombiePicture.draw();

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
