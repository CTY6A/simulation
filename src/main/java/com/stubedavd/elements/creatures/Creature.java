package com.stubedavd.elements.creatures;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;
import com.stubedavd.elements.types.creatureTypes.Liveable;
import com.stubedavd.pathfinding.Astar;

import java.util.ArrayList;

public abstract class Creature extends Entity {
    protected int healthPoints;
    protected int hunger = 0;
    protected Liveable type;

    public abstract void heal(int amount);
    public void makeMove(WorldMap worldMap) {
        starvation();

        Position currentPosition = worldMap.getPositionByEntity(this);
        Position targetPosition = worldMap.findClosestTargetByClass(currentPosition, type.getTargetClass());
        if (targetPosition == null) {
            return;
        }
        if (targetPosition.distanceTo(currentPosition) == 1) {
            eatTarget(worldMap, targetPosition);
            return;
        }

        Astar pathFinder = new Astar(worldMap);
        ArrayList<Position> path = pathFinder.findPath(currentPosition, targetPosition);
        if (path == null || path.size() < 3) {
            return;
        }

        followTarget(worldMap, path);
    }

    private void followTarget(WorldMap worldMap, ArrayList<Position> path) {
        path.remove(path.size() - 1);
        if (path.size() - 1 > type.getSpeed()) {
            // remove all positions according to the speed
            path.subList(type.getSpeed() + 1, path.size()).clear();
        }
        int endPoint = path.size() - 1;
        Position endPosition = path.get(endPoint);
        worldMap.moveEntity(this, endPosition);
    }

    private void starvation() {
        hunger++;
        if (this.hunger > 10) {
            // remove 10% of health points
            this.takeDamage(type.getHealthPoints() / 10);
        }
    }

    protected abstract void eatTarget(WorldMap worldMap, Position targetPosition);

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
