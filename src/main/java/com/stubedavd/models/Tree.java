package com.stubedavd.models;

import com.stubedavd.mapobjects.TreeType;

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
