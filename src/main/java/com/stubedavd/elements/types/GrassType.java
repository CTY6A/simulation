package com.stubedavd.elements.types;

public enum GrassType {
    FRESH("🌱", "Свежая трава"),
    GREEN("🌿", "Зелёная трава"),
    CLOVER("☘️", "Клевер"),
    HERB("🌾", "Злак"),
    FIELD("🍀", "Полевая трава"),
    WILTED("🥀", "Увядшая трава");

    private final String emoji;
    private final String description;

    GrassType(String emoji, String description) {
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

    public static GrassType getRandom() {
        GrassType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}
