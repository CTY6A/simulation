package com.stubedavd.elements.types;

public enum PredatorType {
    WOLF("🐺", "Волк", 25),
    FOX("🦊", "Лиса", 20),
    BEAR("🐻", "Медведь", 35),
    LION("🦁", "Лев", 50),
    TIGER("🐅", "Тигр", 75),
    LEOPARD("🐆", "Леопард", 45),
    CROCODILE("🐊", "Крокодил", 80),
    EAGLE("🦅", "Орёл", 10),
    HAWK("🦉", "Ястреб", 15),
    SNAKE("🐍", "Змея", 5),
    T_REX("🦖", "Тираннозавр", 100),
    HYENA("🦙", "Гиена", 20);

    private final String emoji;
    private final String description;
    private final int damage;

    PredatorType(String emoji, String description, int damage) {
        this.emoji = emoji;
        this.description = description;
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

    public static PredatorType getRandom() {
        PredatorType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }

    public int getDamage() {
        return damage;
    }
}