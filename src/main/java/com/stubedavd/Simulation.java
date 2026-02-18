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
        actions = new Action();
    }

    public void startSimulation() throws InterruptedException {
        actions.initActions(worldMap);

        System.out.println("\n--- Turn " + turnCount + " ---");
        renderer.render();

            while (true){
                Thread.sleep(300);
                nextTurn();
            }
    }

    public void nextTurn() {
        turnCount++;
        System.out.println("\n--- Turn " + turnCount + " ---");
        actions.turnActions(worldMap);
        renderer.render();
    }

}