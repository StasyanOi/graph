package test;

import org.junit.jupiter.api.Test;
import tree.Tree;

import static org.junit.jupiter.api.Assertions.*;

public class TestTree {

    @Test
    public void insert() {
        Tree tree = getTree();
        assertFalse(tree.has(100d));
        tree.insert(100d);
        assertTrue(tree.has(100d));
    }

    @Test
    public void bfs() {
        Tree tree = getTree();
        double findData = 4;
        Tree.Node node = tree.bfs(findData);
        assertTrue(tree.contains(node));
        assertEquals(findData, node.getData());
    }

    @Test
    public void dfsStack() {
        Tree tree = getTree();
        double findData = 4;
        Tree.Node node = tree.dfsStack(findData);
        assertTrue(tree.contains(node));
        assertEquals(findData, node.getData());
    }

    @Test
    public void has() {
        Tree tree = getTree();
        assertTrue(tree.has(-2d));
        assertFalse(tree.has(-200d));
        assertFalse(tree.has(-324d));
        assertFalse(tree.has(-0.123d));
    }

    @Test
    public void nodeConsume() {
        Tree tree = getTree();
        tree.dfsApply(System.out::println);
    }

    @Test
    public void delete() {
        Tree tree = getTree();
        assertTrue(tree.has(-4));
        String beforeDelete = tree.toString();
        tree.delete(-4);
        assertFalse(tree.has(-4));
        String afterDelete = tree.toString();
        assertEquals(beforeDelete.replace("-4.0,", ""), afterDelete);
    }

    @Test
    public void rotateRight() {
        Tree tree = new Tree();
        tree.insert(0);
        tree.insert(-2);
        tree.insert(-1);
        tree.insert(-4);
        String beforeRotation = tree.toString();
        tree.rotateRight(tree.find(-2));
        String afterRotation = tree.toString();
        assertEquals(beforeRotation, afterRotation);
    }

    @Test
    public void rotateLeft() {
        Tree tree = new Tree();
        tree.insert(0);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        String beforeRotation = tree.toString();
        tree.rotateLeft(tree.find(2));
        String afterRotation = tree.toString();
        assertEquals(beforeRotation, afterRotation);
    }

    @Test
    public void dfs() {
        Tree tree = getTree();
        String stringRepresentation = tree.toString();
        String expectedString = "[-8.0,-6.0,-5.0,-4.0,-3.5,-3.4,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0,5.0,6.0,8.0,]";
        assertEquals(expectedString,stringRepresentation);
    }

    @Test
    public void find() {
        Tree tree = getTree();
        tree.find(2);
    }

    public Tree getTree() {
        Tree tree = new Tree();
        tree.insert(0);
        tree.insert(-2);
        tree.insert(-4);
        tree.insert(-6);
        tree.insert(-8);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(-1);
        tree.insert(-3);
        tree.insert(-3.5d);
        tree.insert(-3.4d);
        tree.insert(-5);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        return tree;
    }
}
