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

    // Retrieve grid boundaries from enum
    private int gridStartX = MenuControl.GRID_GRIDSTART_X.getValue();
    private int gridStartY = MenuControl.GRID_GRIDSTART_Y.getValue();
    private int gridEndX = MenuControl.GRID_GRIDEND_X.getValue();
    private int gridEndY = MenuControl.GRID_GRIDEND_Y.getValue();

    // Cell dimensions
    int cellWidth = IMAGE_WIDTH;
    int cellHeight = IMAGE_HEIGTH;

    private int posX;
    private int posY;
    private int level;
    private int distanceToZombie = 30;
    private int numberOfZombies;
    private int lanesToPlaceZombies;

    private String zombieType;

    private Mouse mouse;
    private Picture playground;
    private Plants createdPlants;
    private Plants plant;

    public Game(int level) throws FileNotFoundException, InterruptedException {
        this.level = level;
        allZombies = new ArrayList<>();
        startGame(this.level);
    }

    public void startGame(int level) throws FileNotFoundException, InterruptedException {

        String backgroundPath = GameLevel.getGameBackground(level);
        activateBackground(backgroundPath);

        addZombies(level);

        showPlants();

        startGameLoop();

    }

    public void createAndShootPeas(int centerX, int centerY) throws FileNotFoundException, InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Pea pea = new Pea();
            try {
                pea.addNewPea(centerX,centerY);
                allPeas.add(pea);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0, 3, TimeUnit.SECONDS); // Initial delay 0s, repeat every 2s

    }

    public void checkCollisions() {
        for (int i = 0; i < allPeas.size(); i++) {
                Pea pea = allPeas.get(i);
            for (int j = 0; j < allZombies.size(); j++) {
                Zombie zombie = allZombies.get(j);
                System.out.println("Pea position: " + pea.getPositionX());
                System.out.println("Zombie position " + zombie.getZombiePositionX());


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

        setNumberZombies(level);

        for (int numberOfColumn = 0; numberOfColumn < numberOfZombies; numberOfColumn++) {
            createZombies(numberOfColumn);
        }
    }

    public void showPlants() {
        String plantType = "peaCannon";

        plant = new PlantsBuilder().setType(plantType).build();
        createdPlants = PlantsFactory.createPlants(plant);

        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }

    public void startGameLoop() throws InterruptedException {

        while(true){
            moveAllPeas(allPeas);
            Thread.sleep(50);
            moveAllZombies(allZombies);
            checkCollisions();
        }
    }

    public void setNumberZombies(int level){
        // Create number of zombies comparing with the level
        if(level == 1) {
            System.out.println("level is : " + level);
            numberOfZombies = 1;
            lanesToPlaceZombies = 2;
        }else if (level == 2) {
            numberOfZombies = 6;
            lanesToPlaceZombies = 3;
        } else if(level == 3) {
            numberOfZombies = 8;
            lanesToPlaceZombies = 5;
        }
    }

    public void createZombies(int numberOfColumn){
        double numRandom = Math.random();
        if (numRandom <= 0.8) {
            zombieType = Zombies.ZOMBIES_TYPE_1.getType();
        } else {
            zombieType = Zombies.ZOMBIES_TYPE_2.getType();
        }

        posX = MenuControl.get(MenuControl.GRID_GRIDSTART_X)
                + MenuControl.ZOMBIES_POSITION_X.getValue();
        posY = MenuControl.get(MenuControl.GRID_GRIDSTART_Y)
                + (MenuControl.ZOMBIES_POSITION_Y.getValue() * numberOfColumn);

        Zombie initialZombie = new ZombieBuilder().setType(zombieType).build();
        Zombie createdZombie = ZombieFactory.createZombie(initialZombie);

        System.out.println(numberOfColumn);
//        System.out.println("New Zombie created at X " + posX + " and Y " + posY);

        createdZombie.addNewZombie(posX, posY);
        allZombies.add(createdZombie);
    }

    public void setPlantPosition(int mouseX, int mouseY) {

        if (mouseX >= gridStartX && mouseX <= gridEndX && mouseY >= gridStartY && mouseY <= gridEndY) {
            int relativeX = mouseX - gridStartX;
            int relativeY = mouseY - gridStartY;

            int column = relativeX / cellWidth;
            int row = relativeY / cellHeight;

            int cellX = gridStartX + (column * cellWidth);
            int cellY = gridStartY + (row * cellHeight);

            int centerX = cellX + cellWidth / 2  ;
            int centerY = cellY + cellHeight / 2 - 18 ;

            if(mouseX > 260 && mouseX < 300) {
                centerX -= 10;
            } else if(mouseX > 300 && mouseX < 400) {
                centerX -= 12;
            } else if(mouseX > 400) {
                centerX -= 20;
            }

            try {
                createdPlants.addNewPlant(centerX,centerY);
                createAndShootPeas(centerX, centerY);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY() - 39;

            setPlantPosition(mouseX,mouseY);

        }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}


