package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.elements.types.RockType;

public class Rock extends Entity {
    private final RockType type;

    public Rock(Position position) {
        super(position);
        this.type = RockType.getRandom();
    }

    public Rock(Position position, RockType type) {
        super(position);
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