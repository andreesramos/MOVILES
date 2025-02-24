package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Laberinto laberinto = new Laberinto(this);
        setContentView(laberinto);
    }
}
