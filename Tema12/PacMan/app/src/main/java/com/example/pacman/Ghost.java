package com.example.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ghost {
    private float x, y;
    private int size;
    private int moveCooldown = 0; // Contador para reducir velocidad
    private static final int MOVE_DELAY = 10; // Número de frames entre cada movimiento

    public Ghost(float x, float y, int size) {
        int gridX = Math.round(x / Maze.CELL_SIZE);
        int gridY = Math.round(y / Maze.CELL_SIZE);
        this.x = gridX * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        this.y = gridY * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        this.size = size / 2;
    }

    public void update(Maze maze) {
        if (moveCooldown > 0) {
            moveCooldown--;
            return;
        }

        moveCooldown = MOVE_DELAY;

        int gridX = Math.round(this.x / Maze.CELL_SIZE);
        int gridY = Math.round(this.y / Maze.CELL_SIZE);

        int newGridX = gridX;
        int newGridY = gridY;

        boolean moved = false;

        while (!moved) {
            if (Math.random() < 0.5) {
                int tempGridX = gridX + (Math.random() < 0.5 ? -1 : 1);
                float tempX = tempGridX * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;

                if (!maze.isWall(tempX, this.y, size)) {
                    newGridX = tempGridX;
                    moved = true;
                }
            } else {
                int tempGridY = gridY + (Math.random() < 0.5 ? -1 : 1);
                float tempY = tempGridY * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;

                if (!maze.isWall(this.x, tempY, size)) {
                    newGridY = tempGridY;
                    moved = true;
                }
            }
        }

        this.x = newGridX * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        this.y = newGridY * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, size, paint);
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public int getSize() { return size; }
}
