package com.stubedavd.elements.creatures;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.types.creatureTypes.HerbivoreType;

public class Herbivore extends Creature {

    private static final int HP_HEAL_PERCENT = 10;

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
        this.healthPoints = type.getHealthPoints();
    }

    @Override
    protected void eatTargetByPosition(WorldMap worldMap, Position targetPosition) {
        worldMap.removeEntity(targetPosition);
        int percentageHeal = type.getHealthPoints() * HP_HEAL_PERCENT / PERCENT_100;
        this.heal(percentageHeal);
        this.hunger = MIN_HUNGER_COUNT;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
