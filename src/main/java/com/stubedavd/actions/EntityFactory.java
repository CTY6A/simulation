package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;

import java.util.function.Supplier;

public class EntityFactory extends Action {
    private final Supplier<Entity> entitySupplier;
    private final int minSpawnRate;
    private final int maxSpawnRate;

    public EntityFactory(Supplier<Entity> entitySupplier, int maxSpawnRate) {
        this.entitySupplier = entitySupplier;
        this.minSpawnRate = Integer.MAX_VALUE;
        this.maxSpawnRate = maxSpawnRate;
    }

    public EntityFactory(Supplier<Entity> entitySupplier, int minSpawnRate, int maxSpawnRate) {
        this.entitySupplier = entitySupplier;
        this.minSpawnRate = minSpawnRate;
        this.maxSpawnRate = maxSpawnRate;
    }

    @Override
    public void perform(WorldMap worldMap) {
        Entity entity = entitySupplier.get();
        Class<? extends Entity> entityClass = entity.getClass();
        int currentRate = worldMap.getEntityRate(entityClass);
        if (currentRate > minSpawnRate) {
            return;
        }
        for (int i = currentRate; i < maxSpawnRate; i++) {
            Position randomPos = worldMap.getRandomEmptyPosition();
            if (randomPos == null) {
                return;
            }
            worldMap.placeEntity(randomPos, entitySupplier.get());
        }
    }
}
