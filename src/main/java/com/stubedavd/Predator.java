package com.stubedavd;

public class Predator extends Creature {
    private final PredatorType type;

    public Predator() {
        this.type = PredatorType.getRandom();
    }

    public Predator(PredatorType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public PredatorType getType() {
        return type;
    }
}
