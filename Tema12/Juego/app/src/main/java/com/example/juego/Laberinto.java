package com.example.juego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class Laberinto extends View {

    // Representación del laberinto:
    // 1 -> pared, 0 -> camino libre, 2 -> punto pequeño, 3 -> punto grande
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,2,2,2,2,2,1,2,2,2,2,2,3,1},
            {1,2,1,1,2,1,2,2,2,1,2,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,2,1,2,1,2,1,2,1,1,2,1},
            {1,2,2,2,2,1,2,2,2,1,2,2,2,2,1},
            {1,1,1,1,2,1,1,2,1,1,2,1,1,1,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {1,1,1,2,1,1,1,1,1,2,1,2,1,1,1},
            {1,2,2,2,1,2,2,2,2,2,1,2,2,2,1},
            {1,2,1,2,1,2,1,1,2,1,1,1,1,2,1},
            {1,3,2,2,2,2,2,2,2,2,2,2,2,3,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };


    private Paint pared, camino, puntoPequeno, puntoGrande;
    private Personaje personaje;
    private ArrayList<Fantasma> fantasmas;

    public Laberinto(Context context) {
        super(context);

        pared = new Paint();
        pared.setColor(Color.BLUE);
        camino = new Paint();
        camino.setColor(Color.BLACK);
        puntoPequeno = new Paint();
        puntoPequeno.setColor(Color.WHITE);
        puntoGrande = new Paint();
        puntoGrande.setColor(Color.WHITE);

        // Dimensiones del mapa (asegurar que se calculen correctamente en onDraw)
        int startRow = 1;  // Fila donde comienza Pac-Man
        int startCol = 1;  // Columna donde comienza Pac-Man

        float cellWidth = getWidth() / (float) maze[0].length;
        float cellHeight = getHeight() / (float) maze.length;

        // Ubicación en píxeles basada en la cuadrícula
        personaje = new Personaje(startCol * cellWidth + cellWidth / 2, startRow * cellHeight + cellHeight / 2, cellWidth / 2);

        // Crear fantasmas en distintas posiciones
        fantasmas = new ArrayList<>();
        fantasmas.add(new Fantasma(7 * cellWidth + cellWidth / 2, 7 * cellHeight + cellHeight / 2, cellWidth / 2));
        fantasmas.add(new Fantasma(11 * cellWidth + cellWidth / 2, 7 * cellHeight + cellHeight / 2, cellWidth / 2));
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
                        canvas.drawCircle(left + cellWidth / 2, top + cellHeight / 2, cellWidth / 4, puntoGrande);
                    }
                }
            }
        }

        // Dibujar el personaje después del laberinto
        personaje.dibujar(canvas);

        // Dibujar los fantasmas después del laberinto
        for (Fantasma f : fantasmas) {
            f.mover(maze, cellWidth, cellHeight);
            f.dibujar(canvas);
        }

        // Volver a dibujar
        invalidate();  // Forzar el refresco de la pantalla
    }

}
