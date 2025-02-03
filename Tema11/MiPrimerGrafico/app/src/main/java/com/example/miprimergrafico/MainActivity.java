package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
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
        public MiVista(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();

            String ancho="width: " + getMeasuredWidth();
            String alto="height: " + getMeasuredHeight();


        }
    }
}