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

    public Game(int level) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        this.level = level;
        startGame(this.level);
    }

    public void startGame(int level) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {

        backgroundPath= GameLevel.getGameBackground(level);
        activateBackground(backgroundPath);

        GameStats gameStats = new GameStats();
        gameStats.showMainText(level);

        String filePath = "resources/sounds/soundtrack.wav";
       // soundPlayer  = new SoundPlayer(filePath);
       // soundPlayer.play();

        addZombies(level);

        showPlants();

        startGameLoop();

    }

    public void gameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        soundPlayer.stop();

        GameStats gameStats = new GameStats();
        gameStats.gameOver();
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

        }, 0, 5, TimeUnit.SECONDS); // Initial delay 0s, repeat every 2s
    }

    public void checkCollisions() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Check colision pea with zombie
        for (int i = 0; i < allPeas.size(); i++) {
                Pea pea = allPeas.get(i);
            for (int j = 0; j < allZombies.size(); j++) {
                Zombie zombie = allZombies.get(j);

                //TODO - VALIDAR CODIGO
                /*if (zombie.getHealth() == 0) {
                    allZombies.remove(j);
                    zombie.delete();
                    int randomRowNumber = (int) (Math.random() * lanesToPlaceZombies) + 1;
                    createZombies(randomRowNumber);
                }*/

                int peaX = pea.getPositionX();
                int peaY = pea.getPositionY();
                int zombieX = zombie.getZombiePositionX();
                int zombieY = zombie.getZombiePositionY();

                if (Math.abs(peaY - zombieY) <= tollerance) {
                    if ((peaX < zombieX + distanceToZombie)
                            && (peaX + distanceToZombie > zombieX)) {
                        System.out.println("Collision detected!");
                        zombie.setInjury(pea.getDamage());
                        allPeas.remove(i);
                        System.out.println(pea.toString());
                        pea.delete();

                    }

                }

            }
        }

        //Check colision Zombie with Plant
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = allZombies.get(i);

            for (int j = i; j < allPlants.size(); j++) {
                Plants plant = allPlants.get(j);

                System.out.println("Plant " + j + " : " + plant.getPlantsPosX());

                if (Math.abs(zombie.getZombiePositionY() - plant.getPlantsPosY()) <= 20) {
                    if(Math.abs(zombie.getZombiePositionX() - plant.getPlantsPosX()) <= 40 &&
                    plant.getHealth() > 0){
                        zombie.stopMove();
                        plant.getInjured(zombie.getDamage());
                        System.out.println("Plant life : " + plant.getHealth());

                    }
                }

                if (Math.abs(zombie.getZombiePositionY() - plant.getPlantsPosY()) <= 20) {
                    if (Math.abs(zombie.getZombiePositionX() - plant.getPlantsPosX()) <= 40 &&
                            plant.getHealth() == 0) {

                        allPlants.remove(j);
                        plant.delete();
                        zombie.setSpeed(0.4);

                    }
                }


            }

        }

        /*
        for (int k = 0; k < allPlants.size(); k++) {
            Plants planta = allPlants.get(k);

            int peaX = pea.getPositionX();
            int peaY = pea.getPositionY();
            int zombieX = zombie.getZombiePositionX();
            int zombieY = zombie.getZombiePositionY();

            int plantX = planta.getPlantsPosX();
            int plantY = planta.getPlantsPosY();

            if (planta.getHealth() != 0 && zombieX - plantX < 60 &&
                    Math.abs(plantY - zombieY) <= tollerance) {
                zombie.stopMove();
                planta.getInjured(zombie.getDamage());
                System.out.println(planta.getHealth());
            }

            if (planta.getHealth() == 0) {
                System.out.println("Plant health :" + planta.getHealth() + " 2");
                zombie.move();
                allPlants.remove(k);
                planta.delete();
            }
        }
*/


        for (int a = 0; a < allZombies.size(); a++) {
            Zombie zombie = allZombies.get(a);
            if (zombie.getZombiePositionX() <= MenuControl.GRID_GRIDSTART_X.getValue() - tollerance){

                allZombies.remove(a);
                zombie.delete();
                gameOver();
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

        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }

    public void startGameLoop() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        while(true){
            moveAllPeas(allPeas);
            Thread.sleep(50);
            moveAllZombies();
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
                String plantType = "peaCannon";

                plant = new PlantsBuilder().setType(plantType).build();
                Plants newPlant = PlantsFactory.createPlants(plant);

                newPlant.setPosition(centerX, centerY); // Assuming a method to set position exists
                allPlants.add(newPlant);

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

    public void moveAllZombies() {
        for (int i = 0; i < allZombies.size(); i++) {
            Zombie zombie = allZombies.get(i);
            zombie.move();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY() - 39;
        System.out.println(mouseX + " " + mouseY);
            setPlantPosition(mouseX,mouseY);

        }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}


