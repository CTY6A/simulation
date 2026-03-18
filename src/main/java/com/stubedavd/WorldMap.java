package com.stubedavd;

import com.stubedavd.elements.*;

import java.util.*;

public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Position, Entity> entities;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public void placeEntity(Position position, Entity entity) {
        if (isValidPosition(position)) {
            entities.put(position, entity);
        }
    }

    public void removeEntity(Position position) {
        if (isValidPosition(position)) {
            entities.remove(position);
        }
    }

    public Entity getEntityAt(Position position) {
        if (isValidPosition(position)) {
            return entities.get(position);
        }
        return null;
    }

    public boolean isValidPosition(Position position) {
        return position != null && position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    public void moveEntity(Entity entity, Position toPosition) {
        if (entity == null) {
            return;
        }
        removeEntity(getPositionByEntity(entity));
        placeEntity(toPosition, entity);
    }

    public Position getPositionByEntity(Entity entity) {
        for (Map.Entry<Position, Entity> entry : entities.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isEmptyPosition(Position position) {
        if (isValidPosition(position)) {
            return entities.get(position) == null;
        }
        return false;
    }

    public boolean isEntityExists(Entity entity) {
        if (entity == null) {
            return false;
        }
        return entities.containsValue(entity);
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