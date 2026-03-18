package com.stubedavd;

import com.stubedavd.elements.Entity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class WorldMapUtils {
    private final int height;
    private final int width;
    private final WorldMap worldMap;
    private final Random random;

    public WorldMapUtils(WorldMap worldMap) {
        height = worldMap.getHeight();
        width = worldMap.getWidth();
        this.worldMap = worldMap;
        this.random = new Random();
    }

    public Position getRandomEmptyPosition() {
        ArrayList<Position> emptyPositions = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position position = new Position(x, y);
                if (worldMap.isEmptyPosition(position)) {
                    emptyPositions.add(position);
                }
            }
        }
        if (emptyPositions.isEmpty()) {
            return null;
        }
        int randomIdx = random.nextInt(emptyPositions.size());
        return emptyPositions.get(randomIdx);
    }

    public Position findClosestTargetByClass(Position position, Class<? extends Entity> targetType) {
        if (position == null || targetType == null) {
            return null;
        }

        Map<Position, Entity> targets = worldMap.getEntitiesByClass(targetType);
        if (targets.isEmpty()) {
            return null;
        }
        Position result = null;

        for (Map.Entry<Position, Entity> possibleTarget : targets.entrySet()) {
            Position possibleTargetPosition = possibleTarget.getKey();
            result = closestPosition(position, result, possibleTargetPosition);
        }
        return result;
    }

    private Position closestPosition(Position positionFrom, Position oldPositionTo, Position newPositionTo) {
        if (oldPositionTo == null || newPositionTo == null) {
            return newPositionTo;
        }
        if (positionFrom.distanceTo(newPositionTo) < positionFrom.distanceTo(oldPositionTo)) {
            return newPositionTo;
        }

        return oldPositionTo;
    }

    public int countEntities(Class<? extends Entity> entityClass) {
        int count = 0;
        if (entityClass == null) {
            return count;
        }
        Map<Position, Entity> entities = worldMap.getEntitiesByClass(entityClass);
        return entities.size();
    }
}
