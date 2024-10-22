package com.example.ejercicio5_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Parte2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_parte2);
        Button miBoton1=(Button)findViewById(R.id.miBoton1);
        Button miBoton2=(Button)findViewById(R.id.miBoton2);
        miBoton1.setOnClickListener(this);
        miBoton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        TextView miTexto=(TextView)findViewById(R.id.miTexto);
        if(view.getId() == R.id.miBoton1) {
            miTexto.setText("BOTON 1 PULSADO");
        }else if(view.getId() == R.id.miBoton2) {
            miTexto.setText("BOTON 2 PULSADO");
        }

    }
}