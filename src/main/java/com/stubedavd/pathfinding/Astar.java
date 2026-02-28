package com.stubedavd.pathfinding;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;
import com.stubedavd.elements.Grass;

import java.util.*;

public class Astar {
    private Position startPosition;
    private ArrayList<Position> path;
    private HashSet<AstarNode> visited;
    private PriorityQueue<AstarNode> queue;
    private WorldMap worldMap;
    private Position endPosition;

    private AstarNode startNode;
    private AstarNode endNode;



    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Astar(WorldMap worldMap, Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.path = new ArrayList<>();
        this.visited = new HashSet<>();
        this.queue = new PriorityQueue<>();
        this.worldMap = worldMap;
        this.endPosition = null;
    }

    public ArrayList<Position> getPath() {
        return path;
    }

    public ArrayList<Position> findPath(Position startPosition, Position endPosition) {
        this.endPosition = worldMap.findClosestTargetByClass(this.startPosition, Grass.class);
        if (this.endPosition != null) {
            startNode = new AstarNode(startPosition);
            endNode = new AstarNode(endPosition);
            queue.add(startNode);

            while (!queue.isEmpty()) {
                AstarNode current = queue.poll();
                if (current.equals(endNode)) {
                    path.clear();
                    while (current != null) {
                        path.add(current.getPosition());
                        current = current.getParent();
                    }
                    Collections.reverse(path);
                    return path;
                }
                visited.add(current);

                ArrayList<AstarNode> neighbors = new ArrayList<>();
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        AstarNode neighbor = new AstarNode(new Position(current.getPosition().getX() + i, current.getPosition().getY() + j));
                        if (worldMap.isValidPosition(neighbor.getPosition()) && !visited.contains(neighbor) && (worldMap.isEmptyPosition(neighbor.getPosition()) || neighbor.getPosition().equals(endPosition)/* || worldMap.getEntityAt(neighbor.getPosition()) instanceof Grass*/)) {
                            neighbors.add(neighbor);
                        }
                    }
                }
                for (AstarNode neighbor : neighbors) {
                    int newG = current.getG() + 1;
                    if (queue.contains(neighbor)) {
                        AstarNode queueNode = queue.stream().filter(n -> n.equals(neighbor)).findFirst().get();
                        queue.remove(queueNode);
                        if (newG < queueNode.getG()) {
                            queueNode.setG(newG);
                            queueNode.setH(neighbor.getPosition().distanceTo(endNode.getPosition()));
                            queueNode.setF(newG + queueNode.getH());
                            queueNode.setParent(current);
                            queue.add(queueNode);
                        }
                    } else {
                        neighbor.setG(newG);
                        neighbor.setH(neighbor.getPosition().distanceTo(endNode.getPosition()));
                        neighbor.setF(newG + neighbor.getH());
                        neighbor.setParent(current);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return null;
    }
}
