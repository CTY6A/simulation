package com.stubedavd.elements.creatures;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;
import com.stubedavd.elements.types.creatureTypes.PredatorType;

public class Predator extends Creature {
    private final int damage;

    public Predator() {
        PredatorType type = PredatorType.getRandom();
        this.type = type;
        this.healthPoints = type.getHealthPoints();
        this.damage = type.getDamage();
    }

    @Override
    protected void eatTarget(WorldMap worldMap, Position targetPosition) {
        Entity entity = worldMap.getEntityAt(targetPosition);
        Herbivore herbivore = (Herbivore) entity;
        herbivore.takeDamage(damage);
        this.heal(damage / 2);
        this.hunger = 0;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
