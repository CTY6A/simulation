package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;

import java.util.function.Supplier;

public class EntityFactory extends Action {
    public static final int PERCENT_100 = 100;
    public static final int MIN_EXTINCTION_LIMIT = 0;

    private final Supplier<Entity> entitySupplier;
    private final int spawnPercent;
    private final int extinctionLimit;

    public EntityFactory(Supplier<Entity> entitySupplier, int spawnPercent) {
        this(entitySupplier, spawnPercent, MIN_EXTINCTION_LIMIT);
    }

    public EntityFactory(Supplier<Entity> entitySupplier, int spawnPercent, int extinctionLimit) {
        this.entitySupplier = entitySupplier;
        this.spawnPercent = spawnPercent;
        this.extinctionLimit = extinctionLimit;
    }

    @Override
    public void perform(WorldMap worldMap) {
        int maxEntities = spawnPercent * worldMap.getProportion();
        int numberOfEntities = worldMap.countEntities(entitySupplier.get().getClass());
        if (extinctionLimit > MIN_EXTINCTION_LIMIT) {
            // subtract extinction limit percentage from max entities
            int entitiesLimit = maxEntities - maxEntities * extinctionLimit / PERCENT_100;
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
