package com.learning.play;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import com.codeforall.online.simplegraphics.mouse.MouseHandler;
import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviour.GameLevel;
import com.learning.behaviour.MenuControl;
import com.learning.pea.Pea;
import com.learning.plant.PlantsBuilder;
import com.learning.plant.PlantsFactory;
import com.learning.zombie.Zombie;
import com.learning.zombie.ZombieBuilder;
import com.learning.zombie.ZombieFactory;
import com.learning.plant.Plants;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game implements MouseHandler {

    private List<Zombie> allZombies = new ArrayList<>();
    private List<Pea> allPeas = new ArrayList<>();
    private List<Plants> allPlants = new ArrayList<>();

    private static int IMAGE_WIDTH = 60;
    private static int IMAGE_HEIGTH = 65;
    private static int tollerance = 30;

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
    private int rowNumber;

    private String zombieType;
    private String backgroundPath;

    private Mouse mouse;
    private Picture playground;
    private Plants createdPlants;
    private Plants plant;
    private SoundPlayer soundPlayer;

    private MenuButton button;
    private NewMenu newMenu;
    private boolean isPaused = false;

    public Game(int level) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        this.level = level;
        this.button = new MenuButton();
        this.newMenu = new NewMenu();
        startGame(this.level);



    }

    public void startGame(int level) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {

        backgroundPath= GameLevel.getGameBackground(level);
        activateBackground(backgroundPath);

        GameStats gameStats = new GameStats();
        gameStats.showMainText(level);

        String filePath = "resources/sounds/soundtrack.wav";
        soundPlayer  = new SoundPlayer(filePath);
        soundPlayer.play();


        addZombies(level);

        showPlants();

        button.showMenu();

        startGameLoop();



    }

    public void gameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        soundPlayer.stop();

        GameStats gameStats = new GameStats();
        gameStats.gameOver();
    }

    public void createAndShootPeas(int centerX, int centerY, Plants shootingPlant) throws FileNotFoundException, InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            // Only shoot if the plant is still alive
            if (shootingPlant.getHealth() > 0) {
                Pea pea = new Pea();
                try {
                    pea.addNewPea(centerX, centerY);
                    allPeas.add(pea);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                scheduler.shutdown(); // Stop shooting if plant dies
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void checkCollisions() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Check collision pea with zombie
        for (int i = allPeas.size() - 1; i >= 0; i--) {
            Pea pea = allPeas.get(i);
            for (int j = allZombies.size() - 1; j >= 0; j--) {
                Zombie zombie = allZombies.get(j);

                int peaX = pea.getPositionX();
                int peaY = pea.getPositionY();
                int zombieX = zombie.getZombiePositionX();
                int zombieY = zombie.getZombiePositionY();

                if (Math.abs(peaY - zombieY) <= tollerance) {
                    if ((peaX < zombieX + distanceToZombie)
                            && (peaX + distanceToZombie > zombieX)) {
                        System.out.println("Collision detected!");

                        //playCollisionSound();


                        zombie.setInjury(pea.getDamage());
                        System.out.println("Zombie health: " + zombie.getHealth());
                        if(zombie.getHealth() <= 0){
                            zombie.delete();
                            allZombies.remove(zombie);
                        }
                        allPeas.remove(i);
                        pea.delete();
                        break; // Exit inner loop after removing pea
                    }
                }
            }
        }


        // Check collision Zombie with Plant
        for (int i = allZombies.size() - 1; i >= 0; i--) {
            Zombie zombie = allZombies.get(i);

            for (int j = allPlants.size() - 1; j >= 0; j--) {
                Plants plant = allPlants.get(j);

                if (Math.abs(zombie.getZombiePositionY() - plant.getPlantsPosY()) <= 20) {
                    if (Math.abs(zombie.getZombiePositionX() - plant.getPlantsPosX()) <= 40 && plant.getHealth() > 0) {
                        System.out.println("Zombie collided with Plant");
                        zombie.stopMove();
                        plant.getInjured(zombie.getDamage());
                        System.out.println("Plant life: " + plant.getHealth());
                    }

                    // When plant dies, remove it and allow zombie to move
                    if (plant.getHealth() <= 0) {
                        allPlants.remove(j);
                        plant.delete();
                        zombie.setSpeed(0.4);

                        // Stop shooting peas for this plant
                        removePeasForPlant(plant);
                    }
                }
            }
        }

        // Check game over condition
        for (int a = allZombies.size() - 1; a >= 0; a--) {
            Zombie zombie = allZombies.get(a);
            if (zombie.getZombiePositionX() <= MenuControl.GRID_GRIDSTART_X.getValue() - tollerance) {
                allZombies.remove(a);
                zombie.delete();
                isPaused = true;
                gameOver();
            }
        }
    }



//    private void playCollisionSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        String soundFilePath = "resources/sounds/hit.wav";
//        SoundPlayer soundPlayer = new SoundPlayer(soundFilePath);
//        soundPlayer.play();
//        //espera 1segundo
//        soundPlayer.stop();
//    }




    // New method to remove peas associated with a dead plant
    private void removePeasForPlant(Plants deadPlant) {
        // Remove peas shot from the specific plant
        allPeas.removeIf(pea -> {
            if (Math.abs(pea.getPositionY() - deadPlant.getPlantsPosY()) <= 20) {
                pea.delete(); // Delete the pea graphic
                return true;
            }
            return false;
        });
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

        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }

    public void startGameLoop() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        while(true){
            if (isPaused) {
                Thread.sleep(50);
                continue;
            }
            checkCollisions();
            moveAllPeas();
            moveAllZombies();
            Thread.sleep(50);
        }
    }

    public void setNumberZombies(int level){
        // Create number of zombies comparing with the level
        if(level == 1) {
            System.out.println("level is : " + level);
            numberOfZombies = 3;
            lanesToPlaceZombies = 2;
        }else if (level == 2) {
            numberOfZombies = 10;
            lanesToPlaceZombies = 3;
        } else if(level == 3) {
            numberOfZombies = 15;
            lanesToPlaceZombies = 5;
        }
    }

    public void createZombies(int rowNumber){

        Zombie initialZombie = new ZombieBuilder().setType(zombieType).build();
        Zombie createdZombie = ZombieFactory.createZombie(initialZombie);

        createdZombie.addNewZombie(rowNumber);
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

            int centerX = cellX + cellWidth / 2;
            int centerY = cellY + cellHeight / 2 - 18;

            if (mouseX > 260 && mouseX < 300) {
                centerX -= 10;
            } else if (mouseX > 300 && mouseX < 400) {
                centerX -= 12;
            } else if (mouseX > 400) {
                centerX -= 20;
            }

            try {
                String plantType = "peaCannon";

                plant = new PlantsBuilder().setType(plantType).build();
                Plants newPlant = PlantsFactory.createPlants(plant);

                newPlant.setPosition(centerX, centerY); // Assuming a method to set position exists
                allPlants.add(newPlant);

                createAndShootPeas(centerX, centerY, newPlant);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveAllPeas() {
        for (int i = 0; i < allPeas.size(); i++) {
            Pea pea = allPeas.get(i);
            pea.movePea();
        }

    }

    public void moveAllZombies() {
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = allZombies.get(i);
            zombie.move();
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        System.out.println("Mouse clicked at: " + mouseX + ", " + mouseY);


        if (isPaused) {
            System.out.println("Game is paused. Cannot create plants.");


            int areaStartX = 276;
            int areaStartY = 370;
            int areaEndX = 571;
            int areaEndY = 430;


            System.out.println("Checking click within area: (" + areaStartX + ", " + areaStartY + ") to (" + areaEndX + ", " + areaEndY + ")");
            if (mouseX >= areaStartX && mouseX <= areaEndX && mouseY >= areaStartY && mouseY <= areaEndY) {
                System.out.println("Clicked within the defined area to resume!");
                isPaused = false;
                newMenu.closeMenu();
            } else {
                System.out.println("Clicked outside the resume area.");
            }

            return;
        }


        if (button.isOver(mouseEvent)) {
            System.out.println("Button clicked!");
            isPaused = true;
            newMenu.openNewMenu();
        }


        else if (isPaused && newMenu.isOver(mouseEvent)) {
            System.out.println("Clicked inside the menu. Closing menu.");
            newMenu.closeMenu();
        } else {

            if (!isPaused) {

                System.out.println("Plant placement at: " + mouseX + ", " + mouseY);
                setPlantPosition(mouseX, mouseY);
            }
        }
    }





    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}


