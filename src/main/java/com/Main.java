package com;

import com.graph.Graph;

public class Main {

    public static void main(String[] args) {
        graph();
    }

    private static void graph() {
        int numberOfNodes = 5;
        boolean[][] matrix = new boolean[numberOfNodes][numberOfNodes];
        matrix[0][1] = true;
        matrix[0][2] = true;
        matrix[1][3] = true;
        matrix[1][4] = true;
        printGraphMatrix(matrix);
        Graph graph = new Graph(matrix);
        System.out.println("DFS");
        System.out.println(graph.dfsRecursive(0));
        System.out.println(graph.dfsRecursive(1));
        System.out.println(graph.dfsRecursive(2));
        System.out.println(graph.dfsRecursive(3));
        System.out.println(graph.dfsRecursive(4));
        System.out.println(graph.dfsRecursive(5));
        System.out.println("BFS");
        System.out.println(graph.bfs(0));
        System.out.println(graph.bfs(1));
        System.out.println(graph.bfs(2));
        System.out.println(graph.bfs(3));
        System.out.println(graph.bfs(4));
        System.out.println(graph.bfs(5));
    }

    private static void printGraphMatrix(boolean[][] graphMatrix) {
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix.length; j++) {
                if (graphMatrix[i][j]) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }
}
