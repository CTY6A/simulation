package com.stubedavd.pathfinding;

import com.stubedavd.core.Position;
import com.stubedavd.core.WorldMap;

import java.util.*;

public class AStar {
    public static final int ONE_STEP = 1;
    private final WorldMap worldMap;

    private Node endNode;
    private final PriorityQueue<Node> queue;
    private final HashSet<Node> visited;

    public AStar(WorldMap worldMap) {
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
                return pathFormer(currentNode.getParent());
            }

            visited.add(currentNode);
            ArrayList<Node> neighbors = getNeighbors(currentNode);
            addNeighborsToQueue(currentNode, neighbors);
        }
        return new ArrayList<>();
    }

    private ArrayList<Position> pathFormer(Node targetNode) {
        ArrayList<Position> result = new ArrayList<>();
        if (targetNode == null) {
            return result;
        }
        Node currentNode = targetNode;
        while (currentNode.getParent() != null) {
            result.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(result);
        return result;
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

    private void addNeighborsToQueue(Node currentNode, ArrayList<Node> neighbors) {
        for (Node neighbor : neighbors) {
            int newG = currentNode.getG() + ONE_STEP;
            if (queue.contains(neighbor)) {
                Node queueNode = queue.stream().filter(n -> n.equals(neighbor)).findFirst().get();
                queue.remove(queueNode);
                if (newG < queueNode.getG()) {
                    setNode(currentNode, queueNode, newG);
                    queue.add(queueNode);
                }
            } else {
                setNode(currentNode, neighbor, newG);
                queue.add(neighbor);
            }
        }
    }

    private void setNode(Node currentNode, Node targetNode, int newG) {
        targetNode.setG(newG);
        targetNode.setH(targetNode.getPosition().distanceTo(endNode.getPosition()));
        targetNode.setF(newG + targetNode.getH());
        targetNode.setParent(currentNode);
    }
}
