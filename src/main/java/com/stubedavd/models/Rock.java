package com.stubedavd.models;

import com.stubedavd.mapobjects.RockType;

public class Rock extends Entity {
    private final RockType type;

    public Rock() {
        this.type = RockType.getRandom();
    }

    @Override
    public String toString() {
        return type.toString();
    }
}