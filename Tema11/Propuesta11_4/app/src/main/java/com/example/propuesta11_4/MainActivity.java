package com.example.propuesta11_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    private class MiVista extends View {
        public MiVista(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            String apellidos="Ramos Navarro";
            Paint pincel=new Paint();

            pincel.setTextSize(150);
            canvas.drawText(apellidos, 100,300,pincel);

            pincel.setColor(Color.RED);
            pincel.setTextSize(80);
            canvas.drawText(apellidos, 100,500,pincel);

            pincel.setTextAlign(Paint.Align.CENTER);
            pincel.setColor(Color.BLACK);
            canvas.drawText(apellidos, 700,700,pincel);

            pincel.setTextAlign(Paint.Align.LEFT);
            pincel.setTextScaleX(3);
            canvas.drawText(apellidos, 100, 900, pincel);

            pincel.setTextScaleX(1);
            pincel.setTextSkewX(0.5f);
            canvas.drawText(apellidos, 100, 1100, pincel);
        }
    }
}