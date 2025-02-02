package com.example.propuesta11_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Figura11_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiVista(this));
    }

    private class MiVista extends View{
        public MiVista(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();
            pincel.setColor(Color.BLACK);
            pincel.setStrokeWidth(30);
//            pincel.setColor(Color.argb(127,0,255,0));
//            pincel.setColor(0x7F00FF00);

            float x = getWidth() / 2;
            float y = getHeight() / 2;
            canvas.drawPoint(x,y,pincel);

            pincel.setStyle(Paint.Style.STROKE);
            pincel.setColor(Color.RED);
            canvas.drawRect(200,1000,1250,1700, pincel);

            pincel.setColor(Color.BLACK);
            canvas.drawCircle(x,y,200,pincel);

            pincel.setColor(Color.BLUE);
            canvas.drawOval(200,1000,1250,1700,pincel);

        }
    }


}