package com.stubedavd;

import java.io.IOException;

public class Main {
    private final static int DELAY = 300;
    private final static int MAP_WIDTH = 20;
    private final static int MAP_HEIGHT = 10;
    private final static char PAUSE_KEY_CODE_1 = 10;
    private final static char PAUSE_KEY_CODE_2 = 13;

    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation(MAP_WIDTH, MAP_HEIGHT);

        Thread controlPauseThread = new Thread(() -> {
            while (true) {
                try {
                    if (System.in.available() > 0) {
                        int keyCode = System.in.read();

                        if (isPausePressed(keyCode)) {
                            simulation.pauseSimulation();
                        }
                    }
                    Thread.sleep(DELAY); // small delay to reduce CPU load
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        controlPauseThread.start();

        simulation.startSimulation();
    }

    private static boolean isPausePressed(int keyCode) {
        return keyCode == PAUSE_KEY_CODE_1 || keyCode == PAUSE_KEY_CODE_2;
    }
}