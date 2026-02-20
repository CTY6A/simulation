package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.elements.types.RockType;

public class Rock extends Entity {
    private final RockType type;

    public Rock() {
        this.type = RockType.getRandom();
    }

    public Rock(RockType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public RockType getType() {
        return type;
    }
}