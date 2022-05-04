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
                        unwind(currentNode);
                    } else {
                        currentNode = currentNode.left;
                    }
                } else if (currentNode.data < data) {
                    if (currentNode.right == null) {
                        currentNode.right = new Node(data);
                        currentNode.right.prev = currentNode;
                        inserted = true;
                        unwind(currentNode);
                    } else {
                        currentNode = currentNode.right;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void unwind(Node node) {
        var currentNode = node;
        while (currentNode != null) {
            if (rotateTree(currentNode)) {
                currentNode = currentNode.prev;
            }
            currentNode = currentNode.prev;
        }
    }

    private boolean rotateTree(Node node) {
        if (heightDiff(node) == 1) {
            rotateRight(node);
            return true;
        } else if (heightDiff(node) == -1) {
            rotateLeft(node);
            return true;
        }
        return false;
    }

    private int heightDiff(Node node) {
        Node leftNode = node.left;
        Node rightNode = node.right;
        return calculateHeight(leftNode) - calculateHeight(rightNode);
    }

    public int calculateHeight() {
        return calculateHeight(start);
    }

    private int calculateHeight(Node treeRoot) {
        int height = 0;
        if (treeRoot == null) {
            return height;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(treeRoot);
        var visitedNodes = new LinkedList<Node>();
        while (stack.size() != 0) {
            Node peek = stack.peek();
            if (peek.left != null && !visitedNodes.contains(peek.left)) {
                stack.push(peek.left);
                if (stack.size() - 1 > height) {
                    height = stack.size() - 1;
                }
                continue;
            }
            if (!visitedNodes.contains(peek)) {
                visitedNodes.add(peek);
            }
            if (peek.right != null && !visitedNodes.contains(peek.right)) {
                stack.push(peek.right);
                if (stack.size() - 1 > height) {
                    height = stack.size() - 1;
                }
                continue;
            }
            stack.pop();
        }
        return height;
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
        double replacementValue = replacementNode.data;
        var parentNode = replacementNode.prev;
        if (parentNode.right == replacementNode) {
            parentNode.right = replacementNode.right;
        }
        if (parentNode.left == replacementNode) {
            parentNode.left = replacementNode.right;
        }
        nodeToDelete.data = replacementValue;
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
                stack.push(pop.left);
                continue;
            }
            if (!visitedNodes.contains(pop)) {
                visitedNodes.add(pop);
                stringBuilder.append(pop.data).append(",");
            }
            if (pop.right != null && !visitedNodes.contains(pop.right)) {
                stack.push(pop.right);
                continue;
            }
            stack.pop();
        }
        return stringBuilder.toString();
    }

    public void rotateLeft(Node root) {
        var pivot = root.right;
        root.right = pivot.left;
        if (root.right != null) {
            root.right.prev = root;
        }
        pivot.left = root;
        rewireNodes(root, pivot);
    }

    public void rotateRight(Node root) {
        var pivot = root.left;
        root.left = pivot.right;
        if (root.left != null) {
            root.left.prev = root;
        }
        pivot.right = root;
        rewireNodes(root, pivot);
    }

    private void rewireNodes(Node root, Node pivot) {
        if (start == root) {
            start = pivot;
        }
        pivot.prev = root.prev;
        root.prev = pivot;
        if (pivot.prev != null) {
            if (pivot.prev.left == root) {
                pivot.prev.left = pivot;
            } else if (pivot.prev.right == root) {
                pivot.prev.right = pivot;
            }
        }
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


