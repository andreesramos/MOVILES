package com.example.pacman2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

    private Pacman pacman;
    private Ghost[] ghosts;
    private GameMap gameMap;
    private GameLoopThread gameLoopThread;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initGame();
    }

    private void initGame() {
        gameMap = new GameMap(this);

        ghosts = new Ghost[]{
                new Ghost(1, 16, Color.RED, gameMap),
                new Ghost(1, 2, Color.MAGENTA, gameMap),
                new Ghost(20, 17, Color.CYAN, gameMap),
                new Ghost(20, 1, Color.GREEN, gameMap)
        };

        pacman = new Pacman(12, 9, gameMap, ghosts);

        // Iniciar el hilo del juego
        gameLoopThread = new GameLoopThread(this);
        gameLoopThread.start();

        setOnTouchListener(new SwipeListener(getContext()) {
            @Override
            public void onSwipe(SwipeListener.Direction direction) {
                pacman.setDirection(direction);
            }
        });
    }

    public void update() {
        pacman.update();
        pacman.move();
        for (Ghost ghost : ghosts) {
            ghost.move();
        }
        postInvalidate(); // Refrescar pantalla
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        gameMap.draw(canvas);
        pacman.draw(canvas);
        for (Ghost ghost : ghosts) {
            ghost.draw(canvas);
        }
    }
}
