package com.example.juego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Laberinto extends View {

    // Representación del laberinto:
    // 1 -> pared, 0 -> camino libre, 2 -> punto pequeño, 3 -> punto grande
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,0,0,0,0,0,1,0,0,0,0,0,3,1},
            {1,0,1,1,0,1,0,0,0,1,0,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,0,1,0,1,0,1,0,1,1,0,1},
            {1,0,0,0,0,1,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,0,1,1,1,1,1,0,1,0,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,0,1,1,0,1,1,1,1,0,1},
            {1,3,0,0,0,0,0,0,0,0,0,0,0,3,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private Paint pared;
    private Paint camino;
    private Paint puntoPequeno;
    private Paint puntoGrande;

    public Laberinto(Context context) {
        super(context);

        // Configuración del pincel para las paredes
        pared = new Paint();
        pared.setColor(Color.BLUE);
        pared.setStyle(Paint.Style.FILL);
        //pared.setStrokeWidth(10);

        // Configuración del pincel para el camino
        camino = new Paint();
        camino.setColor(Color.BLACK);
        camino.setStyle(Paint.Style.FILL);

        // Configuración del pincel para los puntos pequeños
        puntoPequeno = new Paint();
        puntoPequeno.setColor(Color.WHITE);
        puntoPequeno.setStyle(Paint.Style.FILL);

        // Configuración del pincel para los puntos grandes
        puntoGrande = new Paint();
        puntoGrande.setColor(Color.WHITE);
        puntoGrande.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        int numRows = maze.length;
        int numCols = maze[0].length;

        float cellWidth = getWidth() / (float) numCols;
        float cellHeight = getHeight() / (float) numRows;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                float left = col * cellWidth;
                float top = row * cellHeight;
                float right = left + cellWidth;
                float bottom = top + cellHeight;

                if (maze[row][col] == 1) {
                    canvas.drawRect(left, top, right, bottom, pared);
                } else {
                    canvas.drawRect(left, top, right, bottom, camino);

                    if (maze[row][col] == 2) {
                        canvas.drawCircle(left + cellWidth / 2, top + cellHeight / 2, cellWidth / 10, puntoPequeno);
                    } else if (maze[row][col] == 3) {
                        canvas.drawCircle(left + cellWidth / 2, top + cellHeight / 2, cellWidth / 5, puntoGrande);
                    }
                }
            }
        }
    }
}
