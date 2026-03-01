package com.stubedavd.elements;

import com.stubedavd.elements.types.GrassType;

public class Grass extends Entity {
    private final GrassType type;

    public Grass() {
        this.type = GrassType.getRandom();
    }

    @Override
    public String toString() {
        return type.toString();
    }
}