package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.elements.types.PredatorType;
import com.stubedavd.WorldMap;
import com.stubedavd.pathfinding.Astar;

import java.util.ArrayList;

public class Predator extends Creature {
    private final PredatorType type;

    public Predator() {
        this.type = PredatorType.getRandom();
        this.healthPoints = type.getHealthPoints();
    }

    public Predator(PredatorType type) {
        this.type = type;
        this.healthPoints = type.getHealthPoints();
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        this.hunger++;
        Position newPosition = null;
        Astar bfs = new Astar(worldMap);
        Position targetPosition = worldMap.findClosestTargetByClass(worldMap.getPositionByEntity(this), Herbivore.class);
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
                Herbivore herbivore = (Herbivore) worldMap.getEntityAt(path.get(1));
                herbivore.takeDamage(type.getDamage());
                this.heal(5);
                this.hunger = 0;
            }
        }

        if (this.hunger > 10) {
            this.takeDamage(type.getHealthPoints() / 10);
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

    public PredatorType getType() {
        return type;
    }
}
