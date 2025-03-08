package com.example.pacman2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Pacman {
    private int row, col;
    private final int blockSize;
    private SwipeListener.Direction direction;
    private final GameMap gameMap;
    private boolean isPoweredUp = false;
    private long powerUpTime;
    private Ghost[] ghosts; // Referencia a los fantasmas
    private String powerUpMessage = "";
    private int moveCounter = 0; // Contador para ralentizar el movimiento
    private final int MOVE_DELAY = 5;

    public Pacman(int row, int col, GameMap gameMap, Ghost[] ghosts) {
        this.row = row;
        this.col = col;
        this.gameMap = gameMap;
        this.blockSize = gameMap.getBlockSize();
        this.direction = SwipeListener.Direction.LEFT;
        this.ghosts = ghosts;
    }

    public void setDirection(SwipeListener.Direction direction) {
        this.direction = direction;
    }

    public void move() {
        moveCounter++;
        if (moveCounter < MOVE_DELAY) {
            return; // No moverse hasta alcanzar los frames necesarios
        }
        moveCounter = 0; // Reiniciar contador

        int newRow = row, newCol = col;

        switch (direction) {
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
        }

        // Verificar si la nueva posición es transitable
        if (gameMap.isWalkable(newRow, newCol)) {
            row = newRow;
            col = newCol;

            // Detectar si comió una píldora de poder
            if (gameMap.getBlock(row, col) == 3) {
                gameMap.setBlock(row, col, 1); // Reemplazar con camino normal
                activatePowerMode();
            }
        }
    }

    // Activar modo poderoso
    private void activatePowerMode() {
        isPoweredUp = true;
        powerUpTime = System.currentTimeMillis();
        powerUpMessage = "¡Pac-Man es invencible!";

        for (Ghost ghost : ghosts) {
            ghost.setVulnerable(true);
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        // Convertir fila/columna en coordenadas de píxeles
        int x = gameMap.getOffsetX() + (col * blockSize) + blockSize / 2;
        int y = gameMap.getOffsetY() + (row * blockSize) + blockSize / 2;

        canvas.drawCircle(x, y, blockSize / 2, paint);

        // Mostrar mensaje si está activado
        if (!powerUpMessage.isEmpty()) {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(50);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(powerUpMessage, canvas.getWidth() / 2, 100, textPaint);
        }
    }

    public void update() {
        if (isPoweredUp && System.currentTimeMillis() - powerUpTime > 5000) {
            isPoweredUp = false;
            powerUpMessage = "";
            for (Ghost ghost : ghosts) {
                ghost.setVulnerable(false); // Volver a la normalidad
            }
        }
    }

}
