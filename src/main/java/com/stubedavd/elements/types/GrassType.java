package com.stubedavd.elements.types;

public enum GrassType {
    FRESH("🌱", "Fresh grass"),
    GREEN("🌿", "Green grass"),
    CLOVER("☘️", "Clover"),
    HERB("🌾", "Grain"),
    FIELD("🍀", "Field grass"),
    WILTED("🥀", "Wilted grass");

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