package com.stubedavd;

public class Action {
    public void initActions (WorldMap worldMap) {
        int rocksCount = (worldMap.getWidth() * worldMap.getHeight()) / 4;
        worldMap.generateRandomRocks(rocksCount);

        int grassCount = 3;//(worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomGrass(grassCount);

        int treesCount = (worldMap.getWidth() * worldMap.getHeight()) / 5;
        //worldMap.generateRandomTrees(treesCount);

        int herbivoresCount = 1;//(worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomHerbivores(herbivoresCount);

        int predatorsCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        //worldMap.generateRandomPredators(predatorsCount);
    }

    public void turnActions (WorldMap worldMap) {
        WorldMap worldMapCopy = new WorldMap(worldMap.getWidth(), worldMap.getHeight());
        worldMapCopy.getEntities().putAll(worldMap.getEntities());

        for (Entity entity : worldMap.getEntities().values()) {
            if (worldMapCopy.isEntityExists(entity)) {
                if (entity instanceof Creature) {
                    Creature creature = (Creature) entity;
                    creature.makeMove(worldMapCopy);
                    //creature.takeDamage(25);
                    /*if (creature.getHealthPoints() <= 0) {
                        Position position = creature.getPosition();
                        worldMapCopy.removeEntity(position);
                        worldMapCopy.placeEntity(position, new Rock(position, RockType.GRAVE));
                    }*/
                }
            }
        }

        worldMap.getEntities().clear();
        worldMap.getEntities().putAll(worldMapCopy.getEntities());
    }
}
