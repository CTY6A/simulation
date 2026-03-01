package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.creatures.Creature;
import com.stubedavd.elements.Entity;

import java.util.Map;

public class HealthPointsChecker extends Action{

    public static final int MIN_HEALTH_POINTS = 0;

    @Override
    public void perform(WorldMap worldMap) {
        Map<Position, Entity> creatures = worldMap.getEntitiesByClass(Creature.class);
        for (Map.Entry<Position, Entity> entry : creatures.entrySet()) {
            Entity entity = entry.getValue();
            Creature creature = (Creature) entity;
            if (creature.getHealthPoints() <= MIN_HEALTH_POINTS) {
                Position position = entry.getKey();
                worldMap.removeEntity(position);
            }
        }
    }
}
