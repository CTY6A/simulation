package com.stubedavd.elements.types;

import com.stubedavd.elements.Entity;
import com.stubedavd.elements.Herbivore;

public enum PredatorType implements CreatureType{
    WOLF("🐺", 30, 4, 15),
    FOX("🦊", 20, 4, 10),
    BEAR("🐻", 60, 3, 25),
    LION("🦁", 50, 5, 30),
    TIGER("🐅", 55, 5, 35),
    LEOPARD("🐆", 40, 6, 25),
    CROCODILE("🐊", 70, 2, 40),
    EAGLE("🦅", 25, 8, 15),
    HAWK("🦉", 20, 7, 12),
    SNAKE("🐍", 15, 3, 20),
    T_REX("🦖",  100, 3, 50),
    HYENA("🦙", 35, 5, 18);

    private final String emoji;
    private final int healthPoints;
    private final int speed;
    private final int damage;

    PredatorType(String emoji, int healthPoints, int speed, int damage) {
        this.emoji = emoji;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.damage = damage;
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

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Herbivore.class;
    }

    public int getDamage() {
        return damage;
    }

    public static PredatorType getRandom() {
        PredatorType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}