package com.stubedavd.elements.types;

public enum GrassType {
    FRESH("🌱"),
    GREEN("🌿"),
    CLOVER("☘️"),
    HERB("🌾"),
    FIELD("🍀"),
    WILTED("🥀");

    private final String emoji;

    GrassType(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return emoji;
    }

    public static GrassType getRandom() {
        GrassType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}