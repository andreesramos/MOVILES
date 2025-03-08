package com.example.pacman2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.os.Handler;

import java.util.*;

public class Ghost {
    private enum Direction { UP, DOWN, LEFT, RIGHT, NONE }
    private Direction direction = Direction.NONE;
    private GameMap gameMap;
    private Pacman pacman;
    private int row, col, color;
    private float posX, posY, speed = 4f;
    private float startX, startY;
    private int blockSize;
    public boolean isVulnerable = false;
    private boolean isActive = true;
    private final Handler handler = new Handler();

    public Ghost(GameMap map, int startRow, int startCol, int color) {
        this.gameMap = map;
        this.blockSize = map.getBlockSize();
        this.color=color;

        if (!gameMap.isWalkableForGhost(startRow, startCol)) {
            return;
        }

        this.row = startRow;
        this.col = startCol;
        this.posX = startCol * blockSize;
        this.posY = startRow * blockSize;
        this.startX=posX;
        this.startY=posY;

        direction = Direction.RIGHT; // Empezamos hacia la derecha
    }

    public void move() {
        float prevX = posX;
        float prevY = posY;

        int currentRow = Math.round(posY / blockSize);
        int currentCol = Math.round(posX / blockSize);

        // Centrar posición del fantasma en la celda actual
        float centerX = currentCol * blockSize + blockSize / 2;
        float centerY = currentRow * blockSize + blockSize / 2;

        if (Math.abs(posX - centerX) < speed) {
            posX = centerX;
        }
        if (Math.abs(posY - centerY) < speed) {
            posY = centerY;
        }

        // Verificamos dirección y si podemos movernos a la siguiente celda
        switch (direction) {
            case LEFT:
                if (gameMap.isWalkableForGhost(currentRow, currentCol - 1)) {
                    posX -= speed;
                } else {
                    changeDirection();
                }
                break;
            case RIGHT:
                if (gameMap.isWalkableForGhost(currentRow, currentCol + 1)) {
                    posX += speed;
                } else {
                    changeDirection();
                }
                break;
            case UP:
                if (gameMap.isWalkableForGhost(currentRow - 1, currentCol)) {
                    posY -= speed;
                } else {
                    changeDirection();
                }
                break;
            case DOWN:
                if (gameMap.isWalkableForGhost(currentRow + 1, currentCol)) {
                    posY += speed;
                } else {
                    changeDirection();
                }
                break;
        }

        // Redondeamos la nueva posición para evitar errores flotantes
        row = Math.round(posY / blockSize);
        col = Math.round(posX / blockSize);

        // Si la nueva posición no es válida, volvemos a la anterior
        if (!gameMap.isWalkableForGhost(row, col)) {
            posX = prevX;
            posY = prevY;
        }
    }


    private void changeDirection() {
        List<Direction> possibleDirections = new ArrayList<>();

        // Revisamos las 4 direcciones posibles y añadimos solo las transitables
        if (gameMap.isWalkableForGhost(row - 1, col)) possibleDirections.add(Direction.UP);
        if (gameMap.isWalkableForGhost(row + 1, col)) possibleDirections.add(Direction.DOWN);
        if (gameMap.isWalkableForGhost(row, col - 1)) possibleDirections.add(Direction.LEFT);
        if (gameMap.isWalkableForGhost(row, col + 1)) possibleDirections.add(Direction.RIGHT);

        // Evitamos que regrese por donde vino (evita bucles)
        if (direction == Direction.UP) possibleDirections.remove(Direction.DOWN);
        if (direction == Direction.DOWN) possibleDirections.remove(Direction.UP);
        if (direction == Direction.LEFT) possibleDirections.remove(Direction.RIGHT);
        if (direction == Direction.RIGHT) possibleDirections.remove(Direction.LEFT);

        // Si hay opciones, elegimos una aleatoria
        if (!possibleDirections.isEmpty()) {
            direction = possibleDirections.get((int) (Math.random() * possibleDirections.size()));
        }
    }

    // Cuando Pac-Man los come desaparecen
    public void resetPosition() {
        isActive = false;

        handler.postDelayed(() -> {
            posX = startX;
            posY = startY;
            pacman.setFantasmasComidos(pacman.getFantasmasComidos()-1);
            isActive = true; // El fantasma reaparece
        }, 30000);
    }

    public void draw(Canvas canvas) {
        if (!isActive) return; // Si está inactivo, no se dibuja

        Paint paint = new Paint();
        paint.setColor(isVulnerable ? Color.WHITE : color); // Blaco si es vulnerable

        int x = gameMap.getOffsetX() + (int) posX + blockSize / 2;
        int y = gameMap.getOffsetY() + (int) posY + blockSize / 2;

        canvas.drawCircle(x, y, blockSize / 2, paint);
    }

    public void setVulnerable(boolean vulnerable) {
        isVulnerable = vulnerable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getBlockSize(){
        return blockSize;
    }
}