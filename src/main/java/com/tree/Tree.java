package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {

    private Node start;

    public void insert(double data) {
        if (start == null) {
            start = new Node(data);
        } else {
            boolean inserted = false;
            Node currentNode = start;
            while (!inserted) {
                if (data < currentNode.data) {
                    if (currentNode.left == null) {
                        currentNode.left = new Node(data);
                        currentNode.left.prev = currentNode;
                        inserted = true;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else if (currentNode.data < data) {
                    if (currentNode.right == null) {
                        currentNode.right = new Node(data);
                        currentNode.right.prev = currentNode;
                        inserted = true;
                    } else {
                        currentNode = currentNode.right;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void rotateTree(Node node) {
        if (heightDiff(node) == 2) {
            rotateRight(node.left);
        } else if (heightDiff(node) == -2) {
            rotateLeft(node.right);
        }

        if (node.left != null) {
            rotateTree(node.left);
        }
        if (node.right != null) {
            rotateTree(node.right);
        }
    }

    private int heightDiff(Node node) {
        Node leftNode = node.left;
        Node rightNode = node.right;
        int leftLevel = 0;
        int rightLevel = 0;

        if (leftNode != null) {
            leftLevel = leftNode.level;
        }

        if (rightNode != null) {
            rightLevel = rightNode.level;
        }
        return leftLevel - rightLevel;
    }

    private void unwind(Node node) {
        int level = 1;
        Node currentNode = node;
        while (currentNode != null) {
            currentNode.level = level;
            level++;
            currentNode = currentNode.prev;
        }
    }

    public void delete(double data) {
        var nodeToDelete = dfs(data);
        if (nodeToDelete.right != null) {
            var leftMostNode = leftMostNode(nodeToDelete.right);
            rewireTree(nodeToDelete, leftMostNode);
        } else if (nodeToDelete.left != null) {
            var rightMostNode = rightMostNode(nodeToDelete.left);
            rewireTree(nodeToDelete, rightMostNode);
        } else {
            var parentDeleteNode = nodeToDelete.prev;
            if (parentDeleteNode.left == nodeToDelete) {
                parentDeleteNode.left = null;
            }
            if (parentDeleteNode.right == nodeToDelete) {
                parentDeleteNode.right = null;
            }
        }
    }

    private void rewireTree(Node nodeToDelete, Node replacementNode) {
        double temp = replacementNode.data;
        var parentNode = replacementNode.prev;
        if (parentNode.right == replacementNode) {
            parentNode.right = replacementNode.right;
        }
        if (parentNode.left == replacementNode) {
            parentNode.left = replacementNode.right;
        }
        nodeToDelete.data = temp;
    }

    private Node rightMostNode(Node leftTree) {
        boolean rightMoseNodeFound = false;
        Node current = leftTree;
        while (!rightMoseNodeFound) {
            if (current.right == null) {
                rightMoseNodeFound = true;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    private Node leftMostNode(Node rightTree) {
        boolean leftMostNodeFound = false;
        Node current = rightTree;
        while (!leftMostNodeFound) {
            if (current.left == null) {
                leftMostNodeFound = true;
            } else {
                current = current.left;
            }
        }
        return current;
    }

    public String toString() {
        return "[" + toString0() + "]";
    }

    private String toString0() {
        StringBuilder stringBuilder = new StringBuilder();
        if (start == null) {
            return "";
        }
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        var visitedNodes = new LinkedList<Node>();
        while (stack.size() != 0) {
            Node pop = stack.peek();
            if (pop.left != null && !visitedNodes.contains(pop.left)) {
                stack.add(pop.left);
                continue;
            }
            if (!visitedNodes.contains(pop)) {
                visitedNodes.add(pop);
                stringBuilder.append(pop.data).append(",");
            }
            if (pop.right != null && !visitedNodes.contains(pop.right)) {
                stack.add(pop.right);
                continue;
            }
            stack.pop();
        }
        return stringBuilder.toString();
    }

    public void rotateLeft(Node midNode) {
        Node prev = midNode.prev;
        prev.right = midNode.left;
        midNode.left = prev;
        if (start == prev) {
            start = midNode;
        }
        midNode.prev = prev.prev;
    }

    public void rotateRight(Node midNode) {
        Node prev = midNode.prev;
        prev.left = midNode.right;
        midNode.right = prev;
        if (start == prev) {
            start = midNode;
        }
        midNode.prev = prev.prev;
    }

    public Node bfs(double findData) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() != 0) {
            Node poll = queue.poll();
            if (findData == poll.data) {
                return poll;
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
        }
        return null;
    }

    public Node dfs(double findData) {
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        var visitedNodes = new LinkedList<Node>();
        while (stack.size() != 0) {
            Node pop = stack.peek();
            if (pop.left != null && !visitedNodes.contains(pop.left)) {
                stack.add(pop.left);
                continue;
            }
            if (!visitedNodes.contains(pop)) {
                visitedNodes.add(pop);
            }
            if (pop.data == findData) {
                stack.clear();
                return pop;
            }
            if (pop.right != null && !visitedNodes.contains(pop.right)) {
                stack.add(pop.right);
                continue;
            }
            stack.pop();
        }
        return null;
    }

    public static class Node {
        public int level = 1;
        private Node prev;
        private Node left;
        private Node right;
        private double data;

        public Node(double data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public double getData() {
            return data;
        }
    }
}


