package com.stubedavd.elements.types;

public enum HerbivoreType {
    DEER("🦌", "Deer", 30, 4),
    RABBIT("🐇", "Rabbit", 15, 3),
    SQUIRREL("🐿️", "Squirrel", 10, 2),
    GOAT("🐐", "Goat", 25, 3),
    SHEEP("🐑", "Sheep", 30, 2),
    COW("🐄", "Cow", 50, 1),
    HORSE("🐎", "Horse", 35, 5),
    ELEPHANT("🐘", "Elephant", 100, 3),
    GIRAFFE("🦒", "Giraffe", 60, 4),
    KOALA("🐨", "Koala", 20, 1),
    PANDA("🐼", "Panda", 40, 1),
    HAMSTER("🐹", "Hamster", 5, 2),
    LLAMA("🦙", "Llama", 30, 3);

    private final String emoji;
    private final String description;
    private final int healthPoints;
    private final int speed;

    HerbivoreType(String emoji, String description, int healthPoints, int speed) {
        this.emoji = emoji;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return emoji;
    }

    public String getDescription() {
        return description;
    }

    public String getEmoji() {
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