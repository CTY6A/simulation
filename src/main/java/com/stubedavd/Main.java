package com.stubedavd;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation(40, 10);

        Thread controlThread = new Thread(() -> {
            try {
                while (true) {
                    if (System.in.available() > 0) {
                        int keyCode = System.in.read();

                        // space (ASCII 32)
                        if (keyCode == 32) {
                            simulation.pauseSimulation();
                        }
                        // 's' or 'S' (ASCII 115 or 83)
                        else if (keyCode == 115 || keyCode == 83) {
                            simulation.stopSimulation();
                            break;
                        }
                    }
                    Thread.sleep(100); // small delay to reduce CPU load
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        controlThread.start();

        simulation.startSimulation();
    }
}