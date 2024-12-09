package com.learning.plant;


public class PlantsFactory {


    public static Plants createPlants(Plants planta) {
        if (planta == null || planta.getType() == null) {
            throw new IllegalArgumentException("Planta or its type cannot be null");
        }

        if ("peaCannon".equalsIgnoreCase(planta.getType())) {
            return createPeaCannon();
        }
        throw new IllegalArgumentException("Tipo de planta não suportado: " + planta.getType());
    }



    private static Plants createPeaCannon() {
        return new PlantsBuilder()
                .setDamage(10)
                .setType("peaCannon")
                .setHealth(100)
                .setMaxHealth(100) // Define a vida atual como igual à máxima
                .setPicturePath("resources/images/peashootersmall1.png")
                .setPositionX(0)
                .setPositionY(0)
                .build();
    }
}
