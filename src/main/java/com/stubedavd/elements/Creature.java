package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;

public abstract class Creature extends Entity {
    private final int MAX_HEALTH_POINTS = 100;
    private int speed;
    private int healthPoints = MAX_HEALTH_POINTS;

    public Creature(Position position) {
        super(position);
    }

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

    public void heal(int amount) {
        healthPoints += amount;
        if (healthPoints > MAX_HEALTH_POINTS) {
            healthPoints = MAX_HEALTH_POINTS;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
