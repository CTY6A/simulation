package com.stubedavd;

import com.stubedavd.actions.*;
import com.stubedavd.elements.*;

import java.util.ArrayList;

public class Simulation {
    private final static int EXTINCTION_LIMIT = 50;
    private final static int ROCK_SPAWN_RATE = 15;
    private final static int TREE_SPAWN_RATE = 15;
    private final static int GRASS_SPAWN_RATE = 15;
    private final static int HERBIVORE_SPAWN_RATE = 15;
    private final static int PREDATOR_SPAWN_RATE = 5;
    private final static int DELAY = 300;

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final ArrayList<Action> initActions;
    private final ArrayList<Action> turnActions;

    private int turnCount;
    private boolean paused = false;


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

        // start simulation infinity loop
        while (true) {
            Thread.sleep(DELAY); // small delay to reduce CPU load

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

    public void pauseSimulation() {
        paused = !paused;
    }
}