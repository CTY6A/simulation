package com.stubedavd.actions;

import com.stubedavd.core.Position;
import com.stubedavd.core.WorldMap;
import com.stubedavd.creatures.Creature;
import com.stubedavd.models.Entity;

import java.util.Map;

public class CreaturesMove extends Action {
    @Override
    public void perform(WorldMap worldMap) {
        Map<Position, Entity> creatures = worldMap.getEntitiesByClass(Creature.class);
        for (Entity entity : creatures.values()) {
            if (worldMap.isEntityExists(entity)) {
                Creature creature = (Creature) entity;
                creature.makeMove(worldMap);
            }
        }
    }
}
