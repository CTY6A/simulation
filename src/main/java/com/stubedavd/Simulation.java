package com.stubedavd;

import com.stubedavd.actions.Action;
import com.stubedavd.actions.CreaturesMove;
import com.stubedavd.actions.EntityFactory;
import com.stubedavd.actions.HealthPointsChecker;
import com.stubedavd.elements.*;

import java.util.ArrayList;

public class Simulation {
    private boolean running = true;
    private boolean paused = false;

    public static final int SPAWN_RATE = 15;
    public static final int MIN_SPAWN_RATE = 0;

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
            add(new EntityFactory(Grass::new, SPAWN_RATE));
            add(new EntityFactory(Herbivore::new, SPAWN_RATE));
            add(new EntityFactory(Predator::new, SPAWN_RATE));
        }};
        this.turnActions = new ArrayList<>(){{
            add(new CreaturesMove());
            add(new HealthPointsChecker());
            add(new EntityFactory(Grass::new, MIN_SPAWN_RATE, SPAWN_RATE));
            add(new EntityFactory(Herbivore::new, MIN_SPAWN_RATE, SPAWN_RATE));
            add(new EntityFactory(Predator::new, MIN_SPAWN_RATE, SPAWN_RATE));
        }};
        this.turnCount = 0;
    }

    public void startSimulation() throws InterruptedException {

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

    public void pauseSimulation() {
        paused = !paused;
    }

    public void stopSimulation() {
        running = false;
    }
}