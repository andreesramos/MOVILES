package com.example.pacman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.pacman.MainThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Player pacman;
    private Ghost ghost;
    private Maze maze;
    private int score = 0;
    private float touchX, touchY;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        pacman = new Player(100, 100, 50);
        ghost = new Ghost(300, 300, 50);
        maze = new Maze();
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchX = event.getX();
            touchY = event.getY();
            pacman.move(touchX, touchY, maze);
        }
        return true;
    }

    public void update() {
        pacman.update();
        ghost.update(maze);
        if (pacman.checkCollision(ghost)) {
            score -= 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            Paint paint = new Paint();
            maze.draw(canvas, paint);
            paint.setColor(Color.YELLOW);
            pacman.draw(canvas, paint);
            paint.setColor(Color.RED);
            ghost.draw(canvas, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText("Score: " + score, 50, Maze.CELL_SIZE * maze.getHeight() + 50, paint);
        }
    }

}


