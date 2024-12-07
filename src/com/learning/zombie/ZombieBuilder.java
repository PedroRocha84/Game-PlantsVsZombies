package com.learning.zombie;

public class ZombieBuilder {

    private String type;
    private int health;
    private int maxHealth;
    private double speed;
    private int damage;
    private String picturePath;

    public Zombie build(){
        return new Zombie(this);
    }

    //Setters
    public ZombieBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ZombieBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public ZombieBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public ZombieBuilder setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public ZombieBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public ZombieBuilder setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    //Getters

    public String getType() { return type;}

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

    public String getPicturePath() { return picturePath; }

}
