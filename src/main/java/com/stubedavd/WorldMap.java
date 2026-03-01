package com.stubedavd;

import com.stubedavd.elements.*;

import java.util.*;

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
        if (position == null) {
            return;
        }
        entities.remove(position);
    }

    public void moveEntity(Entity entity, Position toPosition) {
        if (entity == null) {
            return;
        }
        removeEntity(getPositionByEntity(entity));
        placeEntity(toPosition, entity);
    }

    public Entity getEntityAt(Position position) {
        if (position == null) {
            return null;
        }
        return entities.get(position);
    }

    public Position getPositionByEntity(Entity entity) {
        for (Map.Entry<Position, Entity> entry : entities.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isValidPosition(Position position) {
        return position != null && position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    public boolean isEmptyPosition(Position position) {
        if (position == null) {
            return false;
        }
        return entities.get(position) == null;
    }

    public boolean isEntityExists(Entity entity) {
        if (entity == null) {
            return false;
        }
        return entities.containsValue(entity);
    }

    public Position getRandomEmptyPosition() {
        ArrayList<Position> emptyPositions = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position position = new Position(x, y);
                if (isEmptyPosition(position)) {
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

        Map<Position, Entity> targets = getEntitiesByClass(targetType);
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

    public int getEntityRate(Class<? extends Entity> entityClass) {
        if (entityClass == null) {
            return 0;
        }
        int count = 0;
        for (Entity entity : entities.values()) {
            if (checkEntityClass(entity, entityClass)) {
                count++;
            }
        }
        return count * 100 / width * height;
    }

    private boolean checkEntityClass(Entity entity, Class<? extends Entity> entityClass) {
        return entity.getClass().equals(entityClass);
    }

    public Map<Position, Entity> getEntitiesByClass(Class<? extends Entity> entityClass) {
        Map<Position, Entity> resultEntities = new HashMap<>();
        if (entityClass == null) {
            return resultEntities;
        }
        for (Map.Entry<Position, Entity> entry : entities.entrySet()) {
            Entity currentEntity = entry.getValue();
            Class<? extends Entity> currentEntityClass = currentEntity.getClass();
            if (entityClass.isAssignableFrom(currentEntityClass)) {
                Position currentPosition = entry.getKey();
                resultEntities.put(currentPosition, currentEntity);
            }
        }
        return resultEntities;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}