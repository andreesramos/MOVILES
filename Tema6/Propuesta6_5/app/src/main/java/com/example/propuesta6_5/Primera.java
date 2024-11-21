package com.example.propuesta6_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Primera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_primera);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mensaje="Pulsado ";
        int id=item.getItemId();
        TextView texto = (TextView) findViewById(R.id.texto);
        if(id == R.id.lunes){
            texto.setText(mensaje + "Lunes");
            return true;
        }else if(id == R.id.martes){
            texto.setText(mensaje + "Martes");
            return true;
        }else if(id == R.id.miercoles) {
            texto.setText(mensaje + "Miércoles");
            return true;
        }
        else if(id == R.id.jueves) {
            texto.setText(mensaje + "Jueves");
            return true;
        }
        else if(id == R.id.viernes) {
            texto.setText(mensaje + "Viernes");
            return true;
        }
        else if(id == R.id.sabado) {
            texto.setText(mensaje + "Sábado");
            return true;
        }
        else if(id == R.id.domingo) {
            texto.setText(mensaje + "Domingo");
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}