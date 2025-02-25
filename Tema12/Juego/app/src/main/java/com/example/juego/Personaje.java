package com.example.juego;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Personaje {
    private float x, y; // Posición en píxeles
    private float tamaño;
    private Paint pintura;

    public Personaje(float startX, float startY, float tamaño) {
        this.x = startX;
        this.y = startY;
        this.tamaño = tamaño;

        pintura = new Paint();
        pintura.setColor(Color.YELLOW);
        pintura.setStyle(Paint.Style.FILL);
    }

    public void mover(float dx, float dy, int[][] laberinto, float cellWidth, float cellHeight) {
        int nuevaFila = (int) ((y + dy) / cellHeight);
        int nuevaColumna = (int) ((x + dx) / cellWidth);

        if (laberinto[nuevaFila][nuevaColumna] != 1) {
            x += dx;
            y += dy;
        }
    }

    public void dibujar(Canvas canvas) {
        canvas.drawCircle(x, y, tamaño, pintura);
    }
}
