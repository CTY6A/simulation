package com.stubedavd.elements;

import com.stubedavd.elements.types.RockType;

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