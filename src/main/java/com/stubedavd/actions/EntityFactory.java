package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;

import java.util.function.Supplier;

public class EntityFactory implements Action {
    private final Supplier<Entity> entitySupplier;
    private final int maxRate;
    private final int minSpawnRate;

    public EntityFactory(Supplier<Entity> entitySupplier, int maxRate) {
        this.entitySupplier = entitySupplier;
        this.minSpawnRate = Integer.MAX_VALUE;
        this.maxRate = maxRate;
    }

    public EntityFactory(Supplier<Entity> entitySupplier, int minSpawnRate, int maxRate) {
        this.entitySupplier = entitySupplier;
        this.minSpawnRate = minSpawnRate;
        this.maxRate = maxRate;
    }

    @Override
    public void perform(WorldMap worldMap) {
        int currentRate = worldMap.getEntityRate(entitySupplier.get().getClass());
        if (currentRate > minSpawnRate) {
            return;
        }
        for (int i = currentRate; i < maxRate; i++) {
            Position randomPos = worldMap.getRandomEmptyPosition();
            if (randomPos != null) {
                worldMap.placeEntity(randomPos, entitySupplier.get());
            }
        }
    }
}
