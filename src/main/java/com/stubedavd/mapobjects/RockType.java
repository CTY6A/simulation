package com.stubedavd.mapobjects;

public enum RockType {
    MOUNTAIN("\u26F0\uFE0F"),
    ROCK("\uD83D\uDDFB");

    private final String emoji;

    RockType(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return emoji;
    }

    public static RockType getRandom() {
        RockType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}