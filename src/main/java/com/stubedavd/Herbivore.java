package com.stubedavd;

public class Herbivore extends Creature {
    private final HerbivoreType type;

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
    }

    public Herbivore(HerbivoreType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public HerbivoreType getType() {
        return type;
    }
}
