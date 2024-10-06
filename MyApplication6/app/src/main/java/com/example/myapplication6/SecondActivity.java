package com.example.myapplication6;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        String cadena=getIntent().getStringExtra("cadena");
        double decimal=getIntent().getDoubleExtra("decimal", 0.0);

        String mensaje="Cadena: " + cadena + "\nDecimal: " + decimal;
        Toast.makeText(SecondActivity.this, mensaje, Toast.LENGTH_LONG).show();
    }


}