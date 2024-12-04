package com.learning.plants;

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
}
