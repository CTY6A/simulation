package com.stubedavd.elements;

import com.stubedavd.elements.types.TreeType;

public class Tree extends Entity {
    private final TreeType type;

    public Tree() {
        this.type = TreeType.getRandom();
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
