package com.stubedavd.utils;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class WorldMapUtils {
    private final int height;
    private final int width;
    private final int proportion;
    private final WorldMap worldMap;
    private final Random random;

    public WorldMapUtils(WorldMap worldMap) {
        height = worldMap.getHeight();
        width = worldMap.getWidth();
        this.proportion = width * height / 100;
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
        Position result = null;
        if (targetType == null) {
            return result;
        }

        if (worldMap.isValidPosition(position)) {
            Map<Position, Entity> targets = worldMap.getEntitiesByClass(targetType);
            if (targets.isEmpty()) {
                return result;
            }

            for (Map.Entry<Position, Entity> possibleTarget : targets.entrySet()) {
                Position possibleTargetPosition = possibleTarget.getKey();
                result = closestPosition(position, result, possibleTargetPosition);
            }
        }
        return result;
    }

    private Position closestPosition(Position positionFrom, Position oldPositionTo, Position newPositionTo) {
        if (worldMap.isValidPosition(positionFrom)) {
            if (worldMap.isValidPosition(oldPositionTo) && worldMap.isValidPosition(newPositionTo)) {
                if (positionFrom.distanceTo(newPositionTo) < positionFrom.distanceTo(oldPositionTo)) {
                    return newPositionTo;
                }
                return oldPositionTo;
            }
            return newPositionTo;
        }
        return null;
    }

    public int countEntities(Class<? extends Entity> entityClass) {
        int count = 0;
        if (entityClass == null) {
            return count;
        }
        Map<Position, Entity> entities = worldMap.getEntitiesByClass(entityClass);
        return entities.size();
    }

    public int getProportion() {
        return proportion;
    }
}
