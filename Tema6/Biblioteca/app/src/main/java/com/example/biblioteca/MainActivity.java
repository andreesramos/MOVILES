package com.example.biblioteca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
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
    //ArrayList<Usuario> usuarios=new ArrayList<>();
    BaseDatos dbHelper = new BaseDatos(this);
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        usuario=(EditText) findViewById(R.id.usuario);

        /*usuarios.add(new Usuario("andres", "andres"));
        usuarios.add(new Usuario("pablo", "pablo"));
        usuarios.add(new Usuario("marcos", "marcos"));*/

        /*dbHelper.insertarUsuario("andres", "andres");
        dbHelper.insertarUsuario("julio", "julio");
        dbHelper.insertarUsuario("manuel", "manuel");
        dbHelper.insertarUsuario("eva", "eva");*/

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario", ""));

        mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();

        boolean valido=validarUsuario(nombreIngresado, claveIngresada);
        if (valido) {
            editor.putString("usuario", nombreIngresado);
            editor.commit();
        }


    }

    public void registrarUsuario(String usuario, String clave) {
        long resultado = dbHelper.insertarUsuario(usuario, clave);
        if (resultado != -1) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validarUsuario(String usuario, String clave) {
        boolean valido = dbHelper.validarUsuario(usuario, clave);
        if (valido) {
            // Detener la música antes de cambiar de actividad
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

            Intent intent = new Intent(this, Lista.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }

}