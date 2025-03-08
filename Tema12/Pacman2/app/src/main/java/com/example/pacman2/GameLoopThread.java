package com.example.pacman2;

public class GameLoopThread extends Thread {
    private final GameView gameView;
    private boolean running = true;
    private static final int FPS = 28;

    public GameLoopThread(GameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            gameView.update(); // Llamamos a update(), que usa invalidate()
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
