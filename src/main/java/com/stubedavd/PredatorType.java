package com.stubedavd;

public enum PredatorType {
    WOLF("🐺", "Волк"),
    FOX("🦊", "Лиса"),
    BEAR("🐻", "Медведь"),
    LION("🦁", "Лев"),
    TIGER("🐅", "Тигр"),
    LEOPARD("🐆", "Леопард"),
    CROCODILE("🐊", "Крокодил"),
    SHARK("🦈", "Акула"),
    EAGLE("🦅", "Орёл"),
    HAWK("🦉", "Ястреб"),
    SNAKE("🐍", "Змея"),
    T_REX("🦖", "Тираннозавр"),
    HYENA("🦙", "Гиена");

    private final String emoji;
    private final String description;

    PredatorType(String emoji, String description) {
        this.emoji = emoji;
        this.description = description;
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
}