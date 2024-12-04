package com.learning.behaviours;

public enum GameData {
    // START MENU INFO
    MENU_BUTTON_X(155),
    MENU_BUTTON_Y(577),
    MENU_BUTTON_WIDTH(491),
    MENU_BUTTON_HEIGHT(51),

    // GAME GRID INFO
    GRID_GRIDSTART_X(180),
    GRID_GRIDSTART_Y(73),

    GRID_CANVAS_SIZE_X_MAX(810),
    GRID_CANVAS_SIZE_Y_MAX(610);
    private final int value;

    GameData(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int get(GameData data) {
        return data.getValue();
    }
}
