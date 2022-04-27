package com.graph;

import java.util.*;

public class Graph {

    private final List<Node> nodes;

    public Graph(boolean[][] connectionMatrix, int[] valueMatrix) {
        nodes = createNodes(valueMatrix);
        connectNodes(connectionMatrix);
    }

    private void connectNodes(boolean[][] graphMatrix) {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (graphMatrix[i][j]) {
                    nodes.get(i).nextNodes.add(nodes.get(j));
                }
            }
        }
    }

    private List<Node> createNodes(int[] valueMatrix) {
        var nodes = new ArrayList<Node>(valueMatrix.length);
        for (int initialNodeValue : valueMatrix) {
            nodes.add(new Node(new ArrayList<>(), initialNodeValue));
        }
        return nodes;
    }

    public Node bfs(int searchData) {
        var startNode = nodes.get(0);
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(startNode);
        var visitedNodes = new LinkedList<Node>();
        while (nodeQueue.size() != 0) {
            var currentNode = nodeQueue.poll();
            if (searchData == currentNode.data) {
                nodeQueue.clear();
                return currentNode;
            }
            visitedNodes.add(currentNode);
            for (int i = 0; i < currentNode.nextNodes.size(); i++) {
                var nextNode = currentNode.nextNodes.get(i);
                if (!visitedNodes.contains(nextNode)) {
                    nodeQueue.add(nextNode);
                }
            }
        }
        return null;
    }

    public Node dfs(int searchData) {
        var stack = new Stack<Node>();
        stack.push(nodes.get(0));
        var visitedNodes = new LinkedList<>();
        while (!stack.empty()) {
            var currentNode = stack.peek();
            if (currentNode.data == searchData) {
                stack.clear();
                return currentNode;
            }
            visitedNodes.add(currentNode);
            var nextNodes = currentNode.nextNodes;
            if (nextNodes.size() == 0 || visitedNodes.containsAll(nextNodes)) {
                stack.pop();
            } else {
                for (var nextNode : nextNodes) {
                    if (!visitedNodes.contains(nextNode)) {
                        stack.push(nextNode);
                        break;
                    }
                }
            }
        }
        return null;
    }

    private record Node(List<Node> nextNodes, int data) {}
}
