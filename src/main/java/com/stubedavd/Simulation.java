package com.stubedavd;

/**
 * Main simulation controller
 */
public class Simulation {
    private final WorldMap worldMap;
    private final Renderer renderer;
    private int turnCount;

    public Simulation(int width, int height) {
        this.worldMap = new WorldMap(width, height);
        this.renderer = new Renderer(worldMap);
        this.turnCount = 0;
    }

    /**
     * Initializes the simulation world with initial entities
     */
    public void initialize() {
        // Generate some random grass (about 10% of the map)
        int grassCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomGrass(grassCount);

        // Generate some random trees (about 10% of the map)
        int treesCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomTrees(treesCount);

        // Generate some random rocks (about 10% of the map)
        int rocksCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomRocks(rocksCount);

        // Generate some random herbivores (about 10% of the map)
        int herbivoresCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomHerbivores(herbivoresCount);

        // Generate some random predators (about 10% of the map)
        int predatorsCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomPredators(predatorsCount);



        System.out.println("Simulation initialized with " + grassCount + " grass entities");
    }

    /**
     * Advances simulation by one turn
     */
    public void nextTurn() {
        turnCount++;
        System.out.println("\n--- Turn " + turnCount + " ---");

        // For now, just render the current state
        renderer.render();

        // TODO: Add creature movement and other logic in future
    }

    /**
     * Starts the simulation
     */
    public void startSimulation() {
        System.out.println("Starting simulation...");
        initialize();
        nextTurn(); // Show initial state
    }

    public WorldMap getWorldMap() { return worldMap; }
    public int getTurnCount() { return turnCount; }
}