package com.stubedavd;

import com.stubedavd.actions.Action;
import com.stubedavd.actions.CreaturesMove;
import com.stubedavd.actions.EntityFactory;
import com.stubedavd.actions.HealthPointsChecker;
import com.stubedavd.elements.*;

import java.util.ArrayList;

public class Simulation {
    private final static int MIN_SPAWN_RATE = 0;
    private final static int SPAWN_RATE_PERCENT = 15;
    private final static int PERCENT_100 = 100;
    private final static int DELAY = 300;

    private final WorldMap worldMap;
    private final Renderer renderer;
    private final ArrayList<Action> initActions;
    private final ArrayList<Action> turnActions;
    private final int spawnRate;

    private int turnCount;
    private boolean paused = false;


    public Simulation(int width, int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer();
        this.spawnRate = SPAWN_RATE_PERCENT * width * height / PERCENT_100;
        this.initActions = new ArrayList<>(){{
            add(new EntityFactory(Rock::new, spawnRate));
            add(new EntityFactory(Tree::new, spawnRate));
            add(new EntityFactory(Grass::new, spawnRate));
            add(new EntityFactory(Herbivore::new, spawnRate));
            add(new EntityFactory(Predator::new, spawnRate));
        }};
        this.turnActions = new ArrayList<>(){{
            add(new CreaturesMove());
            add(new HealthPointsChecker());
            add(new EntityFactory(Grass::new, MIN_SPAWN_RATE, spawnRate));
            add(new EntityFactory(Herbivore::new, MIN_SPAWN_RATE, spawnRate));
            add(new EntityFactory(Predator::new, MIN_SPAWN_RATE, spawnRate));
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