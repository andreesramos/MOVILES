package com.example.biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;
    ArrayList<Usuario> usuarios=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuarios.add(new Usuario("andres", "andres"));
        usuarios.add(new Usuario("pablo", "pablo"));
        usuarios.add(new Usuario("marcos", "marcos"));
    }

    public void acceder(View view){
        usuario=(EditText) findViewById(R.id.usuario);
        clave=(EditText) findViewById(R.id.clave);


        String nombreIngresado=usuario.getText().toString();
        String claveIngresada=clave.getText().toString();
        boolean valido=false;

        for(Usuario u : usuarios){
            if(nombreIngresado.equals(u.getNombre()) && claveIngresada.equals(u.getClave())){
                valido=true;
            }
        }

        if(valido){
            Intent acceder=new Intent(this, Lista.class);
            startActivity(acceder);
        }else{
            Toast.makeText(MainActivity.this, "Usuario no existente o contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
}