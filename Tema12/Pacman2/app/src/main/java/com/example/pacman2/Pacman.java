package com.example.pacman2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class Pacman {
    private final Handler handler = new Handler();
    private float posX, posY; // Posición en píxeles
    private int row, col;
    private final int blockSize;
    private SwipeListener.Direction direction;
    private final GameMap gameMap;
    private boolean isPoweredUp = false;
    private long powerUpTime;
    private List<Ghost> ghosts;
    private String powerUpMessage = "";
    private final float speed = 6f; // Velocidad en píxeles
    private int lives = 2; // Pac-Man empieza con 2 vidas
    private Context context;
    private int score = 0; // Puntuación
    private long lastHitTime = 0;
    private boolean isGameOver = false;
    private boolean isVictory = false;
    private int fantasmasComidos = 0;

    public Pacman(int row, int col, GameMap gameMap, List<Ghost> ghosts, Context context) {
        this.row = row;
        this.col = col;
        this.posX = col * gameMap.getBlockSize();
        this.posY = row * gameMap.getBlockSize();
        this.gameMap = gameMap;
        this.blockSize = gameMap.getBlockSize();
        this.direction = SwipeListener.Direction.LEFT;
        this.ghosts = ghosts;
        this.context=context;
    }

    public void setDirection(SwipeListener.Direction direction) {
        this.direction = direction;
    }

    public void move() {
        float nextX = posX, nextY = posY;
        float radius = blockSize / 2; // Radio del Pac-Man

        switch (direction) {
            case LEFT:  nextX -= speed; break;
            case RIGHT: nextX += speed; break;
            case UP:    nextY -= speed; break;
            case DOWN:  nextY += speed; break;
        }

        // Comprobamos si los bordes chocan con una pared
        boolean canMove = !(
                gameMap.isWall((nextY - radius) / blockSize, nextX / blockSize) || // Arriba
                        gameMap.isWall((nextY + radius) / blockSize, nextX / blockSize) || // Abajo
                        gameMap.isWall(nextY / blockSize, (nextX - radius) / blockSize) || // Izquierda
                        gameMap.isWall(nextY / blockSize, (nextX + radius) / blockSize)    // Derecha
        );

        if (canMove) {
            posX = nextX;
            posY = nextY;
        } else {
            alignToCenter();
        }

        // Detectar si Pac-Man recoge un Power-Up (número 3 en el mapa)
        int currentRow = Math.round(posY / blockSize);
        int currentCol = Math.round(posX / blockSize);

        if (gameMap.getBlock(currentRow, currentCol) == 3) {
            gameMap.setBlock(currentRow, currentCol, 2); // Eliminar Power-Up del mapa
            activatePowerMode();
            SoundManager.playSound("power");

            // Restaurar el Power-Up tras 20 segundos
            handler.postDelayed(() -> {
                gameMap.setBlock(currentRow, currentCol, 3);
            }, 20000);
        }

        // Verificar colisión con los fantasmas
        checkCollisionWithGhosts();
    }

    private void checkCollisionWithGhosts() {
        long currentTime = System.currentTimeMillis();
        Iterator<Ghost> iterator = ghosts.iterator();

        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();

            if (verrifyCollision(ghost)) {
                if (isPoweredUp && ghost.isActive()) {
                    ghost.resetPosition();
                    score += 20;
                    fantasmasComidos++;
                    SoundManager.playSound("comer_fantasma");

                    if(fantasmasComidos >= 4 && !isVictory){
                        isVictory=true;
                        SoundManager.playSound("victoria");
                        showVictoryDialog();
                    }

                    continue;
                }

                if (!isPoweredUp && currentTime - lastHitTime > 2000) {
                    lastHitTime = currentTime;
                    lives--;

                    SoundManager.playSound("muerte");

                    if (lives > 0) {
                        resetPosition();
                    } else if (!isGameOver) {
                        isGameOver = true;
                        lives = 0;
                        score = 0;
                        showGameOverDialog();
                    }

                    break; // Evita perder múltiples vidas en el mismo frame
                }
            }
        }
    }

    private void resetPosition() {
        posX = col * gameMap.getBlockSize();
        posY = row * gameMap.getBlockSize();
        direction = SwipeListener.Direction.LEFT; // Restablecer dirección
    }

    // Función para verificar colisión con un fantasma
    private boolean verrifyCollision(Ghost ghost) {
        float dx = posX - ghost.getPosX();
        float dy = posY - ghost.getPosY();

        float distance = (float) Math.sqrt(dx * dx + dy * dy); // Distancia entre los centros
        float pacmanRadius = blockSize / 2;
        float ghostRadius = ghost.getBlockSize() / 2;

        return distance <= (pacmanRadius + ghostRadius); // Colisión si se tocan los bordes
    }


    private void alignToCenter() {
        posX = Math.round(posX / blockSize) * blockSize;
        posY = Math.round(posY / blockSize) * blockSize;
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

        int x = gameMap.getOffsetX() + (int) posX + blockSize / 2;
        int y = gameMap.getOffsetY() + (int) posY + blockSize / 2;

        canvas.drawCircle(x, y, blockSize / 2, paint);

        // Dibujar las vidas
        Paint vidas = new Paint();
        vidas.setColor(Color.WHITE);
        vidas.setTextSize(blockSize);
        vidas.setTextAlign(Paint.Align.LEFT);

        // Posicionar el texto debajo del mapa
        float textX = gameMap.getOffsetX() + blockSize;
        float textY = gameMap.getOffsetY() + gameMap.getMapHeight() * blockSize + blockSize;

        canvas.drawText("Vidas: " + lives, textX, textY, vidas);
        canvas.drawText("Puntos: " + score, textX, textY+100, vidas);

        // Mostrar mensaje si está activado
        if (!powerUpMessage.isEmpty()) {
            Paint texto = new Paint();
            texto.setColor(Color.WHITE);
            texto.setTextSize(blockSize);
            texto.setTextAlign(Paint.Align.CENTER);

            float textoX = canvas.getWidth() / 2;
            float textoY = canvas.getHeight() * 0.15f;
            canvas.drawText(powerUpMessage, textoX, textoY, texto);
        }
    }

    public void update() {
        if (isPoweredUp && System.currentTimeMillis() - powerUpTime > 12000) {
            isPoweredUp = false;
            powerUpMessage = "";
            for (Ghost ghost : ghosts) {
                ghost.setVulnerable(false); // Volver a la normalidad
            }
        }
    }

    private void showVictoryDialog() {
        handler.post(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_victory, null);
            builder.setView(dialogView);

            TextView textView = dialogView.findViewById(R.id.victoryText);
            textView.append(" Has conseguido " + score + " puntos");

            AlertDialog dialog = builder.create();
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
            dialog.show();

            // Esperar 5 segundos y reiniciar el juego
            new Handler().postDelayed(() -> {
                dialog.dismiss();
                restartGame();
            }, 5000);
        });
    }

    private void showGameOverDialog() {
        // Detener el juego antes de mostrar el diálogo
        handler.post(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_game_over, null);
            builder.setView(dialogView);

            TextView textView = dialogView.findViewById(R.id.gameOverText);
            textView.setText("¡Game Over!\nPac-Man ha perdido todas sus vidas.");

            // Mostrar el diálogo con animación
            AlertDialog dialog = builder.create();
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
            dialog.show();

            // Esperar 5 segundos y reiniciar el juego
            new Handler().postDelayed(() -> {
                dialog.dismiss(); // Cierra el diálogo
                restartGame();    // Reinicia el juego
            }, 5000);
        });
    }

    private void restartGame() {
        Intent intent = ((Activity) context).getIntent();
        ((Activity) context).finish();
        context.startActivity(intent);
    }

    public int getFantasmasComidos(){
        return fantasmasComidos;
    }

    public void setFantasmasComidos(int num){
        fantasmasComidos=num;
    }
}