package com.learning.behaviour;

public enum Zombies {

    ZOMBIES_TYPE_1("Civil"),
    ZOMBIES_TYPE_2("Militar");

    private String type;

    Zombies(String militar) {
        this.type = militar;
    }

    public String getType() {
        return type;
    }
}
