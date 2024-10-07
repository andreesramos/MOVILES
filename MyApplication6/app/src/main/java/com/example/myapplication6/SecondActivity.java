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
        setContentView(R.layout.activity_second);

        // Obtener los datos del Intent
        String receivedString = getIntent().getStringExtra("myStringKey");
        double receivedDouble = getIntent().getDoubleExtra("myDoubleKey", 0.0);

        // Mostrar los datos en un Toast
        Toast.makeText(this, "String: " + receivedString + ", Double: " + receivedDouble, Toast.LENGTH_LONG).show();
    }



}