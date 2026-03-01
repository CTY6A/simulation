package com.stubedavd.pathfinding;

import com.stubedavd.Position;
import com.stubedavd.WorldMap;

import java.util.*;

public class Astar {
    private final WorldMap worldMap;

    private Node endNode;
    private final PriorityQueue<Node> queue;
    private final HashSet<Node> visited;

    public Astar(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.queue = new PriorityQueue<>();
        this.visited = new HashSet<>();
    }

    public ArrayList<Position> findPath(Position startPosition, Position endPosition) {
        if (startPosition == null || endPosition == null) {
            return new ArrayList<>();
        }
        Node startNode = new Node(startPosition);
        endNode = new Node(endPosition);

        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.equals(endNode)) {
                return pathFormer(currentNode);
            }

            visited.add(currentNode);
            ArrayList<Node> neighbors = getNeighbors(currentNode);
            addNeighborsToQueue(currentNode, neighbors);
        }
        return new ArrayList<>();
    }

    private void addNeighborsToQueue(Node currentNode, ArrayList<Node> neighbors) {
        for (Node neighbor : neighbors) {
            int newG = currentNode.getG() + 1;
            if (queue.contains(neighbor)) {
                Node queueNode = queue.stream().filter(n -> n.equals(neighbor)).findFirst().get();
                queue.remove(queueNode);
                if (newG < queueNode.getG()) {
                    queueNode.setG(newG);
                    queueNode.setH(neighbor.getPosition().distanceTo(endNode.getPosition()));
                    queueNode.setF(newG + queueNode.getH());
                    queueNode.setParent(currentNode);
                    queue.add(queueNode);
                }
            } else {
                neighbor.setG(newG);
                neighbor.setH(neighbor.getPosition().distanceTo(endNode.getPosition()));
                neighbor.setF(newG + neighbor.getH());
                neighbor.setParent(currentNode);
                queue.add(neighbor);
            }
        }
    }

    private ArrayList<Node> getNeighbors(Node currentNode) {
        ArrayList<Node> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int currentX = currentNode.getPosition().getX() + i;
                int currentY = currentNode.getPosition().getY() + j;
                Position currentPosition = new Position(currentX, currentY);
                Node neighbor = new Node(currentPosition);
                if (neighbor.equals(endNode)) {
                    result.add(neighbor);
                    return result;
                }
                if (visited.contains(neighbor)) {
                    continue;
                }
                if (worldMap.isEmptyPosition(currentPosition)) {
                    result.add(neighbor);
                }
            }
        }
        return result;
    }

    private ArrayList<Position> pathFormer(Node currentNode) {
        ArrayList<Position> result = new ArrayList<>();
        while (currentNode != null) {
            result.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(result);
        return result;
    }
}
