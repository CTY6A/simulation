package com.stubedavd;

public enum RockType {
    MOUNTAIN("\u26F0\uFE0F", "Гора"),
    //STONEHENGE("\uD83D\uDDFF", "Стоунхендж"),
    ROCK("\uD83D\uDDFB", "Скала"),
    GRAVE("⚰\uFE0F", "Могильный камень");

    private final String emoji;
    private final String description;

    RockType(String emoji, String description) {
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

    public static RockType getRandom() {
        RockType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}
