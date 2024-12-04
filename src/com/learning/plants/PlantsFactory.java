package com.learning.plants;


public class PlantsFactory {


    public static Plants createPlants(Plants planta) {
        if ("peaCannon".equalsIgnoreCase(planta.getType())) {
            return createPeaCannon();
        }
        throw new IllegalArgumentException("Tipo de planta não suportado: " + planta.getType());
    }


    private static Plants createPeaCannon() {
        return new PlantsBuilder()
                .setDamage(20)
                .setType("peaCannon")
                .setHealth(100)
                .setMaxHealth(100) // Define a vida atual como igual à máxima
                .setPicturePath("resources/images/peashootersmall1.png")
                .build();
    }
}
