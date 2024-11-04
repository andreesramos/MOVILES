package com.example.andres_prueba2;

import android.content.Intent;
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
        setContentView(R.layout.activity_main);

    }

    public void parte2(View view){
        Intent parte2=new Intent(this, Pantalla2.class);
        startActivity(parte2);
    }

    public void parte3(View view){
        Intent parte3=new Intent(this, Pantalla3.class);
        startActivity(parte3);
    }
    public void parte4(View view){
        Intent parte4=new Intent(this, Pantalla4.class);
        startActivity(parte4);
    }
}