package com.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    @Test
    void bfsSuccessTest() {
        var graph = getTestGraph();
        assertTrue(graph.bfs(2));
    }

    @Test
    void bfsFailTest() {
        var graph = getTestGraph();
        assertFalse(graph.bfs(100));
    }

    @Test
    void dfsSuccessTest() {
        var graph = getTestGraph();
        assertTrue(graph.dfs(2));
    }

    @Test
    void dfsFailTest() {
        var graph = getTestGraph();
        assertFalse(graph.dfs(100));
    }


    private Graph getTestGraph() {
        int numberOfNodes = 5;
        var connectionMatrix = new boolean[numberOfNodes][numberOfNodes];
        connectionMatrix[0][1] = true;
        connectionMatrix[1][2] = true;
        connectionMatrix[1][3] = true;
        connectionMatrix[1][4] = true;
        return new Graph(connectionMatrix);
    }
}