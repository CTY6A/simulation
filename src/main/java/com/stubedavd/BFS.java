package com.stubedavd;

import java.util.*;

public class BFS {
    private Position position;
    private ArrayList<Position> path;
    private HashSet<Position> visited;
    private Queue<Position> queue;
    private WorldMap worldMap;
    private Position targetPosition;

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public BFS(WorldMap worldMap, Position position, Position targetPosition) {
        this.position = position;
        this.path = new ArrayList<>();
        this.visited = new HashSet<>();
        this.queue = new LinkedList<>();
        this.worldMap = worldMap;
        this.targetPosition = null;
    }

    public ArrayList<Position> getPath() {
        return path;
    }

    public void findPath() {
        HashMap<Position, Position> parentMap = new HashMap<>();
        parentMap.put(position, null);
        queue.add(position);
        visited.add(position);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (worldMap.getEntityAt(current) instanceof Grass) {
                targetPosition = current;
                break;
            }
            visited.add(current);

            ArrayList<Position> neighbors = new ArrayList<>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    Position neighbor = new Position(current.getX() + i, current.getY() + j);
                    if (worldMap.isValidPosition(neighbor) && !visited.contains(neighbor) && (worldMap.isEmptyPosition(neighbor) || worldMap.getEntityAt(neighbor) instanceof Grass)) {
                        neighbors.add(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }

            queue.addAll(neighbors);
        }
        Position current = parentMap.get(targetPosition);
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        if (!path.isEmpty()) path.remove(0);
    }
}
