package com.stubedavd;

import com.stubedavd.utils.ControlPauseThread;

public class Main {
    private final static int MAP_WIDTH = 20;
    private final static int MAP_HEIGHT = 10;

    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation(MAP_WIDTH, MAP_HEIGHT);

        ControlPauseThread controlPauseThread = new ControlPauseThread(simulation);
        controlPauseThread.start();

        simulation.startSimulation();
    }
}