package com.test;

import com.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTree {

    @Test
    public void insertTest() {
        Tree tree = getTestTree();
        assertFalse(tree.has(100d));
        tree.insert(100d);
        assertTrue(tree.has(100d));
    }

    @Test
    public void bfsTest() {
        Tree tree = getTestTree();
        double findData = 4;
        Tree.Node node = tree.bfs(findData);
        assertTrue(tree.contains(node));
        assertEquals(findData, node.getData());
    }

    @Test
    public void dfsStackTest() {
        Tree tree = getTestTree();
        double findData = 4;
        Tree.Node node = tree.dfsStack(findData);
        assertTrue(tree.contains(node));
        assertEquals(findData, node.getData());
    }

    @Test
    public void hasTest() {
        Tree tree = getTestTree();
        assertTrue(tree.has(-2d));
        assertFalse(tree.has(-200d));
        assertFalse(tree.has(-324d));
        assertFalse(tree.has(-0.123d));
    }

    @Test
    public void nodeConsumeTest() {
        Tree tree = getTestTree();
        tree.dfsApply(System.out::println);
    }

    @Test
    public void deleteTest() {
        Tree tree = getTestTree();
        assertTrue(tree.has(-4));
        String beforeDelete = tree.toString();
        tree.delete(-4);
        assertFalse(tree.has(-4));
        String afterDelete = tree.toString();
        assertEquals(beforeDelete.replace("-4.0,", ""), afterDelete);
    }

    @Test
    public void rotateRightTest() {
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
    public void rotateLeftTest() {
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
    public void dfsTest() {
        Tree tree = getTestTree();
        String stringRepresentation = tree.toString();
        String expectedString = "[-8.0,-6.0,-5.0,-4.0,-3.5,-3.4,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0,5.0,6.0,8.0,]";
        assertEquals(expectedString,stringRepresentation);
    }

    @Test
    public void findTest() {
        Tree tree = getTestTree();
        tree.find(2);
    }

    private Tree getTestTree() {
        var tree = new Tree();
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
