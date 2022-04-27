package com.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void bfsSuccessTest() {
        var graph = getTestGraph();
        assertNotNull(graph.bfs(2));
    }

    @Test
    void bfsFailTest() {
        var graph = getTestGraph();
        assertNull(graph.bfs(100));
    }

    @Test
    void dfsSuccessTest() {
        var graph = getTestGraph();
        assertNotNull(graph.dfs(2));
    }

    @Test
    void dfsFailTest() {
        var graph = getTestGraph();
        assertNull(graph.dfs(100));
    }


    private Graph getTestGraph() {
        int numberOfNodes = 5;
        var connectionMatrix = new boolean[numberOfNodes][numberOfNodes];
        connectionMatrix[0][1] = true;
        connectionMatrix[1][2] = true;
        connectionMatrix[1][3] = true;
        connectionMatrix[1][4] = true;
        var valueMatrix = new int[numberOfNodes];
        valueMatrix[0] = 0;
        valueMatrix[1] = 1;
        valueMatrix[2] = 2;
        valueMatrix[3] = 3;
        valueMatrix[4] = 4;
        return new Graph(connectionMatrix, valueMatrix);
    }
}