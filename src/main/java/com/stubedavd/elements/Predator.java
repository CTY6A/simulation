package com.stubedavd.elements;

import com.stubedavd.Position;
import com.stubedavd.elements.types.PredatorType;
import com.stubedavd.WorldMap;

public class Predator extends Creature {
    private final PredatorType type;
    private final int damage;

    public Predator(Position position) {
        super(position);
        this.type = PredatorType.getRandom();
        this.damage = type.getDamage();
    }

    public Predator(Position position, PredatorType type) {
        super(position);
        this.type = type;
        this.damage = type.getDamage();
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        Herbivore herbivore = null;
        Position newPosition = worldMap.getRandomEmptyPosition();

        for (Entity entity : worldMap.getEntities().values()) {
            if (entity instanceof Herbivore) {
                herbivore = (Herbivore) entity;
                break;
            }
        }

        if (herbivore != null) {
            worldMap.removeEntity(herbivore.getPosition());
            this.heal(10);
            newPosition = herbivore.getPosition();
        }

        worldMap.moveEntity(this, newPosition);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public PredatorType getType() {
        return type;
    }
}
