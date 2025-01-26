package com.example.biblioteca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;
    ArrayList<Usuario> usuarios=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        usuario=(EditText) findViewById(R.id.usuario);

        usuarios.add(new Usuario("andres", "andres"));
        usuarios.add(new Usuario("pablo", "pablo"));
        usuarios.add(new Usuario("marcos", "marcos"));

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario", ""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        borrarArchivoHistorial();
    }

    private void borrarArchivoHistorial() {
        File file = new File(getFilesDir(), "historial_acciones.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public void acceder(View view){
        clave=(EditText) findViewById(R.id.clave);

        String nombreIngresado=usuario.getText().toString();
        String claveIngresada=clave.getText().toString();
        boolean valido=false;
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();


        for(Usuario u : usuarios){
            if(nombreIngresado.equals(u.getNombre()) && claveIngresada.equals(u.getClave())){
                valido=true;
                editor.putString("usuario", usuario.getText().toString());
                editor.commit();
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