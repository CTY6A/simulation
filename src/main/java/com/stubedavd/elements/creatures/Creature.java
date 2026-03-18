package com.stubedavd.elements.creatures;

import com.stubedavd.WorldMapUtils;
import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Entity;
import com.stubedavd.elements.types.creatureTypes.Liveable;
import com.stubedavd.pathfinding.AStar;

import java.util.ArrayList;

public abstract class Creature extends Entity {
    public static final int MIN_HUNGER_COUNT = 0;
    private static final int MIN_HEALTH_POINTS = 0;
    public static final int HUNGER_TURNS_LIMIT = 10;
    public static final int HP_DAMAGE_PERCENT = 10;
    public static final int PERCENT_100 = 100;
    public static final int ONE_STEP = 1;

    protected int healthPoints;
    protected int hunger = MIN_HUNGER_COUNT;
    protected Liveable type;

    public void makeMove(WorldMap worldMap) {
        starvation();

        Position currentPosition = worldMap.getPositionByEntity(this);
        WorldMapUtils worldMapUtils = new WorldMapUtils(worldMap);
        Position targetPosition = worldMapUtils.findClosestTargetByClass(currentPosition, type.getTargetClass());
        if (worldMap.isValidPosition(targetPosition)) {
            if (targetPosition.distanceTo(currentPosition) == ONE_STEP) {
                eatTargetByPosition(worldMap, targetPosition);
                return;
            }

            AStar pathFinder = new AStar(worldMap);
            ArrayList<Position> path = pathFinder.findPath(currentPosition, targetPosition);
            if (path == null || path.isEmpty()) {
                return;
            }

            followTarget(worldMap, path);
        }
    }

    private void followTarget(WorldMap worldMap, ArrayList<Position> path) {
        if (path.size() > type.getSpeed()) {
            // remove all positions according to the speed
            path.subList(type.getSpeed(), path.size()).clear();
        }
        int endPoint = getEndPoint(path);
        Position endPosition = path.get(endPoint);
        worldMap.moveEntity(this, endPosition);
    }

    private int getEndPoint(ArrayList<Position> path) {
        return path.size() - 1;
    }

    private void starvation() {
        hunger++;
        if (isHungerHarmful()) {
            // remove 10% of health points
            int percentageDamage = type.getHealthPoints() * HP_DAMAGE_PERCENT / PERCENT_100;
            this.takeDamage(percentageDamage);
        }
    }

    private boolean isHungerHarmful() {
        return this.hunger > HUNGER_TURNS_LIMIT;
    }

    protected abstract void eatTargetByPosition(WorldMap worldMap, Position targetPosition);

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < MIN_HEALTH_POINTS) {
            healthPoints = MIN_HEALTH_POINTS;
        }
    }

    public void heal(int amount) {
        healthPoints += amount;
        if (healthPoints > type.getHealthPoints()) {
            healthPoints = type.getHealthPoints();
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
