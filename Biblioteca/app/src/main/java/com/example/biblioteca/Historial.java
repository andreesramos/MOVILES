package com.example.biblioteca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Historial extends AppCompatActivity {

    private HistorialManager historialManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);

        historialManager=new HistorialManager(this);

        TextView textoHistorial = findViewById(R.id.textoHistorial);
        Button botonLimpiarHistorial = findViewById(R.id.botonLimpiarHistorial);

        // Mostrar el historial
        textoHistorial.setText(historialManager.leerHistorial());

        // Botón para limpiar el historial
            botonLimpiarHistorial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    File file= new File(getFilesDir(), "historial_acciones.txt");
                    if(file.exists()){
                        file.delete();
                    }
                    textoHistorial.setText("Historial eliminado.");
                }
            });
    }
}