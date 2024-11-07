package com.example.figura6_5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        Datos[] datos=new Datos[]{
                new Datos("Linea Superior 1", "Linea Inferior 1"),
                new Datos("Linea Superior 2", "Linea Inferior 2"),
                new Datos("Linea Superior 3", "Linea Inferior 3"),
                new Datos("Linea Superior 4", "Linea Inferior 4")
        };
        ListView listado=(ListView) findViewById(R.id.lista);
        Adaptador miAdaptador=new Adaptador(this, datos);
        listado.setAdapter(miAdaptador);
        View miCabecera = getLayoutInflater().inflate(R.layout.cabecera, null);
        listado.addHeaderView(miCabecera);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptador, View v, int position, long id) {
                String seleccionado = ((Datos) adaptador.getItemAtPosition(position)).getTexto1();
            }
        });
    }
}