package com.stubedavd.elements.creatures;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.types.creatureTypes.HerbivoreType;

public class Herbivore extends Creature {

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
        this.healthPoints = type.getHealthPoints();
    }

    @Override
    protected void eatTarget(WorldMap worldMap, Position targetPosition) {
        worldMap.removeEntity(targetPosition);
        this.heal(5);
        this.hunger = 0;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
