package com.test;

import com.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTree {

    @Test
    public void insertTest() {
        var tree = getTestTree();
        assertNull(tree.dfs(100d));
        tree.insert(100d);
        assertNotNull(tree.dfs(100d));
    }

    @Test
    public void bfsTest() {
        var tree = getTestTree();
        double findData = 4;
        var node = tree.bfs(findData);
        assertNotNull(node);
        assertEquals(findData, node.getData());
    }

    @Test
    public void dfsTest() {
        var tree = getTestTree();
        double findData = 4;
        var node = tree.dfs(findData);
        assertNotNull(node);
        assertEquals(findData, node.getData());
    }

    @Test
    public void nodeConsumeTest() {
        var tree = getTestTree();
        tree.dfsApply(System.out::println);
    }

    @Test
    public void deleteTest() {
        var tree = getTestTree();

        assertNotNull(tree.dfs(-4));
        var beforeDeleteString = tree.toString();

        tree.delete(-4);

        assertNull(tree.dfs(-4));
        var afterDeleteString = tree.toString();

        assertEquals(beforeDeleteString.replace("-4.0,", ""), afterDeleteString);
    }

    @Test
    public void rotateRightTest() {
        var tree = new Tree();
        tree.insert(0);
        tree.insert(-2);
        tree.insert(-1);
        tree.insert(-4);

        var beforeRotationString = tree.toString();
        tree.rotateRight(tree.dfs(-2));
        var afterRotationString = tree.toString();

        assertEquals(beforeRotationString, afterRotationString);
    }

    @Test
    public void rotateLeftTest() {
        var tree = new Tree();
        tree.insert(0);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        var beforeRotationString = tree.toString();
        tree.rotateLeft(tree.dfs(2));
        var afterRotationString = tree.toString();
        assertEquals(beforeRotationString, afterRotationString);
    }

    @Test
    public void stringRepresentationTest() {
        var tree = getTestTree();
        var stringRepresentation = tree.toString();
        var expectedString = "[-8.0,-6.0,-5.0,-4.0,-3.5,-3.4,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0,5.0,6.0,8.0,]";
        assertEquals(expectedString, stringRepresentation);
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
