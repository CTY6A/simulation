package com.stubedavd.elements.types;

public enum TreeType {
    EVERGREEN("🌲", "Evergreen tree"),
    DECIDUOUS("🌳", "Deciduous tree"),
    PALM("🌴", "Palm tree"),
    BAMBOO("🎍", "Bamboo"),
    CACTUS("🌵", "Cactus"),
    PINE("🎄", "Pine tree");

    private final String emoji;
    private final String description;

    TreeType(String emoji, String description) {
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

    public static TreeType getRandom() {
        TreeType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}