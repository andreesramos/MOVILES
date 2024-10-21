package com.example.ejecicio5_1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Cuarta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuarta);
        TextView miTexto=(TextView) findViewById(R.id.texto4);
        Typeface miFuente=Typeface.createFromAsset(getAssets(), "font/umbrella.ttf");
        miTexto.setTypeface(miFuente);
    }
}