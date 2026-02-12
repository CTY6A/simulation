package com.stubedavd;

public class Grass extends Entity {
    private final GrassType type;

    public Grass() {
        this.type = GrassType.getRandom();
    }

    public Grass(GrassType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public GrassType getType() {
        return type;
    }
}