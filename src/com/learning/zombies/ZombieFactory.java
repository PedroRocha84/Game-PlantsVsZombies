package com.learning.zombies;

public class ZombieFactory {

    public static Zombie createZombie(Zombie zombie) {
        if ("Civil".equals(zombie.getType())) {
            return createCivilZombie();
        }
        return createMilitarZombie();
    }

    private static Zombie createCivilZombie() {
        return new ZombieBuilder()
                .setDamage(10)
                .setType("Civil")
                .setHealth(100)
                .setMaxHealth(200)
                .setSpeed(0.3)
                .setPicturePath("./resources/images/zombieCivilS.png")
                .build();
    }

    private static Zombie createMilitarZombie() {
        return new ZombieBuilder()
                .setDamage(10)
                .setType("Militar")
                .setHealth(100)
                .setMaxHealth(200)
                .setSpeed(0.2)
                .setPicturePath("./resources/images/zombieMilitarS.png")
                .build();
    }

}

