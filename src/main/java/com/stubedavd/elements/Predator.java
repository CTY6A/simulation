package com.stubedavd.elements;

import com.stubedavd.elements.types.PredatorType;

public class Predator extends Creature {

    public Predator() {
        this.type = PredatorType.getRandom();
        this.healthPoints = type.getHealthPoints();
    }

    public void heal(int amount) {
        healthPoints += amount;
        if (healthPoints > type.getHealthPoints()) {
            healthPoints = type.getHealthPoints();
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
