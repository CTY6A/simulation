package com.stubedavd;

import com.stubedavd.actions.Action;
import com.stubedavd.actions.CreaturesMove;
import com.stubedavd.actions.EntityFactory;
import com.stubedavd.elements.*;

import java.util.ArrayList;

public class Simulation {
    private static volatile boolean running = true;
    private static volatile boolean paused = false;

    public static final int SPAWN_RATE = 10;

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final ArrayList<Action> initActions;
    private final ArrayList<Action> turnActions;

    private int turnCount;

    public Simulation(final int width, final int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer();
        this.initActions = new ArrayList<>(){{
            add(new EntityFactory(Rock::new, SPAWN_RATE));
            add(new EntityFactory(Tree::new, SPAWN_RATE));
            add(new EntityFactory(Grass::new, 1));
            add(new EntityFactory(Herbivore::new, 1));
            add(new EntityFactory(Predator::new, 0));
        }};
        this.turnActions = new ArrayList<>(){{
            add(new CreaturesMove());
        }};
        this.turnCount = 0;
    }

    public void startSimulation() throws InterruptedException {
        // create and start key controller
        KeyController keyController = new KeyController();
        keyController.start();

        // pre start simulation actions
        for (Action action : initActions) {
            action.perform(worldMap);
        }

        // start simulation infinity loop
        while (running) {
            Thread.sleep(300); // small delay to reduce CPU load

            // paused simulation infinity loop and rendering
            if (!paused) {
                // simulate and rendering one turn
                nextTurn();
            }
        }
    }

    private void nextTurn() throws InterruptedException {
        turnCount++;
        renderer.render(worldMap, turnCount);

        // every turn actions
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
    }

    static class KeyController {
        private Thread controlThread;

        public void start() {
            controlThread = new Thread(() -> {
                try {
                    while (running) {
                        if (System.in.available() > 0) {
                            int keyCode = System.in.read();

                            // space (ASCII 32)
                            if (keyCode == 32) {
                                paused = !paused;
                            }
                            // 's' or 'S' (ASCII 115 or 83)
                            else if (keyCode == 115 || keyCode == 83) {
                                running = false;
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
        }

        public void stop() {
            if (controlThread != null && controlThread.isAlive()) {
                controlThread.interrupt();
            }
        }
    }
}