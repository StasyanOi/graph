package com.graph;

import java.util.*;

public class Graph {

    private final List<Node> nodes;

    public Graph(boolean[][] graphMatrix) {
        nodes = createNodes(graphMatrix.length);
        connectNodes(graphMatrix);
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

    private List<Node> createNodes(int amountOfNodes) {
        var nodes = new ArrayList<Node>(amountOfNodes);
        for (int i = 0; i < amountOfNodes; i++) {
            nodes.add(new Node(new ArrayList<>(), i));
        }
        return nodes;
    }

    public boolean bfs(int searchData) {
        var startNode = nodes.get(0);
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(startNode);

        while (nodeQueue.size() != 0) {
            var currentNode = nodeQueue.poll();
            if (searchData == currentNode.data) {
                nodeQueue.clear();
                setAllNotVisited();
                return true;
            }
            for (int i = 0; i < currentNode.nextNodes.size(); i++) {
                var nextNode = currentNode.nextNodes.get(i);
                if (!nextNode.visited) {
                    nodeQueue.add(nextNode);
                }
            }
        }
        setAllNotVisited();
        return false;
    }

    public boolean dfs(int searchData) {
        var stack = new Stack<Node>();
        stack.push(nodes.get(0));

        while (!stack.empty()) {
            var currentNode = stack.peek();
            currentNode.visited = true;
            if (currentNode.data == searchData) {
                stack.clear();
                setAllNotVisited();
                return true;
            }
            var nextNodes = currentNode.nextNodes;
            if (nextNodes.size() == 0 || allNodesVisited(nextNodes)) {
                stack.pop();
            } else {
                for (var nextNode : nextNodes) {
                    if (!nextNode.visited) {
                        stack.add(nextNode);
                        break;
                    }
                }
            }
        }
        return false;
    }

    private boolean allNodesVisited(List<Node> nextNodes) {
        for (var nextNode : nextNodes) {
            if (!nextNode.visited) {
                return false;
            }
        }
        return true;
    }

    private void setAllNotVisited() {
        nodes.forEach(node -> node.visited = false);
    }

    private static class Node {
        private final int data;
        private boolean visited;
        private final List<Node> nextNodes;

        private Node(List<Node> nextNodes, int data) {
            this.nextNodes = nextNodes;
            this.data = data;
        }
    }
}
