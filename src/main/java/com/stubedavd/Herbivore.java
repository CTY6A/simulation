package com.stubedavd;

public class Herbivore extends Creature {
    private final HerbivoreType type;

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
        Grass grass = null;
        Position newPosition = worldMap.getRandomEmptyPosition();

        for (Entity entity : worldMap.getEntities().values()) {
            if (entity instanceof Grass) {
                grass = (Grass) entity;
                break;
            }
        }

        if (grass != null) {
            worldMap.removeEntity(grass.getPosition());
            this.heal(10);
            newPosition = grass.getPosition();
        }

        worldMap.moveEntity(this, newPosition);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public HerbivoreType getType() {
        return type;
    }
}
