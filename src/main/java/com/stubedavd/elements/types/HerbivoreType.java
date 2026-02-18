package com.stubedavd.elements.types;

public enum HerbivoreType {
    DEER("🦌", "Олень"),
    RABBIT("🐇", "Кролик"),
    SQUIRREL("🐿️", "Белка"),
    GOAT("🐐", "Коза"),
    SHEEP("🐑", "Овца"),
    COW("🐄", "Корова"),
    HORSE("🐎", "Лошадь"),
    ELEPHANT("🐘", "Слон"),
    GIRAFFE("🦒", "Жираф"),
    KOALA("🐨", "Коала"),
    PANDA("🐼", "Панда"),
    HAMSTER("🐹", "Хомяк"),
    LLAMA("🦙", "Лама");

    private final String emoji;
    private final String description;

    HerbivoreType(String emoji, String description) {
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

    public static HerbivoreType getRandom() {
        HerbivoreType[] types = values();
        return types[(int) (Math.random() * types.length)];
    }
}