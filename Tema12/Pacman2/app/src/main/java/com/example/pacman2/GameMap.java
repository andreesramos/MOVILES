package com.example.pacman2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class GameMap {
    private int blockSize;
    private int offsetX, offsetY; // Para centrar el mapa
    private int screenWidth, screenHeight;

    private final int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 1, 0},
            {0, 3, 2, 0, 0, 1, 1, 0, 2, 1, 2, 0, 1, 1, 0, 0, 2, 3, 0},
            {0, 1, 2, 0, 0, 1, 1, 0, 2, 1, 2, 0, 1, 1, 0, 0, 2, 2, 0},
            {0, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 2, 0},
            {0, 1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 2, 0},
            {0, 1, 1, 1, 2, 0, 2, 1, 1, 0, 1, 1, 2, 0, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 2, 0, 2, 1, 1, 0, 1, 1, 2, 0, 2, 0, 0, 0, 0},
            {5, 5, 5, 0, 2, 0, 2, 1, 1, 1, 1, 1, 2, 0, 2, 0, 5, 5, 5},
            {0, 0, 0, 0, 2, 0, 2, 0, 0, 1, 0, 0, 2, 0, 2, 0, 0, 0, 0},
            {5, 5, 0, 0, 2, 2, 2, 0, 0, 1, 0, 0, 2, 2, 2, 0, 0, 5, 5},
            {0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 0, 0, 0},
            {5, 5, 5, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 5, 5, 5},
            {0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 2, 2, 2, 1, 1, 0, 2, 2, 2, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 2, 0, 0, 0, 1, 0, 2, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 3, 1, 0, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 0, 1, 3, 0},
            {0, 0, 1, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 1, 0, 0},
            {0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 2, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    public GameMap(View view) {
        DisplayMetrics metrics = view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        blockSize = Math.min(screenWidth / map[0].length, (screenHeight - 200) / map.length);

        // Centrar el mapa en la pantalla
        offsetX = (screenWidth - (map[0].length * blockSize)) / 2;
        offsetY = (screenHeight - (map.length * blockSize)) / 2;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int left = offsetX + (j * blockSize);
                int top = offsetY + (i * blockSize);
                int right = left + blockSize;
                int bottom = top + blockSize;

                if (map[i][j] == 0) { // Pared
                    paint.setColor(Color.BLUE);
                    canvas.drawRect(left, top, right, bottom, paint);
                } else if (map[i][j] == 1 || map[i][j] == 2) { // Comida
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(left + blockSize / 2, top + blockSize / 2, blockSize / 6, paint);
                }
            }
        }
    }

    public boolean isWalkable(int row, int col) {
        // Verificar si la celda está dentro de los límites del mapa
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return false;
        }
        // Es transitable si no es una pared
        return map[row][col] == 1 || map[row][col] == 2 || map[row][col] == 3;
    }

    public boolean isWalkableForGhost(int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return false;
        }
        return map[row][col] == 2;
    }

    public int getBlock(int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return -1; // Retorna -1 si está fuera de los límites
        }
        return map[row][col];
    }

    public void setBlock(int row, int col, int value) {
        if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
            map[row][col] = value;
        }
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
