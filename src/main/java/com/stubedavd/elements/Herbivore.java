package com.stubedavd.elements;

import com.stubedavd.pathfinding.Astar;
import com.stubedavd.elements.types.HerbivoreType;
import com.stubedavd.Position;
import com.stubedavd.WorldMap;

public class Herbivore extends Creature {
    private final HerbivoreType type;

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
    }

    public Herbivore(HerbivoreType type) {
        this.type = type;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        Position newPosition = null;
        Astar bfs = new Astar(worldMap, worldMap.getPositionByEntity(this), newPosition);
        bfs.findPath();
        Position closestGrass = bfs.findClosestTargetByClass(Grass.class);
        System.out.println(closestGrass);
        System.out.println(bfs.getPath());
//        Position secondPosition = null;
//        if (!bfs.getPath().isEmpty()) {
//            secondPosition = bfs.getPath().get(0);
//            bfs.getPath().remove(0);
//        } else if (bfs.getTargetPosition() != null) {
//            secondPosition = bfs.getTargetPosition();
//            bfs.setTargetPosition(null);
//            this.heal(25);
//        }
//
//        if (!(worldMap.isEmptyPosition(secondPosition) || worldMap.getEntityAt(secondPosition) instanceof Grass)) {
//            bfs.findPath();
//        }

        if (bfs.getPath().size() >= 2) {
            worldMap.moveEntity(this, bfs.getPath().get(1));
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public HerbivoreType getType() {
        return type;
    }
}
