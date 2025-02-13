package com.example.puntorojo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
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
        setContentView(new CustomView(this));

    }

    public class CustomView extends View {
        private float x=100, y=100;
        private Paint paint;

        public CustomView(Context context){
            super(context);
            paint=new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(100);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            paint.setColor(Color.RED);
            canvas.drawCircle(x, y, 50, paint);

            paint.setColor(Color.BLACK);
            canvas.drawText("x= " + x, 200, 300, paint);
            canvas.drawText("y= " + y, 200, 420, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            if(event.getAction()==MotionEvent.ACTION_MOVE){
                x=event.getX();
                y=event.getY();
                invalidate(); //Redibuja la vista
            }
            if(event.getAction()==MotionEvent.ACTION_UP){
                x=event.getX();
                y=event.getY();
                invalidate(); //Redibuja la vista
            }
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                x=event.getX();
                y=event.getY();
                invalidate(); //Redibuja la vista
            }
            return true;
        }
    }
}