package com.stubedavd.elements.types;

public enum HerbivoreType {
    DEER("🦌", 30, 4),
    RABBIT("🐇", 15, 3),
    SQUIRREL("🐿️", 10, 2),
    GOAT("🐐", 25, 3),
    SHEEP("🐑", 30, 2),
    COW("🐄", 50, 1),
    HORSE("🐎", 35, 5),
    ELEPHANT("🐘", 100, 3),
    GIRAFFE("🦒",  60, 4),
    KOALA("🐨", 20, 1),
    PANDA("🐼", 40, 1),
    HAMSTER("🐹", 5, 2),
    LLAMA("🦙", 30, 3);

    private final String emoji;
    private final int healthPoints;
    private final int speed;

    HerbivoreType(String emoji, int healthPoints, int speed) {
        this.emoji = emoji;
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return emoji;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public static HerbivoreType getRandom() {
        HerbivoreType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}