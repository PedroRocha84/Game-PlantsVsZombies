package com.learning.plants;

public class PlantsBuilder {
    // Propriedades
    private String type;
    private int health;
    private int maxHealth;
    private int damage;
    private String picturePath;

    public Plants build() {
        return new Plants(this);
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public String getPicturePath() {
        return picturePath;
    }

    // Setters

    public PlantsBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public PlantsBuilder setHealth(int currentLife) {
        this.health = currentLife;
        return this;
    }

    public PlantsBuilder setMaxHealth(int maxLife) {
        this.maxHealth = maxLife;
        return this;
    }

    public PlantsBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public PlantsBuilder setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }
}


