package com.example.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ghost {
    private float x, y;
    private int size;

    public Ghost(float x, float y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void update(Maze maze) {
        float newX = this.x + (float) ((Math.random() - 0.5) * Maze.CELL_SIZE);
        float newY = this.y + (float) ((Math.random() - 0.5) * Maze.CELL_SIZE);

        if (!maze.isWall(newX, newY, size)) {
            this.x = newX;
            this.y = newY;
        }
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, size, paint);
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public int getSize() { return size; }
}
