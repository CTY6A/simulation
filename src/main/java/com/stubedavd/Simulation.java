package com.stubedavd;

public class Simulation {
    private final WorldMap worldMap;
    private int turnCount;
    private final Renderer renderer;
    private final Action actions;

    public Simulation(int width, int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer(worldMap);
        this.turnCount = 0;
        actions = new Action();
    }

    public void startSimulation() {
        actions.initActions(worldMap);

        System.out.println("\n--- Turn " + turnCount + " ---");
        renderer.render();

        //try {
            while (turnCount < 5){
                //Thread.sleep(1000);
                nextTurn();
            }
        /*} catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Simulation interrupted");
        }*/

        //System.out.println("\nSimulation completed!");
    }

    public void nextTurn() {
        turnCount++;
        System.out.println("\n--- Turn " + turnCount + " ---");
        actions.turnActions(worldMap);
        renderer.render();
    }

    public void pauseSimulation() {

    }

    public WorldMap getWorldMap() { return worldMap; }
    public int getTurnCount() { return turnCount; }
}