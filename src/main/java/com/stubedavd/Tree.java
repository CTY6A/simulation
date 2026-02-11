package com.stubedavd;

public class Tree extends Entity {
    private final TreeType type;

    public Tree() {
        this.type = TreeType.getRandom();
    }

    public Tree(TreeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public TreeType getType() {
        return type;
    }
}
