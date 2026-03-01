package com.stubedavd.actions;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.creatures.Creature;
import com.stubedavd.elements.Entity;

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
