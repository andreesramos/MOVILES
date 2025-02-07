package com.example.prueba1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
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

    class MiVista extends View{
        private final float scaleDensity;

        public MiVista(Context context){
            super(context);
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            scaleDensity = metrics.scaledDensity;
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint linea = new Paint();
            Paint texto= new Paint();

            canvas.drawColor(Color.WHITE);

            linea.setColor(Color.GREEN);
            linea.setStyle(Paint.Style.STROKE);
            linea.setStrokeWidth(7);
            linea.setPathEffect(new DashPathEffect(new float[]{50, 30}, 0));

            texto.setColor(Color.BLACK);
            texto.setTextSize(50);

            int width = getWidth();
            int height = getHeight();

            float paso = 30 * scaleDensity;

            for (float i=paso; i<height; i+=paso){
                canvas.drawLine(0, i, width, i, linea);

                canvas.drawText(String.valueOf(i), 100, i-5, texto);
            }

            texto.setTextSize(20*scaleDensity);
            canvas.drawText("fila: " + height/4 + " scale= " + scaleDensity, 500, height/4, texto);
            canvas.drawText("fila: " + height/2 + " width= "  + width, 500, height/2, texto);
            canvas.drawText("fila: " + (height*3)/4 + " height= "  + height, 500, (height*3)/4, texto);
            canvas.drawText("ratio= " + (float)height/width, 500, height, texto );

        }
    }
}