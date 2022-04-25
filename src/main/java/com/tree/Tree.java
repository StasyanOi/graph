package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

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
        Node nodeToDelete = find(data);
        if (nodeToDelete.right != null) {
            Node leftMostNode = leftMostNode(nodeToDelete.right);
            double temp = leftMostNode.data;
            if (leftMostNode.prev.right == leftMostNode) {
                leftMostNode.prev.right = leftMostNode.right;
            }
            if (leftMostNode.prev.left == leftMostNode) {
                leftMostNode.prev.left = leftMostNode.right;
            }
            nodeToDelete.data = temp;
        } else if (nodeToDelete.left != null) {
            Node rightMostNode = rightMostNode(nodeToDelete.left);
            double temp = rightMostNode.data;
            if (rightMostNode.prev.right == rightMostNode) {
                rightMostNode.prev.right = rightMostNode.right;
            }
            if (rightMostNode.prev.left == rightMostNode) {
                rightMostNode.prev.left = rightMostNode.right;
            }
            delete(temp);
            nodeToDelete.data = temp;
        } else {
            Node parentDeleteNode = nodeToDelete.prev;
            if (parentDeleteNode.left == nodeToDelete) {
                parentDeleteNode.left = null;
            }
            if (parentDeleteNode.right == nodeToDelete) {
                parentDeleteNode.right = null;
            }
        }
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

    public Node find(double searchData) {
        if (start == null) {
            return null;
        }
        return start.dfs(searchData);
    }

    public String toString() {
        return "[" + toString0() + "]";
    }

    private String toString0() {
        StringBuilder stringBuilder = new StringBuilder();
        if (start == null) {
            return "";
        }
        start.string(stringBuilder);
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


    public boolean has(double element) {
        Node dfs = find(element);
        return dfs != null;
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

    public boolean contains(Node node) {
        return start.contains(node) != null;
    }

    public Node dfsStack(double findData) {
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        while (stack.size() != 0) {
            Node pop = stack.pop();
            if (findData == pop.data) {
                return pop;
            }
            if (pop.right != null) {
                stack.add(pop.right);
            }
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }
        return null;
    }

    public void dfsApply(Consumer<Node> nodeConsumer) {
        Stack<Node> stack = new Stack<>();
        stack.add(start);
        while (stack.size() != 0) {
            Node pop = stack.peek();
            if (pop.left != null && !pop.left.visited) {
                stack.add(pop.left);
                continue;
            }
            if (!pop.visited) {
                nodeConsumer.accept(pop);
                pop.visited = true;
            }
            if (pop.right != null && !pop.right.visited) {
                stack.add(pop.right);
                continue;
            }
            stack.pop();
        }
    }

    public static class Node {
        public int level = 1;
        private Node prev;
        private Node left;
        private Node right;
        private double data;
        private boolean visited;

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

        public Node dfs(double searchData) {
            if (left != null) {
                Node dfs = left.dfs(searchData);
                if (dfs != null) {
                    return dfs;
                }
            }
            if (data == searchData) {
                return this;
            }
            if (right != null) {
                Node dfs = right.dfs(searchData);
                if (dfs != null) {
                    return dfs;
                }
            }
            return null;
        }

        public Node contains(Node searchData) {
            if (left != null) {
                Node dfs = left.contains(searchData);
                if (dfs != null) {
                    return dfs;
                }
            }
            if (this == searchData) {
                return this;
            }
            if (right != null) {
                Node dfs = right.contains(searchData);
                if (dfs != null) {
                    return dfs;
                }
            }
            return null;
        }

        public void string(StringBuilder stringBuilder) {
            if (left != null) {
                left.string(stringBuilder);
            }
            stringBuilder.append(data).append(",");
            if (right != null) {
                right.string(stringBuilder);
            }
        }
    }
}


