package com.learning.zombie;

import com.learning.behaviour.Zombies;

import java.util.Objects;

public class ZombieFactory {

    public static Zombie createZombie(Zombie zombie) {
        if(Objects.equals(zombie.getType(), Zombies.ZOMBIES_TYPE_1.getType())) {
            return createCivilZombie();
        }else {
            return createMilitarZombie();
        }

    }

    private static Zombie createCivilZombie() {
        return new ZombieBuilder()
                .setDamage(5)
                .setType("Civil")
                .setHealth(100)
                .setMaxHealth(200)
                .setSpeed(0.5)
                .setPicturePath("./resources/images/zombieCivilS.png")
                .build();
    }

    private static Zombie createMilitarZombie() {
        return new ZombieBuilder()
                .setDamage(5)
                .setType("Militar")
                .setHealth(150)
                .setMaxHealth(200)
                .setSpeed(0.3)
                .setPicturePath("./resources/images/zombieMilitarS.png")
                .build();
    }

}

