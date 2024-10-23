package com.example.ej_animaciones;

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

    public void rotar1(View view){
        Intent rotar1=new Intent(this, Rotar1.class);
        startActivity(rotar1);
    }

    public void rotar2(View view){
        Intent rotar2=new Intent(this, Rotar2.class);
        startActivity(rotar2);
    }

    public void rotar3(View view){
        Intent rotar3=new Intent(this, Rotar3.class);
        startActivity(rotar3);
    }

    public void alfa1(View view){
        Intent alfa1=new Intent(this, Alfa1.class);
        startActivity(alfa1);
    }

    public void alfa2(View view){
        Intent alfa2=new Intent(this, Alfa2.class);
        startActivity(alfa2);
    }

    public void escala1(View view){
        Intent escala1=new Intent(this, Escala1.class);
        startActivity(escala1);
    }

    public void escala2(View view){
        Intent escala2=new Intent(this, Escala2.class);
        startActivity(escala2);
    }
    public void mueve1(View view){
        Intent mueve1=new Intent(this, Mueve1.class);
        startActivity(mueve1);
    }

    public void mueve2(View view){
        Intent mueve2=new Intent(this, Mueve2.class);
        startActivity(mueve2);
    }

    public void varios1(View view){
        Intent varios1=new Intent(this, Varios1.class);
        startActivity(varios1);
    }

    public void varios2(View view){
        Intent varios2=new Intent(this, Varios2.class);
        startActivity(varios2);
    }
}