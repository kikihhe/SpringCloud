package com.xiaohe.goods.controller;

import java.util.ArrayList;
import java.util.List;

class MapColoring {
    private int[][] map;  // 地图的邻接矩阵
    private int numRegions;  // 区域的数量
    private int[] colors;  // 每个区域分配的颜色

    public MapColoring(int[][] map) {
        this.map = map;
        this.numRegions = map.length;
        this.colors = new int[numRegions];
    }

    public void colorMap() {
        for (int region = 0; region < numRegions; region++) {
            // 找到相邻区域已经使用的颜色
            List<Integer> usedColors = new ArrayList<>();
            for (int neighbor = 0; neighbor < numRegions; neighbor++) {
                if (map[region][neighbor] == 1 && colors[neighbor] != -1) {
                    usedColors.add(colors[neighbor]);
                }
            }

            // 选择一个尚未使用的颜色
            int color = 0;
            while (usedColors.contains(color)) {
                color++;
            }
            colors[region] = color;
        }
    }

    public void printColors() {
        for (int region = 0; region < numRegions; region++) {
            System.out.println("Region " + region + " color " + colors[region]);
        }
    }

    public static void main(String[] args) {
        int[][] map = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        MapColoring mapColoring = new MapColoring(map);
        mapColoring.colorMap();
        mapColoring.printColors();
    }
}