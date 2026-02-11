package com.stubedavd;

/**
 * Main entry point for the simulation application
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🌿 Starting 2D World Simulation 🌿");

        // Create simulation with 15x10 world
        Simulation simulation = new Simulation(15, 10);

        // Start the simulation
        simulation.startSimulation();

        // Run a few turns to demonstrate
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000); // Wait 1 second between turns
                simulation.nextTurn();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Simulation interrupted");
        }

        System.out.println("\nSimulation completed!");
    }
}