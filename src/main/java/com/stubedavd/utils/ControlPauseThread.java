package com.stubedavd.utils;

import com.stubedavd.Simulation;

import java.io.IOException;

public class ControlPauseThread extends Thread {
    private final static int THREAD_SLEEP = 300;
    private final static char PAUSE_KEY_CODE_1 = 10;
    private final static char PAUSE_KEY_CODE_2 = 13;

    public ControlPauseThread(Simulation simulation) {
        super(() -> {
            while (true) {
                try {
                    if (System.in.available() > 0) {
                        int keyCode = System.in.read();

                        if (isPausePressed(keyCode)) {
                            simulation.pauseSimulation();
                        }
                    }
                    Thread.sleep(THREAD_SLEEP);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static boolean isPausePressed(int keyCode) {
        return keyCode == PAUSE_KEY_CODE_1 || keyCode == PAUSE_KEY_CODE_2;
    }
}
