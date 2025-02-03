package com.example.diagonalesdelcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(new MiVista(this));

    }

    private class MiVista extends View{
        private final int ancho;
        private final int alto;

        public MiVista(Context context){
            super(context);

            ancho = getResources().getDisplayMetrics().widthPixels;
            alto = getResources().getDisplayMetrics().heightPixels;
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel = new Paint();
            pincel.setColor(Color.BLACK);
            pincel.setTextSize(80);

            canvas.drawColor(Color.YELLOW);
            pincel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("width: " + ancho, 700, 220, pincel);
            canvas.drawText("height: " + alto, 700, 340, pincel);
            canvas.drawText("right: " + ancho, 700, 460, pincel);
            canvas.drawText("bottom: " + alto, 700, 580, pincel);

            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(10);

            canvas.drawLine(0, 0, ancho, alto, pincel);
            canvas.drawLine(ancho, 0, 0, alto, pincel);

        }
    }
}