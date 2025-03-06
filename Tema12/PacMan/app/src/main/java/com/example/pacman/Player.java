package com.example.pacman;

import static com.example.pacman.Maze.CELL_SIZE;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Player {
    private float x, y;
    private int size;

    public Player(float x, float y, int size) {
        int gridX = Math.round(x / Maze.CELL_SIZE);
        int gridY = Math.round(y / Maze.CELL_SIZE);
        this.x = gridX * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        this.y = gridY * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        this.size = size / 2;
    }

    public void move(float newX, float newY, Maze maze) {
        int gridX = (int) (newX / CELL_SIZE) * CELL_SIZE + CELL_SIZE / 2;
        int gridY = (int) (newY / CELL_SIZE) * CELL_SIZE + CELL_SIZE / 2;

        if (!maze.isWall(gridX, gridY, size)) {
            this.x = gridX;
            this.y = gridY;
        }
    }

    public void moveBySwipe(float deltaX, float deltaY, Maze maze) {
        int gridX = Math.round(this.x / Maze.CELL_SIZE);
        int gridY = Math.round(this.y / Maze.CELL_SIZE);

        int newGridX = gridX;
        int newGridY = gridY;

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            newGridX += (deltaX > 0 ? 1 : -1);
        } else {
            newGridY += (deltaY > 0 ? 1 : -1);
        }

        float newX = newGridX * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;
        float newY = newGridY * Maze.CELL_SIZE + Maze.CELL_SIZE / 2;

        if (!maze.isWall(newX, newY, size)) {
            this.x = newX;
            this.y = newY;
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
