package com.learning.play;

import com.codeforall.online.simplegraphics.pictures.Picture;
import com.learning.behaviours.GameData;
import com.learning.zombies.Zombie;
import com.learning.zombies.ZombieBuilder;
import com.learning.zombies.ZombieFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Game game;
    private List<Zombie> allZombies;
    private int numberOfZombies = 0;
    private int posX;
    private int posY;

    public void gameMenusInit(Picture picture) throws InterruptedException {
        picture.load("resources/images/playground.png");
        Picture topMenu = new Picture(GameData.get(GameData.GRID_GRIDSTART_X), 0, "resources/images/topMenu.png");
        topMenu.draw();
        addZombies(2);

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

}
