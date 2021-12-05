package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private final List<Node> nodes;

    public Graph(boolean[][] graphMatrix) {
        nodes = new ArrayList<>(graphMatrix.length);
        for (int i = 0; i < graphMatrix.length; i++) {
            nodes.add(new Node(new ArrayList<>(), i));
        }
        for (int i = 0; i < nodes.size(); i++) {
            for (int i1 = 0; i1 < graphMatrix.length; i1++) {
                if (graphMatrix[i][i1]) {
                    nodes.get(i).nexts.add(nodes.get(i1));
                }
            }
        }
    }

    public boolean bfs(int searchData) {
        Node startNode = nodes.get(0);
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(startNode);

        while (nodeQueue.size() != 0) {
            Node currentNode = nodeQueue.poll();
            if (searchData == currentNode.data) {
                nodeQueue.clear();
                setAllNotVisited();
                return true;
            }
            for (int i = 0; i < currentNode.nexts.size(); i++) {
                Node e = currentNode.nexts.get(i);
                if (!e.visited) {
                    nodeQueue.add(e);
                }
            }
        }
        setAllNotVisited();
        return false;
    }

    public boolean dfsRecursive(int searchData) {
        Node currentNode = nodes.get(0);
        boolean found = currentNode.find(searchData);
        setAllNotVisited();
        return found;
    }

    public boolean bfsLoop(int searchData) {
        Node currentNode = nodes.get(0);
        boolean found = currentNode.find(searchData);
        setAllNotVisited();
        return found;
    }

    private void setAllNotVisited() {
        nodes.forEach(node -> node.visited = false);
    }

    private static class Node {
        private final int data;
        private boolean visited;
        private final List<Node> nexts;

        private Node(List<Node> nexts, int data) {
            this.nexts = nexts;
            this.data = data;
        }

        public boolean find(int searchData) {
            if (searchData == data) {
                visited = true;
                return true;
            } else {
                visited = true;
                boolean[] finds = new boolean[nexts.size()];
                for (int i = 0; i < nexts.size(); i++) {
                    Node node = nexts.get(i);
                    if (!node.visited) {
                        finds[i] = node.find(searchData);
                    }
                }
                return or(finds);
            }
        }

        private boolean or(boolean[] finds) {
            boolean finalFind = false;
            for (int i = 0; i < finds.length; i++) {
                finalFind |= finds[i];
            }
            return finalFind;
        }
    }
}
