package com.stubedavd.view;

import com.stubedavd.core.Position;
import com.stubedavd.core.WorldMap;
import com.stubedavd.models.Entity;
import com.stubedavd.models.Grass;
import com.stubedavd.creatures.Herbivore;
import com.stubedavd.creatures.Predator;

public class Renderer {
    public void render(final WorldMap worldMap, final int turnCount) {
        System.out.printf("--- Turn %d ---\n", turnCount);

        String border = "⬛️".repeat(worldMap.getWidth() + 1);
        System.out.println(border);

        renderBody(worldMap);

        System.out.println(border);

        int grassCount = worldMap.getEntitiesByClass(Grass.class).size();
        int herbivoreCount = worldMap.getEntitiesByClass(Herbivore.class).size();
        int predatorCount = worldMap.getEntitiesByClass(Predator.class).size();
        System.out.printf("  Grass: %d  Herbivores: %d  Predators: %d", grassCount, herbivoreCount, predatorCount);

        System.out.println("  Enter - pause/resume\n");
    }

    private static void renderBody(final WorldMap worldMap) {
        for (int y = 0; y < worldMap.getHeight(); y++) {
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