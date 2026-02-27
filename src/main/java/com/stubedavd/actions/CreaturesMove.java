package com.stubedavd.actions;

import com.stubedavd.WorldMap;
import com.stubedavd.elements.Creature;
import com.stubedavd.elements.Entity;

public class CreaturesMove implements Action {
    @Override
    public void perform(WorldMap worldMap) {
        var creatures = worldMap.getEntitiesByClass(Creature.class).values();
        for (Entity creature : creatures) {
            if (worldMap.isEntityExists(creature)) {
                ((Creature)creature).makeMove(worldMap);
                //creature.takeDamage(25);
                    /*if (creature.getHealthPoints() <= 0) {
                        Position position = creature.getPosition();
                        worldMapCopy.removeEntity(position);
                        worldMapCopy.placeEntity(position, new Rock(position, RockType.GRAVE));
                    }*/
            }
        }
    }
}
