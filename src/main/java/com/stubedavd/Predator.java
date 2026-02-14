package com.stubedavd;

public class Predator extends Creature {
    private final PredatorType type;

    public Predator(Position position) {
        super(position);
        this.type = PredatorType.getRandom();
    }

    public Predator(Position position, PredatorType type) {
        super(position);
        this.type = type;
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
