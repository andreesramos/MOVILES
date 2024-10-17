package com.example.ejecicio5_1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tercera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tercera);
        LinearLayout layout=findViewById(R.id.layout1);
        TextView miTexto= layout.findViewById(R.id.texto3);
        miTexto.append("\nTexto añadido con Append desde Java");
        miTexto.setTypeface(null, Typeface.ITALIC);
        miTexto.setTextColor(Color.BLUE);
        miTexto.setTextSize(20);
    }
}