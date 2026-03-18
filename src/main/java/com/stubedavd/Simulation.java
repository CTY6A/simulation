package com.stubedavd;

import com.stubedavd.actions.*;
import com.stubedavd.elements.*;
import com.stubedavd.elements.creatures.Herbivore;
import com.stubedavd.elements.creatures.Predator;

import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    private final static int EXTINCTION_LIMIT = 50;

    private final static int ROCK_SPAWN_RATE = 10;
    private final static int TREE_SPAWN_RATE = 10;
    private final static int GRASS_SPAWN_RATE = 10;
    private final static int HERBIVORE_SPAWN_RATE = 10;
    private final static int PREDATOR_SPAWN_RATE = 5;

    private final static int DELAY = 300;
    private final static char PAUSE_KEY_CODE_1 = 10;
    private final static char PAUSE_KEY_CODE_2 = 13;

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final ArrayList<Action> initActions;
    private final ArrayList<Action> turnActions;

    private int turnCount;

    private volatile boolean paused = false;

    public Simulation(int width, int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer();
        this.initActions = new ArrayList<>(){{
            add(new EntityFactory(Rock::new, ROCK_SPAWN_RATE));
            add(new EntityFactory(Tree::new, TREE_SPAWN_RATE));
            add(new EntityFactory(Grass::new, GRASS_SPAWN_RATE));
            add(new EntityFactory(Herbivore::new, HERBIVORE_SPAWN_RATE));
            add(new EntityFactory(Predator::new, PREDATOR_SPAWN_RATE));
        }};
        this.turnActions = new ArrayList<>(){{
            add(new CreaturesMove());
            add(new HealthPointsChecker());
            add(new EntityFactory(Grass::new, GRASS_SPAWN_RATE, EXTINCTION_LIMIT));
            add(new EntityFactory(Herbivore::new, HERBIVORE_SPAWN_RATE, EXTINCTION_LIMIT));
            add(new EntityFactory(Predator::new, PREDATOR_SPAWN_RATE, EXTINCTION_LIMIT));
        }};
        this.turnCount = 0;
    }

    public void startSimulation() throws InterruptedException {
        // pre start simulation actions
        for (Action action : initActions) {
            action.perform(worldMap);
        }

        Thread controlPauseThread = getControlPauseThread();
        controlPauseThread.start();

        // start simulation
        while (true) {
            Thread.sleep(DELAY);
            if (!paused) {
                nextTurn();
            }
        }
    }

    private void nextTurn() {
        turnCount++;
        renderer.render(worldMap, turnCount);

        // every turn actions
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
    }

    private Thread getControlPauseThread() {
        return new Thread(() -> {
            while (true) {
                try {
                    if (System.in.available() > 0) {
                        int keyCode = System.in.read();

                        if (isPausePressed(keyCode)) {
                            pauseSimulation();
                        }
                    }
                    Thread.sleep(DELAY); // small delay to reduce CPU load
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static boolean isPausePressed(int keyCode) {
        return keyCode == PAUSE_KEY_CODE_1 || keyCode == PAUSE_KEY_CODE_2;
    }

    public void pauseSimulation() {
        paused = !paused;
    }
}