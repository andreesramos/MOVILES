package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button mas=(Button) findViewById(R.id.mas);
        mas.setOnClickListener(this);
        Button menos=(Button) findViewById(R.id.menos);
        menos.setOnClickListener(this);
        Button multi=(Button) findViewById(R.id.multi);
        multi.setOnClickListener(this);
        Button divi=(Button) findViewById(R.id.division);
        divi.setOnClickListener(this);
        Button potencia=(Button) findViewById(R.id.potencia);
        potencia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        TextView editText = findViewById(R.id.editText);
        if(view.getId() == R.id.mas){
            editText.append("+");
        } else if (view.getId() == R.id.menos) {
            editText.append("-");
        }else if (view.getId() == R.id.multi) {
            editText.append("*");
        }else if (view.getId() == R.id.division) {
            editText.append("/");
        }else if (view.getId() == R.id.potencia) {
            editText.append("^");
        }
    }
}