package com.stubedavd.pathfinding;

import com.stubedavd.Position;

import java.util.Objects;

public class Node implements Comparable<Node> {
    private final Position position;
    private int g = 0;
    private int h = 0;
    private int f = 0;
    private Node parent = null;

    public Position getPosition() {
        return position;
    }

    public Node(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(f, o.f);
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

    public void setF(int f) {
        this.f = f;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}
