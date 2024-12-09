package com.example.biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Insertar extends AppCompatActivity {

    private EditText tituloInsertar;
    private EditText autorInsertar;
    private RatingBar ratingInsertar;
    private DatePicker fechaInsertar;
    private Button botonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertar);

        tituloInsertar=(EditText) findViewById(R.id.tituloInsertar);
        autorInsertar=(EditText) findViewById(R.id.autorInsertar);
        ratingInsertar=(RatingBar) findViewById(R.id.ratingInsertar);
        fechaInsertar=(DatePicker) findViewById(R.id.fechaInsertar);
        botonGuardar=(Button) findViewById(R.id.botonGuardar);

        Intent intent=getIntent();
        if(intent!=null){
            boolean isEdit = intent.getBooleanExtra("isEdit", false);

            tituloInsertar.setText(intent.getStringExtra("titulo"));
            autorInsertar.setText(intent.getStringExtra("autor"));
            ratingInsertar.setRating(intent.getFloatExtra("rating", 0));

            int year = intent.getIntExtra("year", 2024);
            int month = intent.getIntExtra("month", 0);
            int day = intent.getIntExtra("day", 1);
            fechaInsertar.updateDate(year, month, day);

            if(isEdit){
                tituloInsertar.setEnabled(false);
            }
        }

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });
    }

    private void enviarDatos(){
        String titulo = tituloInsertar.getText().toString();
        String autor = autorInsertar.getText().toString();
        float rating = ratingInsertar.getRating();
        int year = fechaInsertar.getYear();
        int month = fechaInsertar.getMonth();
        int day = fechaInsertar.getDayOfMonth();
        int position = getIntent().getIntExtra("position", -1);

        Intent intent=new Intent();
        intent.putExtra("isEdit", false);
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