package com.stubedavd;

public class Herbivore extends Creature {
    private final HerbivoreType type;
    private Astar bfs = null;

    public Herbivore(Position position) {
        super(position);
        this.type = HerbivoreType.getRandom();
    }

    public Herbivore(Position position, HerbivoreType type) {
        super(position);
        this.type = type;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        Position newPosition = null;

        if (bfs == null) {
            this.bfs = new Astar(worldMap, this.getPosition(), newPosition);
            bfs.findPath();
        }
        Position closestGrass = bfs.findClosestGrass();
        System.out.println(closestGrass);
        System.out.println(bfs.getPath());
        Position secondPosition = null;
        if (!bfs.getPath().isEmpty()) {
            secondPosition = bfs.getPath().get(0);
            bfs.getPath().remove(0);
        } else if (bfs.getTargetPosition() != null) {
            secondPosition = bfs.getTargetPosition();
            bfs.setTargetPosition(null);
            this.heal(25);
        }

        if (!(worldMap.isEmptyPosition(secondPosition) || worldMap.getEntityAt(secondPosition) instanceof Grass)) {
            bfs.findPath();
        }
        worldMap.moveEntity(this, secondPosition);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public HerbivoreType getType() {
        return type;
    }
}
