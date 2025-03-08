package com.example.pacman2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Ghost {
    private int row, col, color;
    private final int blockSize;
    private final GameMap gameMap;
    private final Random random;
    private boolean isVulnerable = false;
    private int lastRow, lastCol; // Guardar última posición
    private int moveCounter = 0; // Contador para ralentizar movimiento
    private final int MOVE_DELAY = 7;

    public Ghost(int row, int col, int color, GameMap gameMap) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.gameMap = gameMap;
        this.blockSize = gameMap.getBlockSize();
        this.random = new Random();
        this.lastRow = row;
        this.lastCol = col;
    }

    public void move() {
        moveCounter++;

        if (moveCounter < MOVE_DELAY) {
            return; // No moverse hasta que se alcancen los frames necesarios
        }
        moveCounter = 0; // Reiniciar contador

        int[][] possibleMoves = {
                {row - 1, col}, // Arriba
                {row + 1, col}, // Abajo
                {row, col - 1}, // Izquierda
                {row, col + 1}  // Derecha
        };

        List<int[]> validMoves = new ArrayList<>();

        // Filtrar solo movimientos válidos
        for (int[] move : possibleMoves) {
            if (gameMap.isWalkableForGhost(move[0], move[1]) && (move[0] != lastRow || move[1] != lastCol)) {
                validMoves.add(move);
            }
        }

        // Si hay movimientos válidos, elegir uno al azar
        if (!validMoves.isEmpty()) {
            int[] nextMove = validMoves.get(random.nextInt(validMoves.size()));
            lastRow = row;
            lastCol = col;
            row = nextMove[0];
            col = nextMove[1];
        }
    }

    public void setVulnerable(boolean vulnerable) {
        isVulnerable = vulnerable;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(isVulnerable ? Color.BLUE : color); // Azul si es vulnerable

        int x = gameMap.getOffsetX() + (col * blockSize) + blockSize / 2;
        int y = gameMap.getOffsetY() + (row * blockSize) + blockSize / 2;

        canvas.drawCircle(x, y, blockSize / 2, paint);
    }
}
