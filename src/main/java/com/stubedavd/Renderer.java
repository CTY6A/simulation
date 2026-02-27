package com.stubedavd;

import com.stubedavd.elements.Entity;

public class Renderer {
    public void render(final WorldMap worldMap, final int turnCount) {
        System.out.println("\n--- Turn " + turnCount + " ---");

        renderBorder(worldMap);

        renderBody(worldMap);

        renderBorder(worldMap);

        System.out.println("  space - pause/resume");
        System.out.println("  's'    - stop simulation");
    }

    private void renderBorder(final WorldMap worldMap) {
        for (int i = 0; i < worldMap.getWidth() + 2; i++) {
            System.out.print("⬛️");
        }
        System.out.println();
    }

    private static void renderBody(final WorldMap worldMap) {
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
    }
}