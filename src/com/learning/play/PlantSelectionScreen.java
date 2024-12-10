package com.learning.play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlantSelectionScreen extends JFrame {
    private ArrayList<String> selectedPlants = new ArrayList<>();
    private final int MAX_SELECTIONS = 6;

    public PlantSelectionScreen() {
        setTitle("Select Your Plants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel for plant icons
        JPanel plantGrid = new JPanel(new GridLayout(4, 8, 10, 10));
        String[] plantNames = {"Sunflower", "Pea Shooter", "Cherry Bomb", "Wall-nut", "Snow Pea"};
        for (String plant : plantNames) {
            JButton plantButton = new JButton(plant);
            plantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedPlants.size() < MAX_SELECTIONS) {
                        selectedPlants.add(plant);
                        System.out.println(plant + " selected!");
                    } else {
                        JOptionPane.showMessageDialog(null, "You can only select up to " + MAX_SELECTIONS + " plants!");
                    }
                }
            });
            plantGrid.add(plantButton);
        }

        // Panel for selected plants
        JPanel selectionPanel = new JPanel();
        JLabel selectedPlantsLabel = new JLabel("Selected Plants: ");
        selectionPanel.add(selectedPlantsLabel);

        // Buttons for actions
        JPanel actionPanel = new JPanel();
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            if (selectedPlants.size() > 0) {
                System.out.println("Game starting with: " + selectedPlants);
                // Start the game with the selected plants
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Please select at least one plant!");
            }
        });

        JButton almanacButton = new JButton("Almanac");
        almanacButton.addActionListener(e -> System.out.println("Opening Almanac..."));

        JButton shopButton = new JButton("Shop");
        shopButton.addActionListener(e -> System.out.println("Opening Shop..."));

        actionPanel.add(almanacButton);
        actionPanel.add(shopButton);
        actionPanel.add(playButton);

        // Add panels to frame
        add(plantGrid, BorderLayout.CENTER);
        add(selectionPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PlantSelectionScreen();
    }
}
