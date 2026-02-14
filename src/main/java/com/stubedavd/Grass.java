package com.stubedavd;

public class Grass extends Entity {
    private final GrassType type;

    public Grass(Position position) {
        super(position);
        this.type = GrassType.getRandom();
    }

    public Grass(Position position, GrassType type) {
        super(position);
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