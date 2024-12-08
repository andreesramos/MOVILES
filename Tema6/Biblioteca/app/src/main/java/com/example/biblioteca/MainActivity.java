package com.example.biblioteca;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;
    ArrayList<Usuario> usuarios=new ArrayList<>();
    Button btnEnglish;
    Button btnFrench;
    Button btnSpanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuarios.add(new Usuario("andres", "andres"));
        usuarios.add(new Usuario("pablo", "pablo"));
        usuarios.add(new Usuario("marcos", "marcos"));

        btnEnglish= (Button) findViewById(R.id.english);
        btnFrench= (Button) findViewById(R.id.french);
        btnSpanish = (Button) findViewById(R.id.spanish);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
            }
        });

        btnFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("fr");
            }
        });

        btnSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("es");
            }
        });
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

    public void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Reiniciar la actividad para aplicar cambios
        recreate();
        Toast.makeText(this, "Idioma cambiado a " + languageCode, Toast.LENGTH_SHORT).show();
    }
}