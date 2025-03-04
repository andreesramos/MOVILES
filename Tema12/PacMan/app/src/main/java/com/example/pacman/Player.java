package com.example.pacman;

import static com.example.pacman.Maze.CELL_SIZE;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Player {
    private float x, y;
    private int size;

    public Player(float x, float y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void move(float newX, float newY, Maze maze) {
        int gridX = (int) (newX / CELL_SIZE) * CELL_SIZE + CELL_SIZE / 2;
        int gridY = (int) (newY / CELL_SIZE) * CELL_SIZE + CELL_SIZE / 2;

        if (!maze.isWall(gridX, gridY, size)) {
            this.x = gridX;
            this.y = gridY;
        }
    }


    public void update() {}

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, size, paint);
    }

    public boolean checkCollision(Ghost ghost) {
        return Math.hypot(x - ghost.getX(), y - ghost.getY()) < size + ghost.getSize();
    }
}
