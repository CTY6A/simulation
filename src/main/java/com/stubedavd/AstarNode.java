package com.stubedavd;

import java.util.Objects;

public class AstarNode implements Comparable<AstarNode> {
    private final Position position;
    private int g = 0;
    private int h = 0;
    private int f = 0;
    private AstarNode parent = null;

    public Position getPosition() {
        return position;
    }

    public AstarNode(Position position) {
        this.position = position;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AstarNode astarNode = (AstarNode) o;
        return Objects.equals(position, astarNode.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public int compareTo(AstarNode o) {
        if (f < o.f) return -1;
        if (f > o.f) return 1;
        return 0;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setParent(AstarNode parent) {
        this.parent = parent;
    }

    public AstarNode getParent() {
        return parent;
    }
}
