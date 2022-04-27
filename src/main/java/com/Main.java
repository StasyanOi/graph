package com;

import com.graph.Graph;

public class Main {

    public static void main(String[] args) {
        int numberOfNodes = 5;
        boolean[][] matrix = new boolean[numberOfNodes][numberOfNodes];
        matrix[0][1] = true;
        matrix[0][2] = true;
        matrix[1][3] = true;
        matrix[1][4] = true;
        var valueMatrix = new int[numberOfNodes];
        valueMatrix[0] = 0;
        valueMatrix[1] = 1;
        valueMatrix[2] = 2;
        valueMatrix[3] = 3;
        valueMatrix[4] = 4;
        Graph graph = new Graph(matrix, valueMatrix);
        System.out.println("DFS");
        System.out.println(graph.dfs(0));
        System.out.println(graph.dfs(1));
        System.out.println(graph.dfs(2));
        System.out.println(graph.dfs(3));
        System.out.println(graph.dfs(4));
        System.out.println(graph.dfs(5));
        System.out.println("BFS");
        System.out.println(graph.bfs(0));
        System.out.println(graph.bfs(1));
        System.out.println(graph.bfs(2));
        System.out.println(graph.bfs(3));
        System.out.println(graph.bfs(4));
        System.out.println(graph.bfs(5));
    }
}
