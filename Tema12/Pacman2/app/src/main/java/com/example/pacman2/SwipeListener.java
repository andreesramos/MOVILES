package com.example.pacman2;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class SwipeListener implements View.OnTouchListener {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private final GestureDetector gestureDetector;

    public SwipeListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 10; // Distancia mínima del deslizamiento
        private static final int SWIPE_VELOCITY_THRESHOLD = 10; // Velocidad mínima

        @Override
        public boolean onDown(MotionEvent e) {
            return true; // Permitir detectar el gesto
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                // Movimiento horizontal
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipe(Direction.RIGHT);
                    } else {
                        onSwipe(Direction.LEFT);
                    }
                    return true;
                }
            } else {
                // Movimiento vertical
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipe(Direction.DOWN);
                    } else {
                        onSwipe(Direction.UP);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public abstract void onSwipe(Direction direction);
}