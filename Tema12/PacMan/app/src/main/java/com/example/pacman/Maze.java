package com.example.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Maze {
    public static final int CELL_SIZE = 100; // Ajusta este valor según el tamaño de la pantalla
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

    public boolean isWall(float x, float y, int size) {
        int col = (int) (x / 50);
        int row = (int) (y / 50);
        return maze[row][col] == 1;
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
