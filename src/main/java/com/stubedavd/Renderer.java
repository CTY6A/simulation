package com.stubedavd;

/**
 * Responsible for rendering the game world to console
 */
public class Renderer {
    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    /**
     * Renders the entire world map to console
     */
    public void render() {
        System.out.println("=== SIMULATION WORLD ===");
        
        renderBorder();

        for (int y = worldMap.getHeight() - 1; y >= 0; y--) {
            System.out.print("⬛️"); // Left border

            for (int x = 0; x < worldMap.getWidth(); x++) {
                Position currentPos = new Position(x, y);
                Entity entity = worldMap.getEntityAt(currentPos);

                if (entity != null) {
                    System.out.print(entity);
                } else {
                    System.out.print("🟫"); // Empty space is represented by a brown square
                }
            }

            System.out.println("⬛️"); // Right border
        }

        renderBorder();
        System.out.println("Grass count: " + worldMap.getEntities().size());
    }

    /**
     * Renders border of the map
     */
    private void renderBorder() {
        for (int i = 0; i < worldMap.getWidth() + 2; i++) {
            System.out.print("⬛️");
        }
        System.out.println();
    }
}