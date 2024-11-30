package com.example.biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Insertar extends AppCompatActivity {

    private EditText tituloInsertar;
    private EditText autorInsertar;
    private RatingBar ratingInsertar;
    private Button botonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertar);

        tituloInsertar=(EditText) findViewById(R.id.tituloInsertar);
        autorInsertar=(EditText) findViewById(R.id.autorInsertar);
        ratingInsertar=(RatingBar) findViewById(R.id.ratingInsertar);
        botonGuardar=(Button) findViewById(R.id.botonGuardar);

        Intent intent=getIntent();
        if(intent!=null){
            tituloInsertar.setText(intent.getStringExtra("titulo"));
            autorInsertar.setText(intent.getStringExtra("autor"));
            ratingInsertar.setRating(intent.getFloatExtra("rating", 0));
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
        int position = getIntent().getIntExtra("position", -1);

        Intent intent=new Intent();
        intent.putExtra("titulo", titulo);
        intent.putExtra("autor", autor);
        intent.putExtra("rating", rating);
        intent.putExtra("position", position);

        setResult(RESULT_OK, intent);
        finish();
    }
}