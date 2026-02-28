package com.stubedavd.elements.types;

public enum PredatorType {
    WOLF("🐺", "Wolf", 30, 4, 15),
    FOX("🦊", "Fox", 20, 4, 10),
    BEAR("🐻", "Bear", 60, 3, 25),
    LION("🦁", "Lion", 50, 5, 30),
    TIGER("🐅", "Tiger", 55, 5, 35),
    LEOPARD("🐆", "Leopard", 40, 6, 25),
    CROCODILE("🐊", "Crocodile", 70, 2, 40),
    EAGLE("🦅", "Eagle", 25, 8, 15),
    HAWK("🦉", "Hawk", 20, 7, 12),
    SNAKE("🐍", "Snake", 15, 3, 20),
    T_REX("🦖", "T-Rex", 100, 3, 50),
    HYENA("🦙", "Hyena", 35, 5, 18);

    private final String emoji;
    private final String description;
    private final int healthPoints;
    private final int speed;
    private final int damage;

    PredatorType(String emoji, String description, int healthPoints, int speed, int damage) {
        this.emoji = emoji;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }

    public static PredatorType getRandom() {
        PredatorType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}