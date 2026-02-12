package com.stubedavd;

public class Action {
    public void initActions (WorldMap worldMap) {
        int grassCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomGrass(grassCount);

        int treesCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomTrees(treesCount);

        int rocksCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomRocks(rocksCount);

        int herbivoresCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomHerbivores(herbivoresCount);

        int predatorsCount = (worldMap.getWidth() * worldMap.getHeight()) / 10;
        worldMap.generateRandomPredators(predatorsCount);
    }

    public void turnActions () {
        
    }
}
