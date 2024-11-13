package com.example.propuesta6_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int posicion=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView texto = findViewById(R.id.texto);
        ListView lista = (ListView) findViewById(R.id.lista);
        EditText editText=(EditText) findViewById(R.id.editText);
        Button insertar=(Button) findViewById(R.id.insertar);
        Button eliminar=(Button) findViewById(R.id.eliminar);

        //Añadir datos
        ArrayList<String> datos = new ArrayList<>();
        datos.add("España");
        datos.add("Francia");
        datos.add("Belgica");
        datos.add("Croacia");
        datos.add("Portugal");
        datos.add("Paises Bajos");
        datos.add("Italia");
        datos.add("Dinamarca");
        datos.add("Suecia");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adaptador);

        //Seleccionar elemento de la lista al hacer click
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posicion = i;
                String elem = (String) adapterView.getItemAtPosition(i);
                texto.setText(elem);
            }
        });

        //Añadir elemento
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevo= editText.getText().toString();
                if(!nuevo.isEmpty()){
                    datos.add(nuevo);//Añade elemento
                    adaptador.notifyDataSetChanged();//Actualiza vista
                    editText.setText("");//Limpia el editText
                }else{
                    Toast.makeText(MainActivity.this, "Escribe algo para añadir", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Eliminar elemento
        eliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(posicion!=-1){
                    String eliminado= datos.get(posicion);
                    datos.remove(posicion);//Elimina elemento
                    adaptador.notifyDataSetChanged();//Actualiza lista
                    texto.setText("");//Limpia textView
                    posicion=-1;//Reinicia posicion
                    Toast.makeText(MainActivity.this, "Eliminado: " + eliminado, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}