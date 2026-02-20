package com.stubedavd;

import com.stubedavd.actions.Action;

public class Simulation {
    private final WorldMap worldMap;
    private final Renderer renderer;
    private final Action actions;

    private int turnCount;

    public Simulation(final int width, final int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer();
        this.actions = new Action();
        this.turnCount = 0;
    }

    public void startSimulation() throws InterruptedException {
        actions.initActions(worldMap);

        WorldMap beforeMap;
        do {
            beforeMap = worldMap.clone();
            nextTurn();
        } while (isRunning(beforeMap));
    }

    private void nextTurn() throws InterruptedException {
        turnCount++;
        renderer.render(worldMap, turnCount);
        Thread.sleep(300);
        actions.turnActions(worldMap);
    }

    private boolean isRunning(final WorldMap beforeMap) {
        if (worldMap.equals(beforeMap)) {
            return false;
        }
        return true;
    }
}