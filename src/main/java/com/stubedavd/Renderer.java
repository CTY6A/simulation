package com.stubedavd;

public class Renderer {
    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render() {
        renderBorder();

        for (int y = worldMap.getHeight() - 1; y >= 0; y--) {
            System.out.print("⬛️");

            for (int x = 0; x < worldMap.getWidth(); x++) {
                Position currentPos = new Position(x, y);
                Entity entity = worldMap.getEntityAt(currentPos);

                if (entity != null) {
                    System.out.print(entity);
                } else {
                    System.out.print("🟫");
                }
            }

            System.out.println("⬛️");
        }

        renderBorder();
    }

    private void renderBorder() {
        for (int i = 0; i < worldMap.getWidth() + 2; i++) {
            System.out.print("⬛️");
        }
        System.out.println();
    }
}