package com.stubedavd.elements;

import com.stubedavd.Position;

public abstract class Entity {
    private Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}