package com.learning.play;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.GameLevel;
import com.learning.behaviour.MenuControl;
import com.learning.behaviour.Zombies;
import com.learning.pea.Pea;
import com.learning.plant.PlantsBuilder;
import com.learning.plant.PlantsFactory;
import com.learning.zombie.Zombie;
import com.learning.zombie.ZombieBuilder;
import com.learning.zombie.ZombieFactory;
import com.learning.plant.Plants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game implements MouseHandler {

    private List<Zombie> allZombies;
    private List<Pea> allPeas = new ArrayList<>();

    private static int IMAGE_WIDTH = 60;
    private static int IMAGE_HEIGTH = 65;

    private int posX;
    private int posY;
    private int level;
    private int numberOfZombies;
    private int lanesToPlaceZombies;

    private Mouse mouse;
    private Picture playground;
    private Plants createdPlants;
    private Plants plant;

    public Game(int level) throws FileNotFoundException, InterruptedException {
        this.level = level;
        startGame(this.level);

    }

    public void startGame(int level) throws FileNotFoundException, InterruptedException {

        String backgroundPath = GameLevel.getGameBackground(level);
        activateBackground(backgroundPath);

        addZombies(level);

        showPlants();

        startGameLoop();

    }

    public void startGameLoop() throws InterruptedException {

        while(true){
           moveAllPeas(allPeas);
           Thread.sleep(50);
            moveAllZombies(allZombies);
            checkColisions();
        }
    }

    public void createAndShootPeas(int centerX, int centerY) throws FileNotFoundException, InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Pea pea = new Pea();
            try {
                pea.addNewPea(centerX,centerY);
                allPeas.add(pea);
                System.out.println(allPeas.size());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0, 2, TimeUnit.SECONDS); // Initial delay 0s, repeat every 2s



    }

    public void checkColisions(){
        int distanceToZombie = 30;
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = allZombies.get(i);
            for (int j = 0; j < allPeas.size(); j++) {
            Pea pea = allPeas.get(j);

                if (Math.abs(pea.getPositionX() - zombie.getZombiePosX()) <= distanceToZombie) {
                    // Make the pea's image disappear
                    pea.getPicture().delete(); // Ensure you have a getter for the Picture field

                    // Remove the pea from the list
                    allPeas.remove(j);
                    j--; // Adjust the index since the list size has decreased
                    System.out.println("Zombie health:" + zombie.getHealth());
                    System.out.println(createdPlants.getDamage());
                    zombie.setDamage(createdPlants.getDamage());
                    System.out.println("Zombie health:" + zombie.getHealth());
                    System.out.println("Collision detected! Pea removed.");
                    break; // Exit the loop to avoid further checks for this pea
                }
            }
        }

    }

    public void activateBackground(String imagePath) throws FileNotFoundException {

            playground = new Picture(0, 0, imagePath);
            playground.draw();

            Picture topMenu = new Picture(MenuControl.get(MenuControl.GRID_GRIDSTART_X), 0, "resources/images/topMenu.png");
            topMenu.draw();

    }

    public void addZombies(int level){
        allZombies = new ArrayList<>();

        // Create number of zombies comparing with the level
        if(level == 1) {
            System.out.println("level is : " + level);
            numberOfZombies = 4;
            lanesToPlaceZombies = 2;
        }else if (level == 2) {
            numberOfZombies = 6;
            lanesToPlaceZombies = 3;
        } else if(level == 3) {
            numberOfZombies = 8;
            lanesToPlaceZombies = 5;
        }

        for (int i = 0; i < numberOfZombies; i++) {
            String zombieType;
            double numRandom = Math.random();
            if (numRandom <= 0.8) {
                zombieType = Zombies.ZOMBIES_TYPE_1.getType();
            } else {
                zombieType = Zombies.ZOMBIES_TYPE_2.getType();
            }

            Zombie initialZombie = new ZombieBuilder().setType(zombieType).build();
            Zombie createdZombie = ZombieFactory.createZombie(initialZombie);

            posX = MenuControl.get(MenuControl.GRID_GRIDSTART_X)
                    + MenuControl.ZOMBIES_POSITION_X.getValue();
            posY = MenuControl.get(MenuControl.GRID_GRIDSTART_Y)
                    + (MenuControl.ZOMBIES_POSITION_Y.getValue() * i);

            System.out.println(posX + " " + posY);
            createdZombie.show(posX, posY);

            allZombies.add(createdZombie);
        }
    }

    public void moveAllPeas(List<Pea> allPeas) {
        for (int i = 0; i < allPeas.size(); i++) {
            Pea pea = this.allPeas.get(i);
            pea.movePea();
        }
    }

    public void moveAllZombies(List<Zombie> allZombies) {
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = this.allZombies.get(i);
            zombie.move();
        }
    }

    public void showPlants() {
        String plantType = "peaCannon";

        plant = new PlantsBuilder().setType(plantType).build();
        createdPlants = PlantsFactory.createPlants(plant);

        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY() - 39;

        // Retrieve grid boundaries from enum
        int gridStartX = MenuControl.GRID_GRIDSTART_X.getValue();
        int gridStartY = MenuControl.GRID_GRIDSTART_Y.getValue();
        int gridEndX = MenuControl.GRID_GRIDEND_X.getValue();
        int gridEndY = MenuControl.GRID_GRIDEND_Y.getValue();

        // Cell dimensions
        int cellWidth = IMAGE_WIDTH;
        int cellHeight = IMAGE_HEIGTH;

        //System.out.println("Mouse Click Position: (" + mouseX + ", " + mouseY + ")");

        if (mouseX >= gridStartX && mouseX <= gridEndX && mouseY >= gridStartY && mouseY <= gridEndY) {

            int relativeX = mouseX - gridStartX;
            int relativeY = mouseY - gridStartY;

            int column = relativeX / cellWidth;
            int row = relativeY / cellHeight;

            int cellX = gridStartX + (column * cellWidth);
            int cellY = gridStartY + (row * cellHeight);

            //System.out.println("Cell Top-Left Corner: (" + cellX + ", " + cellY + ")");

            int centerX = cellX + cellWidth / 2  ;
            int centerY = cellY + cellHeight / 2 - 18 ;
            System.out.println("center x : " + centerX);
            if(mouseX > 260 && mouseX < 300) {
                centerX -= 10;
            } else if(mouseX > 300 && mouseX < 400) {
                centerX -= 12;
            } else if(mouseX > 400) {
                centerX -= 20;
            }

            try {
                createdPlants.show(centerX,centerY);
                createAndShootPeas(centerX, centerY);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}


