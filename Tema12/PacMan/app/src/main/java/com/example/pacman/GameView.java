package com.example.pacman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

public class GameView extends View {
    private Player pacman;
    private Ghost ghost;
    private Maze maze;
    private int score = 0;
    private float touchX, touchY;
    private final Handler handler = new Handler();
    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            update(); // Actualiza el estado del juego
            invalidate(); // Vuelve a dibujar la pantalla
            handler.postDelayed(this, 30); // Llama a update() cada 500ms
        }
    };

    public GameView(Context context) {
        super(context);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        maze = new Maze(screenWidth, screenHeight);
        pacman = new Player(Maze.CELL_SIZE, Maze.CELL_SIZE, Maze.CELL_SIZE);
        ghost = new Ghost(Maze.CELL_SIZE * 5, Maze.CELL_SIZE * 5, Maze.CELL_SIZE);

        handler.post(updateRunnable); // Inicia la actualización automática
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas == null) return;
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        maze.draw(canvas, paint);
        paint.setColor(Color.YELLOW);
        pacman.draw(canvas, paint);
        paint.setColor(Color.RED);
        ghost.draw(canvas, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 50, Maze.CELL_SIZE * maze.getHeight() - 20, paint);
    }

    public void update() {
        pacman.update();
        ghost.update(maze);
        if (pacman.checkCollision(ghost)) {
            score -= 10;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                return true;

            case MotionEvent.ACTION_UP:
                float deltaX = event.getX() - touchX;
                float deltaY = event.getY() - touchY;
                pacman.moveBySwipe(deltaX, deltaY, maze);
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(updateRunnable); // Detiene el bucle de actualización
    }
}
