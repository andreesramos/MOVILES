package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
        private final float escala;
        private final int ancho;
        private final int alto;

        public MiVista(Context context){
            super(context);

            ancho = getResources().getDisplayMetrics().widthPixels;
            alto = getResources().getDisplayMetrics().heightPixels;

            escala = (float) alto / 800;
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();
            pincel.setTextSize(80);
            pincel.setColor(Color.BLACK);

            canvas.drawText("width: " + ancho, 20 * escala, 80 * escala, pincel);
            canvas.drawText("height: " + alto, 140 * escala, 80 * escala, pincel);
            canvas.drawText("escala: " + escala, 20 * escala, 200 * escala, pincel);

            pincel.setColor(Color.GREEN);
            pincel.setStrokeWidth(10);
            canvas.drawLine(0, 80 * escala, ancho, 80 * escala, pincel);

            pincel.setColor(Color.RED);
            canvas.drawLine(20 * escala, 0, 20 * escala, alto, pincel);
        }
    }
}