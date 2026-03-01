package com.stubedavd.elements;

import com.stubedavd.WorldMap;

public abstract class Creature extends Entity {
    protected int healthPoints;
    protected int hunger = 0;

    public abstract void heal(int amount);
    public abstract void makeMove(WorldMap worldMap);

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
