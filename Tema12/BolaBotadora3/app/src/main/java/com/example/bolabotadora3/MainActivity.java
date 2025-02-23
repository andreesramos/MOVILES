package com.example.bolabotadora3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean continuar = true;
    float velocidadX = 0.5f;
    float velocidadY = 0.5f;
    float aceleracion = 0.2f;
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    DinamicaView dinamica;
    float s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica = new DinamicaView(this);
        setContentView(dinamica);
        s = getResources().getDisplayMetrics().density;
        hilo = new Thread(dinamica);
        hilo.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        if (hilo == null || !hilo.isAlive()) {
            hilo = new Thread(dinamica);
            hilo.start();
        }
    }

    class DinamicaView extends View implements Runnable {
        int x, y, xmax, ymax;
        Paint paintFondo, paintParticula, paint;

        public DinamicaView(Context context) {
            super(context);
            paintFondo = new Paint();
            paintParticula = new Paint();
            paint = new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
        }

        @Override
        public void run() {
            while (continuar) {
                tiempo += dt;
                x += (int) (velocidadX * dt);
                y += (int) (velocidadY * dt);

                if (x > xmax) {
                    x = xmax;
                    velocidadX = -Math.abs(velocidadX) - aceleracion;
                }
                if (x < 0) {
                    x = 0;
                    velocidadX = Math.abs(velocidadX) + aceleracion;
                }
                if (y > ymax) {
                    y = ymax;
                    velocidadY = -Math.abs(velocidadY) - aceleracion;
                }
                if (y < 0) {
                    y = 0;
                    velocidadY = Math.abs(velocidadY) + aceleracion;
                }

                postInvalidate();
                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            x = w / 2;
            y = h / 2;
            xmax = w;
            ymax = h;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            paint.setTextSize(20 * s);
            canvas.drawCircle(x, y, 30 * s, paintParticula);
            canvas.drawText("x=" + x, 10 * s, 25 * s, paint);
            canvas.drawText("y=" + y, 10 * s, 50 * s, paint);
            canvas.drawText("tiempo=" + tiempo, 10 * s, 75 * s, paint);
        }
    }
}
