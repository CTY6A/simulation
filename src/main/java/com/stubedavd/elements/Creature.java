package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.types.CreatureType;
import com.stubedavd.pathfinding.Astar;

import java.util.ArrayList;

public abstract class Creature extends Entity {
    protected int healthPoints;
    protected int hunger = 0;
    protected CreatureType type;

    public abstract void heal(int amount);
    public void makeMove(WorldMap worldMap) {
        hunger++;
        Position newPosition = null;
        Astar bfs = new Astar(worldMap);
        Position targetPosition = worldMap.findClosestTargetByClass(worldMap.getPositionByEntity(this), type.getTargetClass());
        if (targetPosition != null) {

            ArrayList<Position> path = bfs.findPath(worldMap.getPositionByEntity(this), targetPosition);

            if (path.size() > 2) {
                path.remove(path.size() - 1);
                if (path.size() - 1 > type.getSpeed()) {
                    path.subList(type.getSpeed() + 1, path.size()).clear();
                }
                do {
                    worldMap.moveEntity(this, path.get(1));
                    path.remove(0);
                } while (path.size() > 1);
            } else if (path.size() == 2) {
                worldMap.removeEntity(path.get(1));
                this.heal(5);
                this.hunger = 0;
            }
        }
        if (this.hunger > 10) {
            this.takeDamage(type.getHealthPoints() / 10);
        }
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
