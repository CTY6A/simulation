package com.stubedavd;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents the game world containing all entities
 */
public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Position, Entity> entities;
    private final Random random;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Places an entity at the specified position
     */
    public void placeEntity(Position position, Entity entity) {
        if (isValidPosition(position)) {
            entities.put(position, entity);
        }
    }

    /**
     * Gets entity at specified position, returns null if empty
     */
    public Entity getEntityAt(Position position) {
        return entities.get(position);
    }

    /**
     * Checks if position is within map boundaries
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    /**
     * Generates random grass across the map
     * @param grassCount number of grass entities to generate
     */
    public void generateRandomGrass(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Grass());
            }
        }
    }

    /**
     * Generates random trees across the map
     * @param treesCount number of trees entities to generate
     */
    public void generateRandomTrees(int treesCount) {
        for (int i = 0; i < treesCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Tree());
            }
        }
    }

    /**
     * Generates rocks across the map
     * @param rocksCount number of rocks entities to generate
     */
    public void generateRandomRocks(int rocksCount) {
        for (int i = 0; i < rocksCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Rock());
            }
        }
    }

    /**
     * Generates random herbivores across the map
     * @param herbivoresCount number of herbivores entities to generate
     */
    public void generateRandomHerbivores(int herbivoresCount) {
        for (int i = 0; i < herbivoresCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Herbivore());
            }
        }
    }

    /**
     * Generates random predators across the map
     * @param predatorsCount number of predators entities to generate
     */
    public void generateRandomPredators(int predatorsCount) {
        for (int i = 0; i < predatorsCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Predator());
            }
        }
    }

    /**
     * Finds a random empty position on the map
     */
    private Position getRandomEmptyPosition() {
        // Try to find empty position (limit attempts to avoid infinite loop)
        for (int attempts = 0; attempts < width * height * 2; attempts++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Position pos = new Position(x, y);

            if (!entities.containsKey(pos)) {
                return pos;
            }
        }
        return null; // No empty position found
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Map<Position, Entity> getEntities() {
        return entities;
    }
}