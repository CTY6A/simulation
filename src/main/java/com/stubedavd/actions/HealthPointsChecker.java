package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Creature;
import com.stubedavd.elements.Entity;

import java.util.Map;

public class HealthPointsChecker implements Action{
    @Override
    public void perform(WorldMap worldMap) {
        Map<Position, Entity> creatures = worldMap.getEntitiesByClass(Creature.class);
        for (Map.Entry<Position, Entity> entry : creatures.entrySet()) {
            Creature creature = (Creature) entry.getValue();
            if (creature.getHealthPoints() <= 0) {
                worldMap.removeEntity(entry.getKey());
            }
        }
    }
}
