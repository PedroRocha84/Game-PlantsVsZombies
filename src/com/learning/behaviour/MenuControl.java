package com.learning.behaviour;

public enum MenuControl {
    // START MENU INFO
    MENU_BUTTON_X(155),
    MENU_BUTTON_Y(577),
    MENU_BUTTON_WIDTH(491),
    MENU_BUTTON_HEIGHT(51),

    // GAME GRID INFO
    GRID_GRIDSTART_X(170),
    GRID_GRIDSTART_Y(79),
    GRID_GRIDEND_X(700),
    GRID_GRIDEND_Y(400),

    GRID_CANVAS_SIZE_X_MAX(810),
    GRID_CANVAS_SIZE_Y_MAX(610),

    ZOMBIES_POSITION_X(600),
    ZOMBIES_POSITION_Y(60),

    PEAS_POSITION_ADJUSTMENT_X(30),
    PEAS_POSITION_ADJUSTMENT_Y(3),

    GAMESTATS_POS_X(10),
    GAMESTATS_POS_Y(10);

    private final int value;

    MenuControl(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int get(MenuControl data) {
        return data.getValue();
    }
}
