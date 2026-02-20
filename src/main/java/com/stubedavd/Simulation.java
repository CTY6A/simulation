package com.stubedavd;

import com.stubedavd.actions.Action;

public class Simulation {
    private final WorldMap worldMap;
    private int turnCount;
    private final Renderer renderer;
    private final Action actions;

    public Simulation(final int width, final int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer(worldMap);
        this.turnCount = 0;
        this.actions = new Action();
    }

    public void startSimulation() throws InterruptedException {
        actions.initActions(worldMap);

        WorldMap beforeMap;
        do {
            beforeMap = worldMap.clone();
            nextTurn();
        } while (isRunning(beforeMap));
    }

    private boolean isRunning(WorldMap beforeMap) {
        if (worldMap.equals(beforeMap)) {
            return false;
        }
        return true;
    }

    private void nextTurn() throws InterruptedException {
        turnCount++;
        renderer.renderTurn(turnCount);
        Thread.sleep(300);
        actions.turnActions(worldMap);
    }
}