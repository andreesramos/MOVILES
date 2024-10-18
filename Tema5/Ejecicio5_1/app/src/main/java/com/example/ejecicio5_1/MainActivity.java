package com.example.ejecicio5_1;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void primero(View view){
        Intent primero=new Intent(this, Primera.class);
        startActivity(primero);
    }

    public void segundo(View view){
        Intent segundo=new Intent(this, Segunda.class);
        startActivity(segundo);
    }

    public void tercero(View view){
        Intent tercero=new Intent(this, Tercera.class);
        startActivity(tercero);
    }

    public void cuarto(View view){
        Intent cuarto=new Intent(this, Cuarta.class);
        startActivity(cuarto);
    }
}