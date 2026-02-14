package com.stubedavd;

public class Tree extends Entity {
    private final TreeType type;

    public Tree(Position position) {
        super(position);
        this.type = TreeType.getRandom();
    }

    public Tree(Position position, TreeType type) {
        super(position);
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
