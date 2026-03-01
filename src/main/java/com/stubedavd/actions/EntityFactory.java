package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;

import java.util.function.Supplier;

public class EntityFactory extends Action {
    private final Supplier<Entity> entitySupplier;
    private final int spawnPercent;
    private final int extinctionLimit;

    public EntityFactory(Supplier<Entity> entitySupplier, int spawnPercent) {
        this(entitySupplier, spawnPercent, 0);
    }

    public EntityFactory(Supplier<Entity> entitySupplier, int spawnPercent, int extinctionLimit) {
        this.entitySupplier = entitySupplier;
        this.spawnPercent = spawnPercent;
        this.extinctionLimit = extinctionLimit;
    }

    @Override
    public void perform(WorldMap worldMap) {
        int maxEntities = spawnPercent * worldMap.getProportion();
        int numberOfEntities = 0;
        if (extinctionLimit > 0) {
            numberOfEntities = worldMap.countEntities(entitySupplier.get().getClass());
            // calculate entities limit
            int entitiesLimit = maxEntities - maxEntities * extinctionLimit / 100;
            if (numberOfEntities > entitiesLimit) {
                return;
            }
        }
        for (int i = numberOfEntities; i < maxEntities; i++) {
            Position randomPos = worldMap.getRandomEmptyPosition();
            if (randomPos == null) {
                return;
            }
            worldMap.placeEntity(randomPos, entitySupplier.get());
        }
    }
}
