package com.stubedavd.mapobjects;

public enum TreeType {
    EVERGREEN("🌲"),
    DECIDUOUS("🌳"),
    PALM("🌴"),
    BAMBOO("🎍"),
    CACTUS("🌵"),
    PINE("🎄");

    private final String emoji;

    TreeType(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return emoji;
    }

    public static TreeType getRandom() {
        TreeType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}