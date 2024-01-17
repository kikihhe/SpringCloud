package com.xiaohe.goods.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TravelingSalesmanProblem {
    private int[][] distanceMatrix;
    private int numCities;
    private int[] shortestPath;
    private int shortestPathLength;
    private boolean[] visited;

    public TravelingSalesmanProblem(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.numCities = distanceMatrix.length;
        this.shortestPath = new int[numCities];
        Arrays.fill(shortestPath, -1);
        this.shortestPath[0] = 0;
        this.shortestPathLength = Integer.MAX_VALUE;
        this.visited = new boolean[numCities];
    }

    public void branchAndBound(int currentCity, int visitedCities, int currentPathLength) {
        if (visitedCities == numCities) {
            int returnToStartCityDistance = distanceMatrix[currentCity][0];
            currentPathLength += returnToStartCityDistance;
            if (currentPathLength < shortestPathLength) {
                shortestPathLength = currentPathLength;
                shortestPath = Arrays.copyOf(shortestPath, numCities);
            }
        } else {
            for (int nextCity = 0; nextCity < numCities; nextCity++) {
                if (!visited[nextCity]) {
                    int distance = distanceMatrix[currentCity][nextCity];
                    if (currentPathLength + distance < shortestPathLength) {
                        visited[nextCity] = true;
                        branchAndBound(nextCity, visitedCities + 1, currentPathLength + distance);
                        visited[nextCity] = false;
                    }
                }
            }
        }
    }

    public void solveTSP() {
        branchAndBound(0, 1, 0);
    }

    public void printShortestPath() {
        System.out.println("最短TSP路径：");
        for (int city : shortestPath) {
            System.out.print(city + " ");
        }
        System.out.println("0");  // 返回起始城市
        System.out.println("最短路径长度：" + shortestPathLength);
    }

    public static void main(String[] args) {
        int[][] distanceMatrix = {
            {0, 29, 20, 21},
            {29, 0, 15, 17},
            {20, 15, 0, 28},
            {21, 17, 28, 0}
        };

        TravelingSalesmanProblem tsp = new TravelingSalesmanProblem(distanceMatrix);
        tsp.solveTSP();
        tsp.printShortestPath();
    }
}
