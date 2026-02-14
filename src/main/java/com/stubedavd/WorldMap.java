package com.stubedavd;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public void placeEntity(Position position, Entity entity) {
        if (isValidPosition(position)) {
            entities.put(position, entity);
        }
    }

    public void removeEntity(Position position) {
        entities.remove(position);
    }

    public void moveEntity(Entity entity, Position toPosition) {
        removeEntity(entity.getPosition());
        entity.setPosition(toPosition);
        placeEntity(toPosition, entity);
    }

    public Entity getEntityAt(Position position) {
        return entities.get(position);
    }

    public Position getPositionByEntity(Entity entity) {
        for (Map.Entry<Position, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isValidPosition(Position position) {
        return position != null && position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    public void generateRandomRocks(int rocksCount) {
        for (int i = 0; i < rocksCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Rock(randomPos));
            }
        }
    }

    public void generateRandomGrass(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Grass(randomPos));
            }
        }
    }

    public void generateRandomTrees(int treesCount) {
        for (int i = 0; i < treesCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Tree(randomPos));
            }
        }
    }

    public void generateRandomHerbivores(int herbivoresCount) {
        for (int i = 0; i < herbivoresCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Herbivore(randomPos));
            }
        }
    }

    public void generateRandomPredators(int predatorsCount) {
        for (int i = 0; i < predatorsCount; i++) {
            Position randomPos = getRandomEmptyPosition();
            if (randomPos != null) {
                placeEntity(randomPos, new Predator(randomPos));
            }
        }
    }

    public Position getRandomEmptyPosition() {
        for (int attempts = 0; attempts < width * height * 2; attempts++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Position pos = new Position(x, y);

            if (!entities.containsKey(pos)) {
                return pos;
            }
        }
        return null;
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