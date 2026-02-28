package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.types.HealthPoints;

public abstract class Creature extends Entity implements HealthPoints {
    protected int healthPoints;
    protected int hunger = 0;

    public void makeMove(WorldMap worldMap) {
        Position newPosition = worldMap.getRandomEmptyPosition();
        worldMap.moveEntity(this, newPosition);
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getHunger() {
        return hunger;
    }
}
