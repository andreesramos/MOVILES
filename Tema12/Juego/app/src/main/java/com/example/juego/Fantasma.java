package com.example.juego;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class Fantasma {
    private float x, y;
    private float tamaño;
    private Paint pintura;
    private int direccion;
    private Random random;

    public Fantasma(float startX, float startY, float tamaño) {
        this.x = startX;
        this.y = startY;
        this.tamaño = tamaño;

        pintura = new Paint();
        pintura.setColor(Color.RED);
        pintura.setStyle(Paint.Style.FILL);

        random = new Random();
        direccion = random.nextInt(4);
    }

    public void mover(int[][] laberinto, float cellWidth, float cellHeight) {
        float dx = 0, dy = 0;
        switch (direccion) {
            case 0: dy = -cellHeight / 4; break;
            case 1: dy = cellHeight / 4; break;
            case 2: dx = -cellWidth / 4; break;
            case 3: dx = cellWidth / 4; break;
        }

        int nuevaFila = (int) ((y + dy) / cellHeight);
        int nuevaColumna = (int) ((x + dx) / cellWidth);

        if (laberinto[nuevaFila][nuevaColumna] != 1) {
            x += dx;
            y += dy;
        } else {
            direccion = random.nextInt(4);
        }
    }

    public void dibujar(Canvas canvas) {
        canvas.drawCircle(x, y, tamaño, pintura);
    }
}
