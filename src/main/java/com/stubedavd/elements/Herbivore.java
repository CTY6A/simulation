package com.stubedavd.elements;

import com.stubedavd.elements.types.HerbivoreType;

public class Herbivore extends Creature {

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
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
