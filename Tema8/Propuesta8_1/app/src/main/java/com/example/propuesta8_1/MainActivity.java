package com.example.propuesta8_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.id_barra_nuevo){
            mensaje(R.string.cadena_barra_nuevo);
            return true;
        } else if (item.getItemId() == R.id.id_barra_editar) {
            mensaje(R.string.cadena_barra_editar);
            return true;
        } else if (item.getItemId() == R.id.id_barra_configurar) {
            mensaje(R.string.cadena_barra_configurar);
            return true;
        } else if (item.getItemId() == R.id.id_barra_ayuda) {
            mensaje(R.string.cadena_barra_ayuda);
            return true;
        } else if (item.getItemId() == R.id.id_barra_acerca) {
            mensaje(R.string.cadena_barra_acerca);
            return true;
        }else{
            return onOptionsItemSelected(item);
        }
    }

    public void mensaje(int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
}