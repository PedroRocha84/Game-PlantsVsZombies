package com.learning.zombies;

public class Zombie {

    private String type;
    private int health;
    private int maxHealth;
    private double speed;
    private int damage;
    private String picturePath;
    private Zombie zombie;

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

    public Zombie getZombie() {
        return zombie;
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
        this.damage = damage;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }
}
