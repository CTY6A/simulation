package com.stubedavd.elements;

import com.stubedavd.pathfinding.Astar;
import com.stubedavd.elements.types.HerbivoreType;
import com.stubedavd.Position;
import com.stubedavd.WorldMap;

import java.util.ArrayList;

public class Herbivore extends Creature {
    private final HerbivoreType type;

    public Herbivore() {
        this.type = HerbivoreType.getRandom();
        this.healthPoints = type.getHealthPoints();
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        this.hunger++;
        Position newPosition = null;
        Astar bfs = new Astar(worldMap, worldMap.getPositionByEntity(this), newPosition);
        Position targetPosition = worldMap.findClosestTargetByClass(worldMap.getPositionByEntity(this), Grass.class);
        if (targetPosition != null) {
            bfs.findPath(worldMap.getPositionByEntity(this), targetPosition);

            ArrayList<Position> path = bfs.getPath();
            if (path.size() > 2) {
                path.remove(path.size() - 1);
                if (path.size() - 1 > type.getSpeed()) {
                    path.subList(type.getSpeed() + 1, path.size()).clear();
                }
                do {
                    worldMap.moveEntity(this, bfs.getPath().get(1));
                    path.remove(0);
                } while (path.size() > 1);
            } else if (path.size() == 2) {
                worldMap.removeEntity(path.get(1));
                this.heal(5);
                this.hunger = 0;
            }
        }
        if (this.hunger > 3) {
            this.takeDamage(type.getHealthPoints() / 10 * this.hunger);
        }
    }


    public void heal(int amount) {
        healthPoints += amount;
        if (healthPoints > type.getHealthPoints()) {
            healthPoints = type.getHealthPoints();
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
