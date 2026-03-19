package com.stubedavd.core;

public class Position {
    public static final int ONE_STEP = 1;

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int distanceTo(Position position) {
        if (position == null) {
            return Integer.MAX_VALUE;
        }

        int xDistance = Math.abs(x - position.getX());
        int yDistance = Math.abs(y - position.getY());
        if (isDiagonal(xDistance, yDistance)) {
            return ONE_STEP;
        }
        return xDistance + yDistance;
    }

    private boolean isDiagonal(int xDistance, int yDistance) {
        return xDistance == ONE_STEP && yDistance == ONE_STEP;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}