package com.example.propuesta11_3;

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

public class Figura11_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiVista(this));
    }

    private class MiVista extends View {
        public MiVista(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();
            pincel.setColor(Color.RED);
            pincel.setStrokeWidth(30);

            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawRect(100,100,500,500, pincel);

            pincel.setStyle(Paint.Style.FILL);
            canvas.drawRect(100,600,500,1000, pincel);

            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(100,1100,500,1500, pincel);
        }
    }
}