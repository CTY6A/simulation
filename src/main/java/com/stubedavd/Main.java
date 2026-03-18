package com.stubedavd;

public class Main {
    private final static int MAP_WIDTH = 20;
    private final static int MAP_HEIGHT = 10;

    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation(MAP_WIDTH, MAP_HEIGHT);
        simulation.startSimulation();
    }
}