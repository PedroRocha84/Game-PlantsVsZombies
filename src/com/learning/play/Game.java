package com.learning.play;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviours.GameData;
import com.learning.plants.PlantsBuilder;
import com.learning.plants.PlantsFactory;
import com.learning.zombies.Zombie;
import com.learning.zombies.ZombieBuilder;
import com.learning.zombies.ZombieFactory;
import com.learning.plants.Plants;

import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements MouseHandler {

    private Game game;
    private List<Zombie> allZombies;
    private int numberOfZombies = 0;
    private int posX;
    private int posY;
    private Mouse mouse;
    private Plants createdPlants;

    public Game (){

    }

    public void gameMenusInit(Picture picture) throws IOException {
        picture.load("resources/images/playground.png");
        Picture topMenu = new Picture(GameData.get(GameData.GRID_GRIDSTART_X), 0, "resources/images/topMenu.png");
        topMenu.draw();
        addZombies(2);
        showPlants();

//        while (true){
//            moveAllZombies(allZombies);
//            Thread.sleep(100);
//        }

    }

    public void addZombies(int numberOfZombies){
        allZombies = new ArrayList<>();
        /**
         * Step 3: Random object for random number generation
         */
        Random random = new Random();

        // Step 4: Loop to create zombies
        for (int i = 0; i < numberOfZombies; i++) {
            // Randomly decide the zombie type
            String zombieType;

            double numRandom = Math.random();
            if (numRandom <= 0.5) {
                zombieType = "Civil";
            } else {
                zombieType = "Militar";
            }

            // Use the factory to create a fully configured zombie
            Zombie initialZombie = new ZombieBuilder().setType(zombieType).build();
            System.out.println(zombieType);
            Zombie createdZombie = ZombieFactory.createZombie(initialZombie);
            posX = GameData.get(GameData.GRID_GRIDSTART_X) + 600;
            posY = GameData.get(GameData.GRID_GRIDSTART_Y) + (60 * (i));
            createdZombie.show(posX, posY);

            // Add to the list
            allZombies.add(createdZombie);
        }
    }

    public void moveAllZombies(List<Zombie> allZombies) {
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = this.allZombies.get(i);
            zombie.move();
//            int xPos = zombie.getX();  // Obtém a posição X
//            int yPos = zombie.getY();  // Obtém a posição Y
            //System.out.println("Zombie " + i + " position: (" + xPos + ", " + yPos + ")");
        }
    }

    public void showPlants() {
        String plantType = "peaCannon";

        Plants plant = new PlantsBuilder().setType(plantType).build();
        createdPlants = PlantsFactory.createPlants(plant);
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY() - 39;

        // Retrieve grid boundaries from enum
        int gridStartX = GameData.GRID_GRIDSTART_X.getValue();
        int gridStartY = GameData.GRID_GRIDSTART_Y.getValue();
        int gridEndX = GameData.GRID_GRIDEND_X.getValue();
        int gridEndY = GameData.GRID_GRIDEND_Y.getValue();

        // Cell dimensions
        int cellWidth = 60;
        int cellHeight = 65;

        System.out.println("Mouse Click Position: (" + mouseX + ", " + mouseY + ")");
//        System.out.println("Grid Start: (" + gridStartX + ", " + gridStartY + ")");
//        System.out.println("Grid End: (" + gridEndX + ", " + gridEndY + ")");
//        System.out.println("Cell Size: " + cellWidth + " x " + cellHeight);

        // Check if the click is within grid boundaries
        if (mouseX >= gridStartX && mouseX <= gridEndX && mouseY >= gridStartY && mouseY <= gridEndY) {
            // Calculate relative position within the grid
            int relativeX = mouseX - gridStartX;
            int relativeY = mouseY - gridStartY;

//            System.out.println("Relative Click Position: (" + relativeX + ", " + relativeY + ")");

            // Calculate the column and row the click is in
            int column = relativeX / cellWidth;  // integer division
            int row = relativeY / cellHeight;    // integer division

//            System.out.println("Calculated Cell: Column " + column + ", Row " + row);

            // Calculate the top-left corner of the cell
            int cellX = gridStartX + (column * cellWidth);
            int cellY = gridStartY + (row * cellHeight);

            System.out.println("Cell Top-Left Corner: (" + cellX + ", " + cellY + ")");

            // Calculate the center of the cell
            int centerX = cellX + cellWidth / 2  ;
            int centerY = cellY + cellHeight / 2 - 18 ;
            System.out.println("center x : " + centerX);
            if(mouseX > 260 && mouseX < 300) {
                centerX -= 10;
            } else if(mouseX > 300 && mouseX < 400) {
                centerX -= 12;
            } else if(mouseX > 400) {
                centerX -= 20;
            } else if(mouseX > 400 && mouseX < 600) {
                centerX -= 30;
            }
            System.out.println("center x : " + centerX);
            createdPlants.show(centerX,centerY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
