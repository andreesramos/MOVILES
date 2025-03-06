package com.example.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Maze {
    public static int CELL_SIZE;
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,2,2,2,2,2,1,2,2,2,2,2,3,1},
            {1,2,1,1,2,1,2,2,2,1,2,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,2,1,2,1,2,1,2,1,1,2,1},
            {1,2,2,2,2,1,2,2,2,1,2,2,2,2,1},
            {1,1,1,1,2,1,1,2,1,1,2,1,1,1,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {1,1,1,2,1,1,1,1,1,2,1,2,1,1,1},
            {1,2,2,2,1,2,2,2,2,2,1,2,2,2,1},
            {1,2,1,2,1,2,1,1,2,1,1,1,1,2,1},
            {1,3,2,2,2,2,2,2,2,2,2,2,2,3,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public Maze(int screenWidth, int screenHeight) {
        int rows = maze.length;
        int cols = maze[0].length;
        CELL_SIZE = Math.min(screenWidth / cols, screenHeight / rows);
    }

    public boolean isWall(float x, float y, int size) {
        int col = (int) ((x - size / 2) / CELL_SIZE);
        int row = (int) ((y - size / 2) / CELL_SIZE);

        // Evitar que acceda a índices fuera de rango
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return true;
        }

        return maze[row][col] == 1; // Devuelve true si es una pared
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == 1) { // Dibuja solo los muros
                    canvas.drawRect(col * CELL_SIZE, row * CELL_SIZE,
                            (col + 1) * CELL_SIZE, (row + 1) * CELL_SIZE, paint);
                }
            }
        }
    }

    public int getHeight() {
        return maze.length; // Retorna el número de filas
    }

    public int getWidth() {
        return maze[0].length; // Retorna el número de columnas
    }

}
