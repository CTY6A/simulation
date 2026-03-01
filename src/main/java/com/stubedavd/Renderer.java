package com.stubedavd;

import com.stubedavd.elements.*;

import java.util.Map;

public class Renderer {
    public void render(final WorldMap worldMap, final int turnCount) {
        StringBuilder result = new StringBuilder();
        result.append("--- Turn ").append(turnCount).append(" ---\n");

        result.append(renderBorder(worldMap));

        result.append(renderBody(worldMap));

        result.append(renderBorder(worldMap));

        result.append(renderStats(worldMap));

        result.append("  Enter - pause/resume\n");
        System.out.println(result);
    }

    private StringBuilder renderBorder(final WorldMap worldMap) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < worldMap.getWidth() + 2; i++) {
            result.append("⬛️");
        }
        result.append("\n");
        return result;
    }

    private StringBuilder renderBody(final WorldMap worldMap) {
        StringBuilder result = new StringBuilder();
        for (int y = worldMap.getHeight() - 1; y >= 0; y--) {
            result.append("⬛️");
            for (int x = 0; x < worldMap.getWidth(); x++) {
                Position currentPos = new Position(x, y);
                Entity entity = worldMap.getEntityAt(currentPos);

                if (entity == null) {
                    result.append("🟫");
                } else {
                    result.append(entity);
                }
            }

            result.append("⬛️\n");
        }
        return result;
    }

    private StringBuilder renderStats(WorldMap worldMap) {
        StringBuilder result = new StringBuilder();

        result.append("  Herbivores: ");
        Map<Position, Entity> entities = worldMap.getEntitiesByClass(Herbivore.class);
        result.append(entities.size());

        result.append("  Predators: ");
        entities = worldMap.getEntitiesByClass(Predator.class);
        result.append(entities.size());

        result.append("  Grass: ");
        entities = worldMap.getEntitiesByClass(Grass.class);
        result.append(entities.size());
        return result;
    }
}
