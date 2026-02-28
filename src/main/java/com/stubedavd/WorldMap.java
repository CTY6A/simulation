package com.stubedavd;

import com.stubedavd.elements.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Position, Entity> entities;
    private final Random random;

    public WorldMap(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
        this.random = new Random();
    }

    public void placeEntity(final Position position, final Entity entity) {
        if (isValidPosition(position)) {
            entities.put(position, entity);
        }
    }

    public void removeEntity(final Position position) {
        entities.remove(position);
    }

    public void moveEntity(final Entity entity, final Position toPosition) {
        if (entity != null && toPosition != null) {
            removeEntity(getPositionByEntity(entity));
            placeEntity(toPosition, entity);
        }
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

    public boolean isEmptyPosition(Position position) {
        return entities.get(position) == null;
    }

    public boolean isEntityExists(Entity entity) {
        return entities.containsValue(entity);
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

    public Position findClosestTargetByClass(Position position, Class<? extends Entity> targetType) {
        Position result = null;
        Map<Position, Entity> targets = getEntitiesByClass(targetType);

        for (Map.Entry<Position, Entity> possibleTarget : targets.entrySet()) {
            //if (possibleTarget.getValue().getClass().equals(targetType)) {
            Position possibleTargetPosition = possibleTarget.getKey();
            if (result == null || possibleTargetPosition.distanceTo(position) < result.distanceTo(position)) {
                result = possibleTargetPosition;
            }
            //}
        }
        return result;
    }

    public int getEntityRate(Class<? extends Entity> entityClass) {
        int count = 0;
        for (Entity entity : entities.values()) {
            if (entity.getClass().equals(entityClass)) {
                count++;
            }
        }
        return count * 100 / width * height;
    }

    public Map<Position, Entity> getEntitiesByClass(Class<? extends Entity> entityClass) {
        Map<Position, Entity> resultEntities = new HashMap<>();
        for (Map.Entry<Position, Entity> entry : entities.entrySet()) {
            if (entityClass.isAssignableFrom(entry.getValue().getClass())) {
                resultEntities.put(entry.getKey(), entry.getValue());
            }
        }
        return resultEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorldMap worldMap = (WorldMap) o;
        return width == worldMap.width && height == worldMap.height && Objects.deepEquals(entities, worldMap.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, entities);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}