package com.example.biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Mostrar extends AppCompatActivity {

    private TextView tituloMostrar;
    private TextView autorMostrar ;
    private RatingBar ratingMostrar;
    private DatePicker fechaMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar);

        tituloMostrar = (TextView) findViewById(R.id.tituloMostrar);
        autorMostrar = (TextView) findViewById(R.id.autorMostrar);
        ratingMostrar = (RatingBar) findViewById(R.id.ratingMostrar);
        fechaMostrar = (DatePicker) findViewById(R.id.fechaMostrar);

        Intent intent=getIntent();
        if(intent!=null){
            tituloMostrar.setText(intent.getStringExtra("titulo"));
            autorMostrar.setText(intent.getStringExtra("autor"));
            ratingMostrar.setRating(intent.getFloatExtra("rating", 0));

            int year = intent.getIntExtra("year", 2024);
            int month = intent.getIntExtra("month", 0);
            int day = intent.getIntExtra("day", 1);
            fechaMostrar.updateDate(year, month, day);
            //fechaMostrar.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS); //Bloque la interaccion del usuario
            fechaMostrar.setEnabled(false);  // Deshabilita el DatePicker
        }

    }

    private void enviarDatos(){
        String titulo = tituloMostrar.getText().toString();
        String autor = autorMostrar.getText().toString();
        float rating = ratingMostrar.getRating();
        int year = fechaMostrar.getYear();
        int month = fechaMostrar.getMonth();
        int day = fechaMostrar.getDayOfMonth();
        int position = getIntent().getIntExtra("position", -1);

        Intent intent=new Intent();
        intent.putExtra("titulo", titulo);
        intent.putExtra("autor", autor);
        intent.putExtra("rating", rating);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("position", position);

        setResult(RESULT_OK, intent);
        finish();
    }
}