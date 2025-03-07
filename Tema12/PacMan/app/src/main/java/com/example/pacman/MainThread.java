package com.example.pacman;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static final int FPS = 30;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.running = false; // Inicializa en false
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void requestRedraw() {
        gameView.postInvalidate();
    }

    @Override
    public void run() {
        long targetTime = 1000 / FPS;
        System.out.println("MainThread: Hilo iniciado...");
        while (running) {
            long startTime = System.nanoTime();
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    System.out.println("MainThread: Canvas es NULL, no se puede dibujar.");
                    continue; // Si es null, saltamos este ciclo
                }
                synchronized (surfaceHolder) {
                    if (canvas != null) { // Asegura que el canvas no sea null
                        System.out.println("MainThread: Llamando a update() y draw()...");
                        gameView.update();
                        gameView.draw(canvas);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            requestRedraw(); // Forzar redibujado
            long elapsedTime = (System.nanoTime() - startTime) / 1000000;
            long waitTime = targetTime - elapsedTime;
            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

