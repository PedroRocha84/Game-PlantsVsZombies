package com.learning.behaviour;

public enum GameLevel {

    GAME_LEVEL_1(2, "resources/images/playground1.png"),
    GAME_LEVEL_2(4, "resources/images/playground2.png"),
    GAME_LEVEL_3(6, "resources/images/playground3.png");

    private final int level;
    private final String path;

    GameLevel(int level, String path) {
        this.level = level;
        this.path = path;
    }

    public int getLevel() {
        return level;
    }

    public String getPath() {
        return path;
    }

    public static String getGameBackground(int level) {
        return GameLevel.values()[level].getPath();
    }

}
